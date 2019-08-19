package org.fugerit.java.daogen.sample.def.facade;

import java.math.BigDecimal;
import org.fugerit.java.core.db.daogen.BaseIdFinder;
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
public class LogDataFinder extends BaseIdFinder {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 329810489228L;

	private ModelLogData model;

	public void setModel( ModelLogData model ) {
		this.model = model;
	}

	public ModelLogData getModel() {
		return this.model;
	}

	/**
	 * Factory method per un nuovo finder 
	 *
	 * @param id		id dell'oggetto da cercare
	 *
	 * @return	il finder
	 */
	public static LogDataFinder newInstance( BigDecimal id ) { 
		LogDataFinder finder = new LogDataFinder();
		finder.setId( id );
		return finder;
	}

	/**
	 * Factory method per un nuovo finder 
	 *
	 * @param model		l'oggetto di modello
	 *
	 * @return	il finder
	 */
	public static LogDataFinder newInstance( ModelLogData model ) { 
		LogDataFinder finder = new LogDataFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
