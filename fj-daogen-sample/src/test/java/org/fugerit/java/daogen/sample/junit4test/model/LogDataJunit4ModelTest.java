package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.daogen.sample.impl.helper.HelperLogData;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LogDataJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class LogDataJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( LogDataJunit4ModelTest.class );
	public ModelLogData newInstance() { 
		HelperLogData current = new HelperLogData();
		current.setId(new java.math.BigDecimal( "1" ));
		current.setLogTime(new java.util.Date());
		current.setInfo("1");
		return current;
	}
	public void printAll( ModelLogData current ) { 
		 logger.info( "ID-> {}", current.getId() );
		 logger.info( "LOG_TIME-> {}", current.getLogTime() );
		 logger.info( "INFO-> {}", current.getInfo() );
	}

	@Test
	public void testModelLogData() { 
		ModelLogData current = this.newInstance();
		this.printAll( current );
		Assert.assertNotNull( current );
	}

}
