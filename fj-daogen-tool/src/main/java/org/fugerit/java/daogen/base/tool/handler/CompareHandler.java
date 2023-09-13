package org.fugerit.java.daogen.base.tool.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.javagen.SimpleJavaGenerator;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.gen.util.ExtractCustomCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompareHandler {

	private static final String SERIAL_UID_STRING = "serialVersionUID";
	
	public static final String ARG_REPORT = "report";
	
	public static final String ARG_TRY_DELETE_EQUAL = "try-delere-equal";
	
	public static final String ARG_TRY_CORRECT_HELPER = "try-correct-helper";
	public static final String ARG_CUSTOM_CODE_START = "custom-code-start";
	public static final String ARG_CUSTOM_CODE_END = "custom-code-end";
	public static final String ARG_CUSTOM_IMPORT_START = "custom-import-start";
	public static final String ARG_CUSTOM_IMPORT_END = "custom-import-end";
	
	public void handleCompare( File baseDir, File file1, File file2, Properties params ) {
		SafeFunction.apply( () -> {
			try ( StringWriter writer = new StringWriter(); 
					PrintWriter report = new PrintWriter( writer, true ) ) {
				String removePath = baseDir == null ? "" : baseDir.getCanonicalPath();
				handleCompareWork(removePath, file1, file2, params, report);
				log.info( "print report : \n{}", writer.toString() );
				String reportFile = params.getProperty( ARG_REPORT );
				if ( StringUtils.isNotEmpty( reportFile ) ) {
					log.info( "write report file : {}", reportFile );
					FileIO.writeString( writer.toString() , new File(reportFile) );
				}
			}
		} );
	}
	
	private String removePath( String path, String removePath ) {
		String res = path;
		if ( StringUtils.isNotEmpty( removePath ) && path.indexOf( removePath ) == 0) {
			res = path.substring( removePath.length() );
		}
		return res;
	}
	
	private void handleRealFileCorrect( File file2, Properties params, PrintWriter report, File realFile ) throws IOException {
		report.print( "real file exists, try to correct from helper : "+realFile.getName() );
		String customCodeStart = params.getProperty( ARG_CUSTOM_CODE_START, SimpleJavaGenerator.CUSTOM_CODE_START );
		String customCodeEnd = params.getProperty( ARG_CUSTOM_CODE_END, SimpleJavaGenerator.CUSTOM_CODE_END );
		String customImportStart = params.getProperty( ARG_CUSTOM_IMPORT_START, SimpleJavaGenerator.CUSTOM_IMPORT_START );
		String customImportEnd = params.getProperty( ARG_CUSTOM_IMPORT_END, SimpleJavaGenerator.CUSTOM_IMPORT_END );
		String contentCode = ExtractCustomCode.extractCustom( file2 , customCodeStart, customCodeEnd);
		String contentImport = ExtractCustomCode.extractCustom( file2 , customImportStart, customImportEnd);
		log.info( "customCode    : {}", contentCode );
		log.info( "contentImport : {}", contentImport );
		if ( StringUtils.isNotEmpty( contentCode ) || StringUtils.isNotEmpty( contentImport ) ) {
			String realFileContent = FileIO.readString( realFile );	
			if ( StringUtils.isNotEmpty( contentCode ) ) {
				realFileContent = ExtractCustomCode.addCustomContent( realFileContent , customCodeStart, customCodeEnd, contentCode );
			}
			if ( StringUtils.isNotEmpty( contentImport ) ) {
				realFileContent = ExtractCustomCode.addCustomContent( realFileContent , customImportStart, customImportEnd, contentImport );
			}
			FileIO.writeString( realFileContent , realFile );
			report.print( " real file customized! "+realFileContent );
		}
	}
	
	private void tryCorrectHelperEqual( File file2, Properties params, PrintWriter report ) throws IOException {
		boolean tryCorrectHelper = BooleanUtils.isTrue( params.getProperty( ARG_TRY_CORRECT_HELPER ) );
		if ( tryCorrectHelper && file2.getName().endsWith( "Helper.java" ) ) {
			String realFileName = file2.getName().replace( "Helper.java" , ".java" );
			File realFile = new File( file2.getParentFile(), realFileName );
			if ( realFile.exists() ) {
				handleRealFileCorrect(file2, params, report, realFile);
			} else {
				report.print( "default real file not found : "+realFileName );
			}
		}
	}
	
	private void tryDeleteEqual( File file2, Properties params, PrintWriter report ) throws IOException {
		boolean tryDeleteEqual = BooleanUtils.isTrue( params.getProperty( ARG_TRY_DELETE_EQUAL ) );
		if ( tryDeleteEqual ) {
			report.print( " - try delete equal result : "+ Files.deleteIfExists( file2.toPath() )  );
		}
	}
	
	private void checkDiffResult( File file2, Properties params, PrintWriter report, int diffSize, boolean differentUid ) throws IOException {
		if ( diffSize > 0 ) {
			report.print( " - diffeent lines : "+diffSize );
		} else {
			this.tryDeleteEqual(file2, params, report);
		}
		if ( differentUid ) {
			report.print( " - diffeent serialVersionUID" );
		}
	}
	
	private void checkLines( File file2, Properties params, PrintWriter report, List<String> lines1, List<String> lines2 ) throws IOException {
		report.print( "file 1 size : "+lines1.size()+", " );
		if ( lines1.size() == lines2.size() ) {
			report.print( "file 2 same size! " );
			boolean differentUid = false;
			int diffSize = 0;
			for ( int k=0; k<lines1.size(); k++ ) {
				String current1 = lines1.get( k );
				String current2 = lines2.get( k );
				if ( !current1.equals( current2 ) ) {
					if ( current1.contains( SERIAL_UID_STRING ) && current2.contains( SERIAL_UID_STRING ) ) {
						differentUid = true;
					} else {
						diffSize++;
					}
				}
			}
			this.checkDiffResult( file2, params, report, diffSize, differentUid);
		} else {
			report.print( "file 2 size : "+lines1.size()+" DIFFERENT! " );
			this.tryCorrectHelperEqual(file2, params, report);
		}
		report.println();
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
		this.checkLines(file2, params, report, lines1, lines2);
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
