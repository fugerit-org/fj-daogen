package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;
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
	public ModelUpload newInstance() { 
		HelperUpload current = new HelperUpload();
		current.setId(new java.math.BigDecimal( "1" ));
		current.setDateInsert(new java.util.Date());
		current.setDateUpdate(new java.util.Date());
		current.setContent(null);
		return current;
	}
	public void printAll( ModelUpload current ) { 
		 logger.info( "ID-> {}", current.getId() );
		 logger.info( "DATE_INSERT-> {}", current.getDateInsert() );
		 logger.info( "DATE_UPDATE-> {}", current.getDateUpdate() );
		 logger.info( "CONTENT-> {}", current.getContent() );
	}

	@Test
	public void testModelUpload() { 
		ModelUpload current = this.newInstance();
		this.printAll( current );
		Assert.assertNotNull( current );
	}

}