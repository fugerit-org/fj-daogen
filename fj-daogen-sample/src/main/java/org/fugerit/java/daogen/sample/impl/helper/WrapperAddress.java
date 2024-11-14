package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapperNG;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperAddress, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperAddress extends BasicWrapperNG<ModelAddress> implements ModelAddress {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	public WrapperAddress( ModelAddress wrapped ) {
		super( wrapped );
	}

	public ModelAddress unwrap( WrapperAddress wrapper ) {
		ModelAddress res = wrapper;
		while ( res instanceof WrapperAddress ) { 
			res = ((WrapperAddress)res).unwrapModel();
		}
		return res;
	}

	/*
	 * fields generated for relations 
	 */

	@Override
	public void setUser( ModelUser value ) {
		this.unwrapModel().setUser( value );
	}

	@Override
	public ModelUser getUser() {
		return this.unwrapModel().getUser();
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
	public void setIdUser( java.math.BigDecimal value ) {
		this.unwrapModel().setIdUser( value );
	}

	@Override
	public java.math.BigDecimal getIdUser() {
		return this.unwrapModel().getIdUser();
	}

	@Override
	public void setDateInsert( java.time.LocalDateTime value ) {
		this.unwrapModel().setDateInsert( value );
	}

	@Override
	public java.time.LocalDateTime getDateInsert() {
		return this.unwrapModel().getDateInsert();
	}

	@Override
	public void setDateUpdate( java.time.LocalDateTime value ) {
		this.unwrapModel().setDateUpdate( value );
	}

	@Override
	public java.time.LocalDateTime getDateUpdate() {
		return this.unwrapModel().getDateUpdate();
	}

	@Override
	public void setInfo( java.lang.String value ) {
		this.unwrapModel().setInfo( value );
	}

	@Override
	public java.lang.String getInfo() {
		return this.unwrapModel().getInfo();
	}

	@Override
	public boolean isEmpty() {
		return this.unwrapModel().isEmpty();
	}

}
