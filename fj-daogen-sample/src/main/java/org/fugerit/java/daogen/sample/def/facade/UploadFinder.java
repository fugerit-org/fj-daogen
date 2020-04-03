package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.BaseIdFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UploadFinder, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UploadFinder extends BaseIdFinder {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 596269532220L;

	private ModelUpload model;

	public void setModel( ModelUpload model ) {
		this.model = model;
	}

	public ModelUpload getModel() {
		return this.model;
	}

	/**
	 *Factory method to create a new finder 
	 *
	 * @param id		id to wrap in the finder
	 *
	 * @return	the finder
	 */
	public static UploadFinder newInstance( java.math.BigDecimal id ) { 
		UploadFinder finder = new UploadFinder();
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
	public static UploadFinder newInstance( ModelUpload model ) { 
		UploadFinder finder = new UploadFinder();
		finder.setId( model.getId() );
		finder.setModel( model );
		return finder;
	}

}
