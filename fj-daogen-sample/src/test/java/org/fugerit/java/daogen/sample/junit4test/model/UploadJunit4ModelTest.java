package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.facade.UploadFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUpload;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UploadJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UploadJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( UploadJunit4ModelTest.class );

	public void printAll( ModelUpload current ) { 
		logger.info( "ID-> {}", current.getId() );
		logger.info( "DATE_INSERT-> {}", current.getDateInsert() );
		logger.info( "DATE_UPDATE-> {}", current.getDateUpdate() );
		logger.info( "CONTENT-> {}", current.getContent() );
	}

	public ModelUpload newInstance() { 
		WrapperUpload current = new WrapperUpload( new HelperUpload() );
		Assert.assertTrue( current.isEmpty() );
		current.setId(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setDateInsert(null);
		Assert.assertFalse( current.isEmpty() );
		current.setDateUpdate(null);
		Assert.assertFalse( current.isEmpty() );
		current.setContent(null);
		Assert.assertFalse( current.isEmpty() );
		logger.info( "unwrap :  {}", current.unwrap( current ) );
		return current;
	}

	@Test
	public void testJUnit4ModelUpload() { 
		ModelUpload current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		Assert.assertNotNull( current );
		UploadFinder finder1 = new UploadFinder();
		finder1.setModel( current );
		logger.info( "finder1.getModel() -> {}", finder1.getModel() );
		finder1.setId( current.getId() );
		Assert.assertEquals( current.getId(), finder1.getId() );
		Assert.assertNotNull( UploadFinder.newInstance( current.getId() ) );
		Assert.assertNotNull( UploadFinder.newInstance( current ) );
		Assert.assertNotNull( finder1 );
	}

}
