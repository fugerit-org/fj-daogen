package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.IdFinderNG;
import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestColumnRenameFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestColumnRenameFinder extends IdFinderNG {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private ModelTestColumnRename model;

	public void setModel( ModelTestColumnRename model ) {
		this.model = model;
	}

	public ModelTestColumnRename getModel() {
		return this.model;
	}

	/**
	 *Factory method to create a new finder 
	 *
	 * @param id		id to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static TestColumnRenameFinder newInstance( java.math.BigDecimal id ) { 
		TestColumnRenameFinder finder = new TestColumnRenameFinder();
		finder.setId( id );
		return finder;
	}

	/**
	 * Factory method to create a new finder 
	 *
	 * @param model		the model to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static TestColumnRenameFinder newInstance( ModelTestColumnRename model ) { 
		TestColumnRenameFinder finder = new TestColumnRenameFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
