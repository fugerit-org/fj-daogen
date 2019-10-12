package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapper;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperUserData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperUserData extends BasicWrapper<ModelUserData> implements ModelUserData {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 647032161858L;

	public WrapperUserData( ModelUserData wrapped ) {
		super( wrapped );
	}

	public ModelUserData unwrap( WrapperUserData wrapper ) {
		ModelUserData res = wrapper;
		while ( res != null && res instanceof WrapperUserData ) { 
			res = ((WrapperUserData)res).unwrapModel();
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
	public void setUsername( java.lang.String value ) {
		this.unwrapModel().setUsername( value );
	}

	@Override
	public java.lang.String getUsername() {
		return this.unwrapModel().getUsername();
	}

	@Override
	public void setPassword( java.lang.String value ) {
		this.unwrapModel().setPassword( value );
	}

	@Override
	public java.lang.String getPassword() {
		return this.unwrapModel().getPassword();
	}

	@Override
	public void setLastLogin( java.util.Date value ) {
		this.unwrapModel().setLastLogin( value );
	}

	@Override
	public java.util.Date getLastLogin() {
		return this.unwrapModel().getLastLogin();
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
	public void setState( java.math.BigDecimal value ) {
		this.unwrapModel().setState( value );
	}

	@Override
	public java.math.BigDecimal getState() {
		return this.unwrapModel().getState();
	}

}
