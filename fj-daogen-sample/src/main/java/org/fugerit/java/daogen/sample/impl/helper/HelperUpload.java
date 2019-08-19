package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicHelper;
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
public class HelperUpload extends BasicHelper implements ModelUpload {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 618353454393L;

	private java.math.BigDecimal id;

	@Override
	public void setId( java.math.BigDecimal value ) {
		this.id = value;
	}

	@Override
	public java.math.BigDecimal getId() {
		return this.id;
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

}
