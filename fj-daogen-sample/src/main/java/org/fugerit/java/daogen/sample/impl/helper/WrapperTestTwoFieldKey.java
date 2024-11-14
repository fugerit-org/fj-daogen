package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapperNG;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperTestTwoFieldKey, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperTestTwoFieldKey extends BasicWrapperNG<ModelTestTwoFieldKey> implements ModelTestTwoFieldKey {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	public WrapperTestTwoFieldKey( ModelTestTwoFieldKey wrapped ) {
		super( wrapped );
	}

	public ModelTestTwoFieldKey unwrap( WrapperTestTwoFieldKey wrapper ) {
		ModelTestTwoFieldKey res = wrapper;
		while ( res instanceof WrapperTestTwoFieldKey ) { 
			res = ((WrapperTestTwoFieldKey)res).unwrapModel();
		}
		return res;
	}

	/*
	 * fields generated for entity attributes 
	 */
	@Override
	public void setIdOne( java.math.BigDecimal value ) {
		this.unwrapModel().setIdOne( value );
	}

	@Override
	public java.math.BigDecimal getIdOne() {
		return this.unwrapModel().getIdOne();
	}

	@Override
	public void setIdTwo( java.math.BigDecimal value ) {
		this.unwrapModel().setIdTwo( value );
	}

	@Override
	public java.math.BigDecimal getIdTwo() {
		return this.unwrapModel().getIdTwo();
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
