package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.facade.UserFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUser;
import org.fugerit.java.daogen.sample.impl.helper.HelperUser;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUser;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( UserJunit4ModelTest.class );

	public void printAll( ModelUser current ) { 
		logger.info( "ID-> {}", current.getId() );
		logger.info( "USERNAME-> {}", current.getUsername() );
		logger.info( "PASSWORD-> {}", current.getPassword() );
		logger.info( "LAST_LOGIN-> {}", current.getLastLogin() );
		logger.info( "DATE_INSERT-> {}", current.getDateInsert() );
		logger.info( "DATE_UPDATE-> {}", current.getDateUpdate() );
		logger.info( "STATE-> {}", current.getState() );
		logger.info( "relation : userToAddress-> {}", current.getUserAddresses() );
	}

	public ModelUser newInstance() { 
		WrapperUser current = new WrapperUser( new HelperUser() );
		Assert.assertTrue( current.isEmpty() );
		current.setId(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setUsername("1");
		Assert.assertFalse( current.isEmpty() );
		current.setPassword("1");
		Assert.assertFalse( current.isEmpty() );
		current.setLastLogin(null);
		Assert.assertFalse( current.isEmpty() );
		current.setDateInsert(null);
		Assert.assertFalse( current.isEmpty() );
		current.setDateUpdate(null);
		Assert.assertFalse( current.isEmpty() );
		current.setState(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setStateVirtual(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setUserAddresses( new java.util.ArrayList<org.fugerit.java.daogen.sample.def.model.ModelAddress>() );
		logger.info( "unwrap :  {}", current.unwrap( current ) );
		return current;
	}

	@Test
	public void testJUnit4ModelUser() { 
		ModelUser current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		Assert.assertNotNull( current );
		UserFinder finder1 = new UserFinder();
		finder1.setModel( current );
		logger.info( "finder1.getModel() -> {}", finder1.getModel() );
		finder1.setId( current.getId() );
		Assert.assertEquals( current.getId(), finder1.getId() );
		Assert.assertNotNull( UserFinder.newInstance( current.getId() ) );
		Assert.assertNotNull( UserFinder.newInstance( current ) );
		Assert.assertNotNull( finder1 );
	}

}
