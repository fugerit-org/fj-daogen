package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapper;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperUser, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperUser extends BasicWrapper<ModelUser> implements ModelUser {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 117929240875L;

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

	public WrapperUser( ModelUser wrapped ) {
		super( wrapped );
	}

	public ModelUser unwrap( WrapperUser wrapper ) {
		ModelUser res = wrapper;
		while ( res instanceof WrapperUser ) { 
			res = ((WrapperUser)res).unwrapModel();
		}
		return res;
	}

	/*
	 * fields generated for relations 
	 */

	@Override
	public void setUserAddresses( java.util.List<ModelAddress> value ) {
		this.unwrapModel().setUserAddresses( value );
	}

	@Override
	public java.util.List<ModelAddress> getUserAddresses() {
		return this.unwrapModel().getUserAddresses();
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

	@Override
	public void setStateVirtual( java.math.BigDecimal value ) {
		this.unwrapModel().setStateVirtual( value );
	}

	@Override
	public java.math.BigDecimal getStateVirtual() {
		return this.unwrapModel().getStateVirtual();
	}

	@Override
	public boolean isEmpty() {
		return this.unwrapModel().isEmpty();
	}

}
