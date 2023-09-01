package test.org.fugerit.java.daogen.base.config;

import java.io.File;
import java.io.FileInputStream;

import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import test.org.fugerit.java.MemDBTestBase;

@Slf4j
public class TestDaogenRun extends MemDBTestBase {

	@Test
	public void testDaoGeneration() {
		boolean ok = false;
		try {
			try ( FileInputStream fis = new FileInputStream( new File( "src/test/resources/sample/daogenruntest-sample-daogen-config.xml" ) ) ) {
				log.info( "DAOGEN start!" );
				DaogenFacade.generate( fis );
				log.info( "DAOGEN end!" );
				ok = true;
			}	
		} catch (Exception e) {
			log.error( "Error : "+e, e );
		}
		Assert.assertTrue( ok );
	}
	
}
