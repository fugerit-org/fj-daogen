package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FactoryDataImplGenerator;

public class FactoryDataImplRealGenerator extends FactoryDataImplGenerator {

	public static final String KEY = FactoryDataImplRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FactoryDataImplRealGenerator() {
		this.setMode( MODE_REAL );
	}

}
