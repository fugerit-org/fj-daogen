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
	
	private static final String SOURCE_TEST = "src/test/resources/compare_handler_test/daogen";
	
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
	
	private static void copyHelper( File sourceFile, File testDir ) throws IOException {
		if ( !testDir.exists() ) {
			copyHelper(sourceFile, testDir, sourceFile );
		} else {
			log.info( "testDir already exists! {}", testDir );
		}
	}
	
	private static void prepare( File file1, File file2 ) {
		SafeFunction.apply( () -> {
			File sourceDir = new File( SOURCE_TEST );
			copyHelper( sourceDir, file1 );
			copyHelper( sourceDir, file2 );
		});
	}
	
	@Test
	public void test() {
		File baseDir = new File( "target" );
		File file1 = new File( baseDir, "daogen1" );
		File file2 = new File( baseDir, "daogen2" );
		prepare(file1, file2);
		Assert.assertTrue( file1.exists() );
		Assert.assertTrue( file2.exists() );
		CompareHandler handler = new CompareHandler();
		Properties params = new Properties();
		params.setProperty( CompareHandler.ARG_TRY_CORRECT_HELPER , BooleanUtils.BOOLEAN_TRUE );
		params.setProperty( CompareHandler.ARG_TRY_DELETE_EQUAL , BooleanUtils.BOOLEAN_TRUE );
		handler.handleCompare( baseDir, file1, file2, params );
	}
	
}
