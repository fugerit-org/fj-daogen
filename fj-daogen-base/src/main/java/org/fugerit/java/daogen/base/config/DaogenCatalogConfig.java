package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.Properties;

import org.fugerit.java.core.cfg.xml.CustomListCatalogConfig;
import org.fugerit.java.core.io.helper.StreamHelper;
import org.fugerit.java.core.lang.helpers.ClassHelper;

public class DaogenCatalogConfig extends CustomListCatalogConfig<DaogenCatalogField, DaogenCatalogEntity> {

	public static final String ATT_DAOGEN_ROOT = "daogen-config";
	
	public static final String ATT_DAOGEN_ENTITY = "entity";
	
	public static final String ATT_DAOGEN_FIELD = "field";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491328999494021347L;

	public DaogenCatalogConfig() {
		super( ATT_DAOGEN_ENTITY, ATT_DAOGEN_FIELD );
		this.getGeneralProps().setProperty( ATT_TYPE , DaogenCatalogField.class.getName() );
		this.getGeneralProps().setProperty( ATT_LIST_TYPE , DaogenCatalogEntity.class.getName() );
		this.classConfig = new Properties();
	}
	
	public String getGeneralProp( String key ) {
		return this.getGeneralProps().getProperty( key );
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input ) throws Exception {
		DaogenCatalogConfig config = new DaogenCatalogConfig();
		load( input , config );
		// class config
		String classConfigPath = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_CLASS_CONFIG, DaogenCatalogConstants.GEN_PROP_CLASS_CONFIG_DEFAULT );
		try ( InputStream is = StreamHelper.resolveStream( classConfigPath ) ) {
			config.classConfig.loadFromXML( is );
		}
		// type mapper
		String typeMapperClass = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_TYPE_MAPPER, DaogenCatalogConstants.GEN_PROP_TYPE_MAPPER_DEFAULT );
		DaogenTypeMapper typeMapper = (DaogenTypeMapper)ClassHelper.newInstance( typeMapperClass );
		typeMapper.init( config );
		config.typeMapper = typeMapper;
		// generator catalog
		String pathGeneratorCatalog = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG, DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT );
		try ( InputStream is = StreamHelper.resolveStream( pathGeneratorCatalog ) ) {
			DaogenGeneratorCatalog generatorCatalog = new DaogenGeneratorCatalog();
			DaogenGeneratorCatalog.load( is , generatorCatalog );
			config.generatorCatalog = generatorCatalog;
		}
		return config;
	}
	
	private Properties classConfig;

	public Properties getClassConfig() {
		return classConfig;
	}
	
	private DaogenTypeMapper typeMapper;

	public DaogenTypeMapper getTypeMapper() {
		return typeMapper;
	}
	
	private DaogenGeneratorCatalog generatorCatalog;

	public DaogenGeneratorCatalog getGeneratorCatalog() {
		return generatorCatalog;
	}
	
}
