package test.org.fugerit.java.daogen.base.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import test.org.fugerit.java.MemDBTestBase;

@Slf4j
public class TestDaogenRun extends MemDBTestBase {

	private void testDaoGenerationWorker( File baseDir, Properties overrideProperties ) {
		try {
			overrideProperties.setProperty( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER , baseDir.getCanonicalPath() );
			log.info( "overrideProperties : {}", overrideProperties );
			try ( FileInputStream fis = new FileInputStream( new File( "src/test/resources/sample/daogenruntest-sample-daogen-config.xml" ) ) ) {
				log.info( "DAOGEN start!" );
				DaogenFacade.generate( fis, overrideProperties );
				log.info( "DAOGEN end!" );
			}
		} catch ( Exception e ) {
			logger.info( "Errore : "+e, e );
		}
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
		this.testDaoGenerationWorker(file, overrideProperties);
		Assert.assertTrue( file.exists() );
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
