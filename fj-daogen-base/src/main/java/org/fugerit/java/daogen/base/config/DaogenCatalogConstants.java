package org.fugerit.java.daogen.base.config;

public class DaogenCatalogConstants {

	public static final String GEN_PROP_BASE_SRC_FOLDER = "base-src-folder";
	
	public static final String GEN_PROP_SRC_MAIN_JAVA =		 "src-main-java";
	public static final String GEN_PROP_SRC_MAIN_RESOURCES = "src-test-java";
	public static final String GEN_PROP_SRC_TEST_JAVA = 	 "src-main-resources";
	public static final String GEN_PROP_SRC_TEST_RESOURCES = "src-test-resources";
	
	public static final String GEN_PROP_PACKAGE_MODEL =		 "package-model";
	
	
	public static final String PREFIX_MODEL = "Model";
	
	public static String modelName( DaogenCatalogEntity entity ) {
		return PREFIX_MODEL+entity.toClassName();
	}
	
}
