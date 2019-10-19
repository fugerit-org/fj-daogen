package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FactoryDataImplGenerator;

public class FactoryDataImplHelperGenerator extends FactoryDataImplGenerator {

	public static final String KEY = FactoryDataImplHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FactoryDataImplHelperGenerator() {
		this.setMode( MODE_HELPER );
	}

}
