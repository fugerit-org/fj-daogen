package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperTestTwoFieldKey, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperTestTwoFieldKey implements ModelTestTwoFieldKey {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * fields generated for entity attributes 
	 */
	private java.math.BigDecimal idOne;

	@Override
	public void setIdOne( java.math.BigDecimal value ) {
		this.idOne = value;
	}

	@Override
	public java.math.BigDecimal getIdOne() {
		return this.idOne;
	}

	private java.math.BigDecimal idTwo;

	@Override
	public void setIdTwo( java.math.BigDecimal value ) {
		this.idTwo = value;
	}

	@Override
	public java.math.BigDecimal getIdTwo() {
		return this.idTwo;
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
		buffer.append( "[idOne=" );
		buffer.append( this.getIdOne() );
		buffer.append( ",idTwo=" );
		buffer.append( this.getIdTwo() );
		buffer.append( ",info=" );
		buffer.append( this.getInfo() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getIdOne() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getIdTwo() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getInfo() ) );
	}

}
