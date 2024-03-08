package test.org.fugerit.java.daogen.base.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.util.result.Result;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import test.org.fugerit.java.MemDBTestBase;

@Slf4j
public class TestDaogenRun extends MemDBTestBase {

	private int testDaoGenerationWorker( File baseDir, Properties overrideProperties ) {
		int result = Result.RESULT_CODE_OK;
		try {
			overrideProperties.setProperty( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER , baseDir.getCanonicalPath() );
			log.info( "overrideProperties : {}", overrideProperties );
			try ( FileInputStream fis = new FileInputStream( new File( "src/test/resources/sample/daogenruntest-sample-daogen-config.xml" ) ) ) {
				log.info( "DAOGEN start!" );
				DaogenFacade.generate( fis, overrideProperties );
				log.info( "DAOGEN end!" );
			}
		} catch ( Exception e ) {
			result = Result.RESULT_CODE_KO;
			logger.info( "Errore : "+e, e );
		}
		return result;
	}
	
	@Test
	public void testDaoGenerationJdk17SpringBoot() throws IOException, ConfigException {
		File file = new File( "target/daogen-run-jdk17-sprint-boot" );
		Properties overrideProperties = new Properties();
		overrideProperties.setProperty( 
				DaogenCatalogConstants.GEN_PROP_PACKAGE_SPRING_REST_LOAD , "org.fugerit.java.daogen.sample.impl.spring.load" );
		overrideProperties.setProperty( 
				DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST , BooleanUtils.BOOLEAN_TRUE );		
		overrideProperties.setProperty( 
				DaogenCatalogConstants.GEN_PROP_JDK_TARGET_VERSION , DaogenCatalogConstants.GEN_PROP_JDK_TARGET_VERSION_17.toString() );
		int result = this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertTrue( file.exists() );
		Assert.assertEquals( Result.RESULT_CODE_OK, result );
	}

	@Test
	public void testDaoGenerationClassicalEntity() throws IOException, ConfigException {
		File file = new File( "target/daogen-run-classical-entity" );
		Properties overrideProperties = new Properties();
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE ,
				DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE_DISABLED);
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE ,
				DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_DISABLED );
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE ,
				DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_DISABLED );
		int result = this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertTrue( file.exists() );
		Assert.assertEquals( Result.RESULT_CODE_OK, result );
	}

	@Test
	public void testDaoGenerationFailHelperNg() throws IOException, ConfigException {
		File file = new File( "target/daogen-run-fail-helper-ng" );
		Properties overrideProperties = new Properties();
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE , "unknown" );
		int result = this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertEquals( Result.RESULT_CODE_KO, result );
	}

	@Test
	public void testDaoGenerationFailWrapperNg() throws IOException, ConfigException {
		File file = new File( "target/daogen-run-fail-wrapper-ng" );
		Properties overrideProperties = new Properties();
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE , "unknown" );
		int result = this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertEquals( Result.RESULT_CODE_KO, result );
	}

	@Test
	public void testDaoGenerationFailFinderNg() throws IOException, ConfigException {
		File file = new File( "target/daogen-run-fail-finder-ng" );
		Properties overrideProperties = new Properties();
		overrideProperties.setProperty(
				DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE , "unknown" );
		int result = this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertEquals( Result.RESULT_CODE_KO, result );
	}

	@Test
	public void testDaoGenerationDefault() throws IOException, ConfigException {
		File file = new File( "target/daogen-run" );
		try ( FileInputStream fis = new FileInputStream( new File( "src/test/resources/sample/daogenruntest-sample-daogen-config.xml" ) ) ) {
			log.info( "DAOGEN start!" );
			DaogenFacade.generate( fis );
			log.info( "DAOGEN end!" );
		}
		Assert.assertTrue( file.exists() );
	}
	
}
