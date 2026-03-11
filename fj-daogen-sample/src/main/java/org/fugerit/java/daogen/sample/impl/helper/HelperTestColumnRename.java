package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperTestColumnRename, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperTestColumnRename implements ModelTestColumnRename {

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

	private java.lang.String renamedFieldOne;

	@Override
	public void setRenamedFieldOne( java.lang.String value ) {
		this.renamedFieldOne = value;
	}

	@Override
	public java.lang.String getRenamedFieldOne() {
		return this.renamedFieldOne;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append( this.getClass().getSimpleName() );
		buffer.append( "[id=" );
		buffer.append( this.getId() );
		buffer.append( ",renamedFieldOne=" );
		buffer.append( this.getRenamedFieldOne() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getId() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getRenamedFieldOne() ) );
	}

}
