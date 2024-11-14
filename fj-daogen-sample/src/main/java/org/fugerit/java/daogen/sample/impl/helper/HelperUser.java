package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperUser, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperUser implements ModelUser {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * fields generated for relations 
	 */

	private java.util.List<ModelAddress> userAddresses;

	@Override
	public void setUserAddresses( java.util.List<ModelAddress> value ) {
		this.userAddresses = value;
	}

	@Override
	public java.util.List<ModelAddress> getUserAddresses() {
		return this.userAddresses;
	}

	/*
	 * fields generated for entity attributes 
	 */
	private java.math.BigDecimal id;

	@Override
	public void setId( java.math.BigDecimal value ) {
		this.id = value;
	}

	@Override
	public java.math.BigDecimal getId() {
		return this.id;
	}

	private java.lang.String username;

	@Override
	public void setUsername( java.lang.String value ) {
		this.username = value;
	}

	@Override
	public java.lang.String getUsername() {
		return this.username;
	}

	private java.lang.String password;

	@Override
	public void setPassword( java.lang.String value ) {
		this.password = value;
	}

	@Override
	public java.lang.String getPassword() {
		return this.password;
	}

	private java.time.LocalDateTime lastLogin;

	@Override
	public void setLastLogin( java.time.LocalDateTime value ) {
		this.lastLogin = value;
	}

	@Override
	public java.time.LocalDateTime getLastLogin() {
		return this.lastLogin;
	}

	private java.time.LocalDateTime dateInsert;

	@Override
	public void setDateInsert( java.time.LocalDateTime value ) {
		this.dateInsert = value;
	}

	@Override
	public java.time.LocalDateTime getDateInsert() {
		return this.dateInsert;
	}

	private java.time.LocalDateTime dateUpdate;

	@Override
	public void setDateUpdate( java.time.LocalDateTime value ) {
		this.dateUpdate = value;
	}

	@Override
	public java.time.LocalDateTime getDateUpdate() {
		return this.dateUpdate;
	}

	private java.math.BigDecimal state;

	@Override
	public void setState( java.math.BigDecimal value ) {
		this.state = value;
	}

	@Override
	public java.math.BigDecimal getState() {
		return this.state;
	}

	private java.math.BigDecimal stateVirtual;

	@Override
	public void setStateVirtual( java.math.BigDecimal value ) {
		this.stateVirtual = value;
	}

	@Override
	public java.math.BigDecimal getStateVirtual() {
		return this.stateVirtual;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append( this.getClass().getSimpleName() );
		buffer.append( "[id=" );
		buffer.append( this.getId() );
		buffer.append( ",username=" );
		buffer.append( this.getUsername() );
		buffer.append( ",password=" );
		buffer.append( this.getPassword() );
		buffer.append( ",lastLogin=" );
		buffer.append( this.getLastLogin() );
		buffer.append( ",dateInsert=" );
		buffer.append( this.getDateInsert() );
		buffer.append( ",dateUpdate=" );
		buffer.append( this.getDateUpdate() );
		buffer.append( ",state=" );
		buffer.append( this.getState() );
		buffer.append( ",stateVirtual=" );
		buffer.append( this.getStateVirtual() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getId() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getUsername() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getPassword() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getLastLogin() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateInsert() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateUpdate() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getState() ) );
	}

}
