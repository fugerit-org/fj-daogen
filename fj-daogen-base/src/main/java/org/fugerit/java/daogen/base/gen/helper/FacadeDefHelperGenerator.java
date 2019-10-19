package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;

public class FacadeDefHelperGenerator extends FacadeDefGenerator {

	public static final String KEY = FacadeDefHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public FacadeDefHelperGenerator() {
		this.setMode( MODE_HELPER );
	}

}
