package org.fugerit.java.daogen.sample.def.facade;

import java.math.BigDecimal;
import org.fugerit.java.core.db.daogen.BaseIdFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserFinder extends BaseIdFinder {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 657787973072L;

	private ModelUser model;

	public void setModel( ModelUser model ) {
		this.model = model;
	}

	public ModelUser getModel() {
		return this.model;
	}

	/**
	 * Factory method per un nuovo finder 
	 *
	 * @param id		id dell'oggetto da cercare
	 *
	 * @return	il finder
	 */
	public static UserFinder newInstance( BigDecimal id ) { 
		UserFinder finder = new UserFinder();
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
	public static UserFinder newInstance( ModelUser model ) { 
		UserFinder finder = new UserFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
