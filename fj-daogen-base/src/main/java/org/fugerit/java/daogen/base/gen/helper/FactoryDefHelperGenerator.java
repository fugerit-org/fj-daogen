package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.gen.FactoryDefGenerator;

public class FactoryDefHelperGenerator extends FactoryDefGenerator {

	public static final String KEY = FactoryDefHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public FactoryDefHelperGenerator() {
		super();
		this.setMode( MODE_HELPER );
	}

}
