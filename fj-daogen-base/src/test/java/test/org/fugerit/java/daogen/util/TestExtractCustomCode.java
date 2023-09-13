package test.org.fugerit.java.daogen.util;

import java.io.File;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.javagen.SimpleJavaGenerator;
import org.fugerit.java.daogen.base.gen.util.ExtractCustomCode;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestExtractCustomCode {

	@Test
	public void test001() {
		SafeFunction.apply( () -> {
			String path = "../fj-daogen-tool/src/test/resources/compare_handler_test/daogen_original/org/fugerit/java/daogen/sample/impl/facade/data/DataEntityAddressFacadeHelper.java";
			String pathReal = "../fj-daogen-tool/src/test/resources/compare_handler_test/daogen_original/org/fugerit/java/daogen/sample/impl/facade/data/DataEntityAddressFacade.java";
			File file = new File( path );
			File fileReal = new File( pathReal );
			log.info( "try source : {}", file.getCanonicalPath() );
			String content = FileIO.readString( file );
			String result = ExtractCustomCode.extractCustom( content , SimpleJavaGenerator.CUSTOM_CODE_START, SimpleJavaGenerator.CUSTOM_CODE_END );
			Assert.assertNotNull( result );
			log.info( "result : {}", result );
			// try to correct
			log.info( "try correct : {}", fileReal.getCanonicalPath() );
			String realContent = FileIO.readString( fileReal );
			// test real mode 1
			String resultReal = ExtractCustomCode.addCustomContent( realContent , SimpleJavaGenerator.CUSTOM_CODE_START, SimpleJavaGenerator.CUSTOM_CODE_END, result );
			Assert.assertNotNull( resultReal );
			// test real mode 2
			log.info( "try correct alt : {}", fileReal.getCanonicalPath() );
			String resultRealAlt = ExtractCustomCode.addBeforeClassEnd( realContent , result );
			log.info( "real result alt {}", resultRealAlt );
			Assert.assertNotNull( resultRealAlt );			
		} );
	}
	
	@Test
	public void test002() {
		SafeFunction.apply( () -> {
			String path = "../fj-daogen-tool/src/test/resources/compare_handler_test/daogen_original/org/fugerit/java/daogen/sample/impl/facade/data/DataEntityAddressFacadeHelper.java";
			String pathReal = "../fj-daogen-tool/src/test/resources/compare_handler_test/daogen_original/org/fugerit/java/daogen/sample/impl/facade/data/DataEntityAddressFacade.java";
			File file = new File( path );
			File fileReal = new File( pathReal );
			log.info( "try source : {}", file.getCanonicalPath() );
			String result = ExtractCustomCode.extractCustom( file , SimpleJavaGenerator.CUSTOM_IMPORT_START, SimpleJavaGenerator.CUSTOM_IMPORT_END );
			Assert.assertNotNull( result );
			log.info( "result : {}", result );
			// try to correct
			log.info( "try correct : {}", fileReal.getCanonicalPath() );
			String realContent = FileIO.readString( fileReal );
			// test real mode 1
			String resultReal = ExtractCustomCode.addCustomContent( realContent , SimpleJavaGenerator.CUSTOM_IMPORT_START, SimpleJavaGenerator.CUSTOM_IMPORT_END, result );
			Assert.assertNotNull( resultReal );
			// test real mode 2
			log.info( "try correct alt : {}", fileReal.getCanonicalPath() );
			String resultRealAlt = ExtractCustomCode.addAfterPackageClassEnd( realContent , result );
			log.info( "real result alt {}", resultRealAlt );
			Assert.assertNotNull( resultRealAlt );			
		} );
	}
	
}


