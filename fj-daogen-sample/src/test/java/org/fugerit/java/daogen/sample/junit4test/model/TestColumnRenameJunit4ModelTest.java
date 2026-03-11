package org.fugerit.java.daogen.sample.junit4test.model;

import org.fugerit.java.daogen.sample.def.facade.TestColumnRenameFinder;
import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestColumnRename;
import org.fugerit.java.daogen.sample.impl.helper.WrapperTestColumnRename;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestColumnRenameJunit4ModelTest, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestColumnRenameJunit4ModelTest {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final Logger logger = LoggerFactory.getLogger( TestColumnRenameJunit4ModelTest.class );

	public void printAll( ModelTestColumnRename current ) { 
		logger.info( "ID-> {}", current.getId() );
		logger.info( "RENAMED_FIELD_ONE-> {}", current.getRenamedFieldOne() );
	}

	public ModelTestColumnRename newInstance() { 
		ModelTestColumnRename model = new HelperTestColumnRename();
		WrapperTestColumnRename current = new WrapperTestColumnRename( model );
		Assert.assertTrue( current.isEmpty() );
		current.setId(new java.math.BigDecimal( "1" ));
		Assert.assertFalse( current.isEmpty() );
		current.setRenamedFieldOne("1");
		Assert.assertFalse( current.isEmpty() );
		logger.info( "unwrap :  {}", current.unwrap( current ) );
		return current;
	}

	@Test
	public void testJUnit4ModelTestColumnRename() { 
		ModelTestColumnRename current = this.newInstance();
		this.printAll( current );
		logger.info( "current toString() : {}", current );
		Assert.assertNotNull( current );
		TestColumnRenameFinder finder1 = new TestColumnRenameFinder();
		finder1.setModel( current );
		logger.info( "finder1.getModel() -> {}", finder1.getModel() );
		finder1.setId( current.getId() );
		Assert.assertEquals( current.getId(), finder1.getId() );
		Assert.assertNotNull( TestColumnRenameFinder.newInstance( current.getId() ) );
		Assert.assertNotNull( TestColumnRenameFinder.newInstance( current ) );
		Assert.assertNotNull( finder1 );
	}

}
