package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperAddress, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperAddress implements ModelAddress {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * fields generated for relations 
	 */

	private ModelUser user;

	@Override
	public void setUser( ModelUser value ) {
		this.user = value;
	}

	@Override
	public ModelUser getUser() {
		return this.user;
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

	private java.math.BigDecimal idUser;

	@Override
	public void setIdUser( java.math.BigDecimal value ) {
		this.idUser = value;
	}

	@Override
	public java.math.BigDecimal getIdUser() {
		return this.idUser;
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

	private java.lang.String info;

	@Override
	public void setInfo( java.lang.String value ) {
		this.info = value;
	}

	@Override
	public java.lang.String getInfo() {
		return this.info;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append( this.getClass().getSimpleName() );
		buffer.append( "[id=" );
		buffer.append( this.getId() );
		buffer.append( ",idUser=" );
		buffer.append( this.getIdUser() );
		buffer.append( ",dateInsert=" );
		buffer.append( this.getDateInsert() );
		buffer.append( ",dateUpdate=" );
		buffer.append( this.getDateUpdate() );
		buffer.append( ",info=" );
		buffer.append( this.getInfo() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getId() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getIdUser() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateInsert() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateUpdate() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getInfo() ) );
	}

}
