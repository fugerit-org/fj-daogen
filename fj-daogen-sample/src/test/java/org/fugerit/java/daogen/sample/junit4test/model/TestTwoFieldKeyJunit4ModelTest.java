package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.facade.TestTwoFieldKeyFinder;
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
		ModelTestTwoFieldKey model = new HelperTestTwoFieldKey();
		WrapperTestTwoFieldKey current = new WrapperTestTwoFieldKey( model );
		Assert.assertTrue( current.isEmpty() );
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
		Assert.assertNotNull( current );
		TestTwoFieldKeyFinder finder1 = new TestTwoFieldKeyFinder();
		finder1.setModel( current );
		logger.info( "finder1.getModel() -> {}", finder1.getModel() );
		Assert.assertNotNull( TestTwoFieldKeyFinder.newInstance( current ) );
		Assert.assertNotNull( finder1 );
	}

}
