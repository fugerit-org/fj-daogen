package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FacadeImplDataGenerator;

public class FacadeImplDataHelperGenerator extends FacadeImplDataGenerator {

	public static final String KEY = FacadeImplDataHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FacadeImplDataHelperGenerator() {
		this.setMode( MODE_HELPER );
	}
	
}
