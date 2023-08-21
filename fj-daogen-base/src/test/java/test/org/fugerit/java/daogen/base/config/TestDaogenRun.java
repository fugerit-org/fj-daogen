package test.org.fugerit.java.daogen.base.config;

import java.io.File;
import java.io.FileInputStream;

import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.junit.Assert;
import org.junit.Test;

import test.org.fugerit.java.MemDBTestBase;

public class TestDaogenRun extends MemDBTestBase {

	@Test
	public void testDaoGeneration() {
		boolean ok = false;
		try {
			try ( FileInputStream fis = new FileInputStream( new File( "src/test/resources/sample/fugerit-sample-daogen-config.xml" ) ) ) {
				logger.info( "DAOGEN start!" );
				DaogenFacade.generate( fis );
				logger.info( "DAOGEN end!" );
				ok = true;
			}	
		} catch (Exception e) {
			logger.error( "Error : "+e, e );
		}
		Assert.assertTrue( ok );
	}
	
}
