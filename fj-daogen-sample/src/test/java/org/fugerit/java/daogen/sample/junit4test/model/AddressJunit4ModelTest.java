package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.impl.helper.HelperAddress;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * AddressJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class AddressJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( AddressJunit4ModelTest.class );
	public ModelAddress newInstance() { 
		HelperAddress current = new HelperAddress();
		current.setId(new java.math.BigDecimal( "1" ));
		current.setIdUser(new java.math.BigDecimal( "1" ));
		current.setDateInsert(new java.util.Date());
		current.setDateUpdate(new java.util.Date());
		current.setInfo("1");
		return current;
	}
	public void printAll( ModelAddress current ) { 
		 logger.info( "ID-> {}", current.getId() );
		 logger.info( "ID_USER-> {}", current.getIdUser() );
		 logger.info( "DATE_INSERT-> {}", current.getDateInsert() );
		 logger.info( "DATE_UPDATE-> {}", current.getDateUpdate() );
		 logger.info( "INFO-> {}", current.getInfo() );
	}

	@Test
	public void testModelAddress() { 
		ModelAddress current = this.newInstance();
		this.printAll( current );
		Assert.assertNotNull( current );
	}

}