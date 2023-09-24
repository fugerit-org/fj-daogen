package org.fugerit.java.daogen.base.gen.helper;

import java.util.Arrays;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;

public class RestLoadRealGenerator extends BaseRestLoadRealGenerator {

	public static final String KEY = RestLoadRealGenerator.class.getSimpleName();
	
	private static final String[] IMPORTS = { "javax.ws.rs.Path" };

	public RestLoadRealGenerator() {
		super( KEY, Arrays.asList( IMPORTS ), DaogenCatalogConstants.GEN_PROP_PACKAGE_REST_LOAD, null, "@Path(\"/{0}/load\")" );
	}

}
