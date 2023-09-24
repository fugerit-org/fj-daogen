package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.BaseIdFinder;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * AddressFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class AddressFinder extends BaseIdFinder {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 991425775053L;

	private ModelAddress model;

	public void setModel( ModelAddress model ) {
		this.model = model;
	}

	public ModelAddress getModel() {
		return this.model;
	}

	/**
	 *Factory method to create a new finder 
	 *
	 * @param id		id to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static AddressFinder newInstance( java.math.BigDecimal id ) { 
		AddressFinder finder = new AddressFinder();
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
	public static AddressFinder newInstance( ModelAddress model ) { 
		AddressFinder finder = new AddressFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
