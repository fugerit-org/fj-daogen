package org.fugerit.java.daogen.base.config;

public class DaogenHelperGenerator {

	private DaogenHelperGenerator() {}
	
	public static String toHelperSourceFolder( DaogenCatalogConfig config ) {
		return config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_SRC_HELPERS, DaogenCatalogConstants.GEN_PROP_SRC_HELPERS_MAIN );
	}
	
	public static String toHelperClassName( String className ) {
		return className+"Helper";
	}
	
}
