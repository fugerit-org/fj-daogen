package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicHelper;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperUserData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperUserData extends BasicHelper implements ModelUserData {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 321604260603L;

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

	private java.util.Date lastLogin;

	@Override
	public void setLastLogin( java.util.Date value ) {
		this.lastLogin = value;
	}

	@Override
	public java.util.Date getLastLogin() {
		return this.lastLogin;
	}

	private java.util.Date dateInsert;

	@Override
	public void setDateInsert( java.util.Date value ) {
		this.dateInsert = value;
	}

	@Override
	public java.util.Date getDateInsert() {
		return this.dateInsert;
	}

	private java.util.Date dateUpdate;

	@Override
	public void setDateUpdate( java.util.Date value ) {
		this.dateUpdate = value;
	}

	@Override
	public java.util.Date getDateUpdate() {
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
