package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicHelper;
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
public class HelperAddress extends BasicHelper implements ModelAddress {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 102956265348L;

	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
		// this class is conditionally serializable, depending on contained object
		// special situation can be handled using this method in future
		out.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
		// this class is conditionally serializable, depending on contained object
		// special situation can be handled using this method in future
		in.defaultReadObject();
	}

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
