package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperUpload, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperUpload implements ModelUpload {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

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

	private org.fugerit.java.core.db.daogen.ByteArrayDataHandler content;

	@Override
	public void setContent( org.fugerit.java.core.db.daogen.ByteArrayDataHandler value ) {
		this.content = value;
	}

	@Override
	public org.fugerit.java.core.db.daogen.ByteArrayDataHandler getContent() {
		return this.content;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append( this.getClass().getSimpleName() );
		buffer.append( "[id=" );
		buffer.append( this.getId() );
		buffer.append( ",dateInsert=" );
		buffer.append( this.getDateInsert() );
		buffer.append( ",dateUpdate=" );
		buffer.append( this.getDateUpdate() );
		buffer.append( ",content=" );
		buffer.append( this.getContent() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getId() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateInsert() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getDateUpdate() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getContent() ) );
	}

}
