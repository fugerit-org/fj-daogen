package org.fugerit.java.daogen.base.gen;

public class DocOpenAPIRestGenerator extends DocOpenAPIBaseRestGenerator {

	public DocOpenAPIRestGenerator() {
		super(VERSION_V2);
	}

	public static final String KEY = "DocOpenAPIRestGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

}
