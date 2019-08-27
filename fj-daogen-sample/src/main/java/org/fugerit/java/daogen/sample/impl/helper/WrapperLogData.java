package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.core.db.daogen.BasicWrapper;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperLogData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperLogData extends BasicWrapper<ModelLogData> implements ModelLogData {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 827293319822L;

	public WrapperLogData( ModelLogData wrapped ) {
		super( wrapped );
	}

	public ModelLogData unwrap( WrapperLogData wrapper ) {
		ModelLogData res = wrapper;
		while ( res != null && res instanceof WrapperLogData ) { 
			res = ((WrapperLogData)res).unwrapModel();
		}
		return res;
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
	public void setLogTime( java.util.Date value ) {
		this.unwrapModel().setLogTime( value );
	}

	@Override
	public java.util.Date getLogTime() {
		return this.unwrapModel().getLogTime();
	}

	@Override
	public void setInfo( java.lang.String value ) {
		this.unwrapModel().setInfo( value );
	}

	@Override
	public java.lang.String getInfo() {
		return this.unwrapModel().getInfo();
	}

}
