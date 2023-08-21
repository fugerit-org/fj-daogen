package org.fugerit.java.daogen.base.gen;

public class DocOpenAPIV3RestGenerator extends DocOpenAPIBaseRestGenerator {

	public DocOpenAPIV3RestGenerator() {
		super(  VERSION_V3 );
	}

	public static final String KEY = "DocOpenAPIRestGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
}
