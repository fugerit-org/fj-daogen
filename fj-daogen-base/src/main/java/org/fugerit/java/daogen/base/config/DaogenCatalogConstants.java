package org.fugerit.java.daogen.base.config;

import org.fugerit.java.core.javagen.GeneratorNameHelper;

public class DaogenCatalogConstants {

	public static final String GEN_PROP_BASE_SRC_FOLDER = "base-src-folder";
	
	public static final String GEN_PROP_SRC_MAIN_JAVA =		 "src-main-java";
	public static final String GEN_PROP_SRC_MAIN_RESOURCES = "src-main-resources";
	public static final String GEN_PROP_SRC_TEST_JAVA = 	 "src-test-java";
	public static final String GEN_PROP_SRC_TEST_RESOURCES = "src-test-resources";
	
	public static final String GEN_PROP_GENERATOR_CATALOG = "generator-catalog";
	public static final String GEN_PROP_GENERATOR_CATALOG_DEFAULT = "cl://config/default-generator-catalog.xml";
			
	public static final String GEN_PROP_CLASS_CONFIG = "class-config";
	public static final String GEN_PROP_CLASS_CONFIG_DEFAULT = "cl://config/daogen_default_class_config.xml";
	
	public static final String GEN_PROP_TYPE_MAPPER = "type-mapper";
	public static final String GEN_PROP_TYPE_MAPPER_DEFAULT = "org.fugerit.java.daogen.base.config.DaogenTypeMapper";
	public static final String GEN_PROP_TYPE_MAP_CONFIG = "type-map-config";
	public static final String GEN_PROP_TYPE_MAP_CONFIG_DEFAULT = "cl://config/daogen_default_type_mapping.xml";	
	
	public static final String GEN_PROP_PACKAGE_MODEL =			"package-model";
	public static final String GEN_PROP_PACKAGE_HELPER =		"package-helper";
	public static final String GEN_PROP_PACKAGE_FACADE_DEF = 	"package-facade-def";
	public static final String GEN_PROP_PACKAGE_FACADE_DATA_IMPL = 	"package-facade-data-impl";
	public static final String GEN_PROP_PACKAGE_RSE = 	"package-rse";
	public static final String GEN_PROP_PACKAGE_STRUCT = 	"package-struct";
	public static final String GEN_PROP_PACKAGE_REST_LOAD = 	"package-rest-load";
	public static final String GEN_PROP_BASE_REST_SERVICE = 	"base-rest-service";
	public static final String GEN_PROP_STRUCT_PREFIX = 	"struct-prefix";
	public static final String GEN_PROP_STRUCT_PREFIX_DEFAULT = 	"OBJ_";
	
	public static final String GEN_PROP_PACKAGE_FACTORY_DEF = 			"factory-def";
	public static final String GEN_PROP_PACKAGE_FACTORY_DATA_IMPL = 	"factory-data-impl";
	
	public static final String GEN_PROP_DEFAULT_COLUMN_TIME_INSERT = "default-column-time-insert";
	public static final String GEN_PROP_DEFAULT_COLUMN_TIME_UPDATE = "default-column-time-update";
	
	public static final String GEN_PROP_DEFAULT_SEQUENCE = "default-sequence";
	
	
	
	public static final String PREFIX_MODEL = "Model";
	
	public static final String PREFIX_HELPER = "Helper";
	
	public static final String PREFIX_WRAPPER = "Wrapper";
	
	public static final String PREFIX_RESTLOAD = "Load";
	
	public static final String SUFFIX_FINDER = "Finder";
	
	public static final String SUFFIX_RSE = "RSE";
	
	public static final String PREFIX_FACADE_DEF = "Entity";
	public static final String SUFFIX_FACADE_DEF = "Facade";
	public static final String PREFIX_FACADE_IMPL_DATA = "Data";
	
	public static String modelName( DaogenCatalogEntity entity ) {
		return PREFIX_MODEL+entity.toClassName();
	}
	
	public static String helperName( DaogenCatalogEntity entity ) {
		return PREFIX_HELPER+entity.toClassName();
	}
	
	public static String structPrefix( DaogenCatalogConfig config) {
		return config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_STRUCT_PREFIX, DaogenCatalogConstants.GEN_PROP_STRUCT_PREFIX_DEFAULT );
	}

	public static String structName( DaogenCatalogConfig config, DaogenCatalogEntity entity ) {
		return GeneratorNameHelper.toClassName( structPrefix( config ) )+entity.toClassName();
	}
	
	public static String rseName( DaogenCatalogEntity entity ) {
		return entity.toClassName()+SUFFIX_RSE;
	}
	
	public static String wrapperName( DaogenCatalogEntity entity ) {
		return PREFIX_WRAPPER+entity.toClassName();
	}
	
	public static String finderlName( DaogenCatalogEntity entity ) {
		return entity.toClassName()+SUFFIX_FINDER;
	}
	
	public static String facadeDefName( DaogenCatalogEntity entity ) {
		return PREFIX_FACADE_DEF+entity.toClassName()+SUFFIX_FACADE_DEF;
	}
	
	public static String facadeImplDataName( DaogenCatalogEntity entity ) {
		return PREFIX_FACADE_IMPL_DATA+facadeDefName( entity );
	}
	
	public static String restLoadName( DaogenCatalogEntity entity ) {
		return PREFIX_RESTLOAD+entity.toClassName();
	}
	
}
