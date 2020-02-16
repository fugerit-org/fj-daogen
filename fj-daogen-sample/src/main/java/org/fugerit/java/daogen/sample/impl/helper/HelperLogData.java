package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicHelper;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * HelperLogData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class HelperLogData extends BasicHelper implements ModelLogData {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 412994167955L;

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

	private java.util.Date logTime;

	@Override
	public void setLogTime( java.util.Date value ) {
		this.logTime = value;
	}

	@Override
	public java.util.Date getLogTime() {
		return this.logTime;
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
		buffer.append( ",logTime=" );
		buffer.append( this.getLogTime() );
		buffer.append( ",info=" );
		buffer.append( this.getInfo() );
		buffer.append( "]" );
		return buffer.toString();
	}

	@Override
	public boolean isEmpty() {
		return  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getId() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getLogTime() ) )
		 &&  ( org.fugerit.java.core.lang.compare.CheckEmptyHelper.isEmpty( this.getInfo() ) );
	}

}
