package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;

public class FacadeDefRealGenerator extends FacadeDefGenerator {

	public static final String KEY = FacadeDefRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public FacadeDefRealGenerator() {
		this.setMode( MODE_REAL );
	}

}
