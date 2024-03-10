package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.daogen.sample.impl.helper.HelperLogData;
import org.fugerit.java.daogen.sample.impl.helper.WrapperLogData;
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

	public void printAll( ModelLogData current ) { 
		 logger.info( "ID-> {}", current.getId() );
		 logger.info( "LOG_TIME-> {}", current.getLogTime() );
		 logger.info( "INFO-> {}", current.getInfo() );
	}

	public ModelLogData newInstance() { 
		WrapperLogData current = new WrapperLogData( new HelperLogData() );
		current.setId(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setLogTime(new java.util.Date());
		Assert.assertFalse( current.isEmpty() );
		current.setInfo("1");
		Assert.assertFalse( current.isEmpty() );
		logger.info( "unwrap :  {}", current.unwrap( current ) );
		return current;
	}

	@Test
	public void testJUnit4ModelLogData() { 
		ModelLogData current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		logger.info( "current isEmpty() : {}", current.isEmpty() );
		org.fugerit.java.core.function.SafeFunction.apply( () -> org.fugerit.java.core.io.ObjectIO.fullSerializationTest( current ) );
		Assert.assertNotNull( current );
	}

}
