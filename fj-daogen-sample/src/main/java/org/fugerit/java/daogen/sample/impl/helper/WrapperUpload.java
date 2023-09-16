package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapper;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperUpload, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperUpload extends BasicWrapper<ModelUpload> implements ModelUpload {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 714845245143L;

	public WrapperUpload( ModelUpload wrapped ) {
		super( wrapped );
	}

	public ModelUpload unwrap( WrapperUpload wrapper ) {
		ModelUpload res = wrapper;
		while ( res instanceof WrapperUpload ) { 
			res = ((WrapperUpload)res).unwrapModel();
		}
		return res;
	}

	/*
	 * fields generated for entity attributes 
	 */
	@Override
	public void setId( java.math.BigDecimal value ) {
		this.unwrapModel().setId( value );
	}

	@Override
	public java.math.BigDecimal getId() {
		return this.unwrapModel().getId();
	}

	@Override
	public void setDateInsert( java.util.Date value ) {
		this.unwrapModel().setDateInsert( value );
	}

	@Override
	public java.util.Date getDateInsert() {
		return this.unwrapModel().getDateInsert();
	}

	@Override
	public void setDateUpdate( java.util.Date value ) {
		this.unwrapModel().setDateUpdate( value );
	}

	@Override
	public java.util.Date getDateUpdate() {
		return this.unwrapModel().getDateUpdate();
	}

	@Override
	public void setContent( org.fugerit.java.core.db.daogen.ByteArrayDataHandler value ) {
		this.unwrapModel().setContent( value );
	}

	@Override
	public org.fugerit.java.core.db.daogen.ByteArrayDataHandler getContent() {
		return this.unwrapModel().getContent();
	}

	@Override
	public boolean isEmpty() {
		return this.unwrapModel().isEmpty();
	}

}
