package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FacadeImplDataGenerator;

public class FacadeImplDataRealGenerator extends FacadeImplDataGenerator {

	public static final String KEY = FacadeImplDataRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FacadeImplDataRealGenerator() {
		this.setMode( MODE_REAL );
	}
}
