package test.org.fugerit.java.daogen.base.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.tool.handler.CompareHandler;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCompareHandler {
	
	private static final String SOURCE_GENERATED = "src/test/resources/compare_handler_test/daogen_generated";
	
	private static final String SOURCE_ORIGINAL = "src/test/resources/compare_handler_test/daogen_original";
	
	private static void copyHelper( File baseSource, File baseDest, File currentSource ) throws IOException {
		String relPath = currentSource.getCanonicalPath().substring( baseSource.getCanonicalPath().length() );
		File newFile = new File( baseDest, relPath );
		if ( currentSource.isDirectory() ) {
			newFile.mkdirs();
			for ( File current : currentSource.listFiles() ) {
				copyHelper(baseSource, baseDest, current);
			}
		} else {
			try ( FileInputStream fis = new FileInputStream( currentSource );
					FileOutputStream fos = new FileOutputStream( newFile ) ) {
				StreamIO.pipeStream(fis, fos, StreamIO.MODE_CLOSE_NONE);
			}
		}
	}
	
	private static int deleteRecurse( File dir ) {
		int res = 0;
		if ( dir.isDirectory() ) {
			for ( File file : dir.listFiles() ) {
				res+= deleteRecurse( file );
			}
		}
		if ( dir.delete() ) {
			res++;
		}
		return res;
	}
	
	private static void copyHelper( File sourceFile, File testDir ) throws IOException {
		if ( testDir.exists() ) {
			int res = deleteRecurse( testDir );
			log.info( "delete result {} : {}", testDir, res );
		}
		copyHelper(sourceFile, testDir, sourceFile );
	}
	
	private static void prepare( File file1, File file2 ) {
		SafeFunction.apply( () -> {
			File sourceDir = new File( SOURCE_GENERATED );
			File sourceDirOriginal = new File( SOURCE_ORIGINAL );
			copyHelper( sourceDir, file1 );
			copyHelper( sourceDirOriginal, file2 );
		});
	}
	
	@Test
	public void test001() {
		File baseDir = new File( "target" );
		File file1 = new File( baseDir, "daogen1" );
		File file2 = new File( baseDir, "daogen2" );
		prepare(file1, file2);
		Assert.assertTrue( file1.exists() );
		Assert.assertTrue( file2.exists() );
		CompareHandler handler = new CompareHandler();
		Properties params = new Properties();
		params.setProperty( CompareHandler.ARG_REPORT , "target/report_compare_handler_001.md" );
		params.setProperty( CompareHandler.ARG_TRY_CORRECT_HELPER , BooleanUtils.BOOLEAN_TRUE );
		params.setProperty( CompareHandler.ARG_TRY_DELETE_EQUAL , BooleanUtils.BOOLEAN_TRUE );
		handler.handleCompare( baseDir, file1, file2, params );
	}
	
}
