package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.WrapperTestTwoFieldKey;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestTwoFieldKeyJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestTwoFieldKeyJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( TestTwoFieldKeyJunit4ModelTest.class );

	public void printAll( ModelTestTwoFieldKey current ) { 
		 logger.info( "ID_ONE-> {}", current.getIdOne() );
		 logger.info( "ID_TWO-> {}", current.getIdTwo() );
		 logger.info( "INFO-> {}", current.getInfo() );
	}

	public ModelTestTwoFieldKey newInstance() { 
		WrapperTestTwoFieldKey current = new WrapperTestTwoFieldKey( new HelperTestTwoFieldKey() );
		current.setIdOne(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setIdTwo(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setInfo("1");
		Assert.assertFalse( current.isEmpty() );
		logger.info( "unwrap :  {}", current.unwrap( current ) );
		return current;
	}

	@Test
	public void testJUnit4ModelTestTwoFieldKey() { 
		ModelTestTwoFieldKey current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		logger.info( "current isEmpty() : {}", current.isEmpty() );
		org.fugerit.java.core.function.SafeFunction.apply( () -> org.fugerit.java.core.io.ObjectIO.fullSerializationTest( current ) );
		Assert.assertNotNull( current );
	}

}
