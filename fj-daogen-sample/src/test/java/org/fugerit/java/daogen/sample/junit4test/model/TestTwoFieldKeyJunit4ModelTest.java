package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestTwoFieldKey;
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
	public ModelTestTwoFieldKey newInstance() { 
		HelperTestTwoFieldKey current = new HelperTestTwoFieldKey();
		current.setIdOne(new java.math.BigDecimal( "1" ));
		current.setIdTwo(new java.math.BigDecimal( "1" ));
		current.setInfo("1");
		return current;
	}
	public void printAll( ModelTestTwoFieldKey current ) { 
		 logger.info( "ID_ONE-> {}", current.getIdOne() );
		 logger.info( "ID_TWO-> {}", current.getIdTwo() );
		 logger.info( "INFO-> {}", current.getInfo() );
	}

	@Test
	public void testModelTestTwoFieldKey() { 
		ModelTestTwoFieldKey current = this.newInstance();
		this.printAll( current );
		Assert.assertNotNull( current );
	}

}
