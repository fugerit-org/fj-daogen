package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.IdFinderNG;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestTwoFieldKeyFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestTwoFieldKeyFinder extends IdFinderNG {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private ModelTestTwoFieldKey model;

	public void setModel( ModelTestTwoFieldKey model ) {
		this.model = model;
	}

	public ModelTestTwoFieldKey getModel() {
		return this.model;
	}

	/**
	 * Factory method to create a new finder 
	 *
	 * @param model		the model to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static TestTwoFieldKeyFinder newInstance( ModelTestTwoFieldKey model ) { 
		TestTwoFieldKeyFinder finder = new TestTwoFieldKeyFinder();
		// default id not available for this entity
		finder.setModel( model );
		return finder;
	}

}
