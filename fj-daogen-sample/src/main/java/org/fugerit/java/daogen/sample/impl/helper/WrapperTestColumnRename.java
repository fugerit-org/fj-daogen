package org.fugerit.java.daogen.sample.impl.helper;

import org.fugerit.java.core.db.daogen.BasicWrapperNG;
import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * WrapperTestColumnRename, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class WrapperTestColumnRename extends BasicWrapperNG<ModelTestColumnRename> implements ModelTestColumnRename {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	public WrapperTestColumnRename( ModelTestColumnRename wrapped ) {
		super( wrapped );
	}

	public ModelTestColumnRename unwrap( WrapperTestColumnRename wrapper ) {
		ModelTestColumnRename res = wrapper;
		while ( res instanceof WrapperTestColumnRename ) { 
			res = ((WrapperTestColumnRename)res).unwrapModel();
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
	public void setRenamedFieldOne( java.lang.String value ) {
		this.unwrapModel().setRenamedFieldOne( value );
	}

	@Override
	public java.lang.String getRenamedFieldOne() {
		return this.unwrapModel().getRenamedFieldOne();
	}

	@Override
	public boolean isEmpty() {
		return this.unwrapModel().isEmpty();
	}

}
