package org.fugerit.java.daogen.sample.def.facade;

import java.math.BigDecimal;
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

	private static final long serialVersionUID = 566741358487L;

	private ModelAddress model;

	public void setModel( ModelAddress model ) {
		this.model = model;
	}

	public ModelAddress getModel() {
		return this.model;
	}

	/**
	 * Factory method per un nuovo finder 
	 *
	 * @param id		id dell'oggetto da cercare
	 *
	 * @return	il finder
	 */
	public static AddressFinder newInstance( BigDecimal id ) { 
		AddressFinder finder = new AddressFinder();
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
	public static AddressFinder newInstance( ModelAddress model ) { 
		AddressFinder finder = new AddressFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
