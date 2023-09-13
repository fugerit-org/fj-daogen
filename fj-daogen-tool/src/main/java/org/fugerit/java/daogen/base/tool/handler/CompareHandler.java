package org.fugerit.java.daogen.base.tool.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompareHandler {

	public static final String ARG_REPORT = "report";
	
	public static final String ARG_TRY_DELETE_EQUAL = "try-delere-equal";
	
	public static final String ARG_TRY_CORRECT_HELPER = "try-correct-helper";
	
	public void handleCompare( File baseDir, File file1, File file2, Properties params ) {
		SafeFunction.apply( () -> {
			try ( StringWriter writer = new StringWriter(); 
					PrintWriter report = new PrintWriter( writer, true ) ) {
				String removePath = baseDir == null ? "" : baseDir.getCanonicalPath();
				handleCompareWork(removePath, file1, file2, params, report);
				String reportFile = params.getProperty( ARG_REPORT );
				if ( StringUtils.isNotEmpty( reportFile ) ) {
					log.info( "write report file : {}", reportFile );
					FileIO.writeString( writer.toString() , new File(reportFile) );
				} else {
					log.info( "print report : \n{}", writer.toString() );
				}
			}
		} );

	}
	
	private String removePath( String path, String removePath ) {
		String res = path;
		if ( StringUtils.isNotEmpty( removePath ) ) {
			if ( path.indexOf( removePath ) == 0 ) {
				res = path.substring( removePath.length() );
			}
		}
		return res;
	}
	
	private void tryCorrectHelperEqual( File file1, File file2, Properties params, PrintWriter report ) {
		boolean tryCorrectHelper = BooleanUtils.isTrue( params.getProperty( ARG_TRY_CORRECT_HELPER ) );
		if ( tryCorrectHelper ) {
			if ( file2.getName().endsWith( "Helper.java" ) ) {
				String realFileName = file2.getName().replace( "Helper.java" , ".java" );
				File realFile = new File( file2.getParentFile(), realFileName );
				if ( realFile.exists() ) {
					report.print( "real file exists, try to correct from helper : "+realFileName );
				} else {
					report.print( "default real file not found : "+realFileName );
				}
			}
		}
	}
	
	private void tryDeleteEqual( File file1, File file2, Properties params, PrintWriter report ) {
		boolean tryDeleteEqual = BooleanUtils.isTrue( params.getProperty( ARG_TRY_DELETE_EQUAL ) );
		if ( tryDeleteEqual ) {
			report.print( " - try delete equal result : "+file2.delete() );
		}
	}
	
	private void compareFile( File file1, File file2, Properties params, PrintWriter report ) throws IOException {
		List<String> lines1 = new ArrayList<>();
		List<String> lines2 = new ArrayList<>();
		try ( BufferedReader reader1 = new BufferedReader( new FileReader( file1 ) );
				BufferedReader reader2 = new BufferedReader( new FileReader( file2 ) ) ) {
			lines1.addAll( reader1.lines().collect( Collectors.toList() ) );
			lines2.addAll( reader2.lines().collect( Collectors.toList() ) );
		}
		// check
		report.print( "file 1 size : "+lines1.size()+", " );
		if ( lines1.size() == lines2.size() ) {
			report.print( "file 2 same size! " );
			boolean differentUid = false;
			int diffSize = 0;
			for ( int k=0; k<lines1.size(); k++ ) {
				String current1 = lines1.get( k );
				String current2 = lines2.get( k );
				if ( !current1.equals( current2 ) ) {
					if ( current1.contains( "serialVersionUID" ) && current2.contains( "serialVersionUID" ) ) {
						differentUid = true;
					} else {
						diffSize++;
					}
				}
			}
			if ( diffSize > 0 ) {
				report.print( " - diffeent lines : "+diffSize );
			} else {
				this.tryDeleteEqual(file1, file2, params, report);
			}
			if ( differentUid ) {
				report.print( " - diffeent serialVersionUID" );
			}
		} else {
			report.print( "file 2 size : "+lines1.size()+" DIFFERENT! " );
			this.tryCorrectHelperEqual(file1, file2, params, report);
		}
		report.println();
	}
	
	private void handleCompareWork( String removePath, File file1, File file2, Properties params, PrintWriter report ) throws ConfigException, IOException {
		String fileEntry1 = removePath( file1.getCanonicalPath(), removePath );
		String fileEntry2 = removePath( file2.getCanonicalPath(), removePath );
		log.info( "handleCompare {} - {}", fileEntry1, fileEntry2 );
		if ( file1.isDirectory() && file2.isDirectory() ) {
			report.println( "# directory : "+fileEntry1 );
			for ( File file : file1.listFiles() ) {
				if ( file.isDirectory() ) {
					report.println();
				}
				this.handleCompareWork( removePath, file, new File( file2, file.getName() ), params, report );
			}
		} else if ( file1.isDirectory() || file2.isDirectory() ) {
			throw new ConfigException( "Only one is directory : "+file1+"("+file1.isDirectory()+"), : "+file2+"("+file2.isDirectory()+")" );
		} else {
			report.print( "- "+fileEntry1+" -> "+fileEntry2+" : " );
			if ( !file2.exists() ) {
				report.println( "does not exist!" );
			} else {
				report.println( "exists : " );
				this.compareFile(file1, file2, params, report);
			}
		}
	}
	
}
