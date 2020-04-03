package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.BaseIdFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserDataFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserDataFinder extends BaseIdFinder {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 338411557691L;

	private ModelUserData model;

	public void setModel( ModelUserData model ) {
		this.model = model;
	}

	public ModelUserData getModel() {
		return this.model;
	}

	/**
	 *Factory method to create a new finder 
	 *
	 * @param id		id to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static UserDataFinder newInstance( java.math.BigDecimal id ) { 
		UserDataFinder finder = new UserDataFinder();
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
	public static UserDataFinder newInstance( ModelUserData model ) { 
		UserDataFinder finder = new UserDataFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
