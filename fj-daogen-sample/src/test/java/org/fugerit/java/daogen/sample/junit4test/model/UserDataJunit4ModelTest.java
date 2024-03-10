package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelUserData;
import org.fugerit.java.daogen.sample.impl.helper.HelperUserData;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUserData;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserDataJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserDataJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( UserDataJunit4ModelTest.class );
	public void printAll( ModelUserData current ) { 
		 logger.info( "ID-> {}", current.getId() );
		 logger.info( "USERNAME-> {}", current.getUsername() );
		 logger.info( "PASSWORD-> {}", current.getPassword() );
		 logger.info( "LAST_LOGIN-> {}", current.getLastLogin() );
		 logger.info( "DATE_INSERT-> {}", current.getDateInsert() );
		 logger.info( "DATE_UPDATE-> {}", current.getDateUpdate() );
		 logger.info( "STATE-> {}", current.getState() );
	}

	public ModelUserData newInstance() { 
		WrapperUserData current = new WrapperUserData( new HelperUserData() );
		current.setId(new java.math.BigDecimal( "1" ));
		current.setUsername("1");
		current.setPassword("1");
		current.setLastLogin(new java.util.Date());
		current.setDateInsert(new java.util.Date());
		current.setDateUpdate(new java.util.Date());
		current.setState(new java.math.BigDecimal( "1" ));
		return current;
	}
	@Test
	public void testJUnit4ModelUserData() { 
		ModelUserData current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		logger.info( "current isEmpty() : {}", current.isEmpty() );
		org.fugerit.java.core.function.SafeFunction.apply( () -> org.fugerit.java.core.io.ObjectIO.fullSerializationTest( current ) );
		Assert.assertNotNull( current );
	}

}
