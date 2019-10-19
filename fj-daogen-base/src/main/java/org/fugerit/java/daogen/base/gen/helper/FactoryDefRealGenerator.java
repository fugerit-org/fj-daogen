package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FactoryDefGenerator;

public class FactoryDefRealGenerator extends FactoryDefGenerator {

	public static final String KEY = "FactoryDefGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FactoryDefRealGenerator() {
		super();
		this.setMode( MODE_REAL );
	}

}
