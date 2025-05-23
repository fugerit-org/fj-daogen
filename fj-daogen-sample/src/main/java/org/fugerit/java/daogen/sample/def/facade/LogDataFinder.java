package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.IdFinderNG;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LogDataFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class LogDataFinder extends IdFinderNG {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private ModelLogData model;

	public void setModel( ModelLogData model ) {
		this.model = model;
	}

	public ModelLogData getModel() {
		return this.model;
	}

	/**
	 *Factory method to create a new finder 
	 *
	 * @param id		id to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static LogDataFinder newInstance( java.math.BigDecimal id ) { 
		LogDataFinder finder = new LogDataFinder();
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
	public static LogDataFinder newInstance( ModelLogData model ) { 
		LogDataFinder finder = new LogDataFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
