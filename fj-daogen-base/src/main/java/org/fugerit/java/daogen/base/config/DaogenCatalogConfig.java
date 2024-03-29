package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.CustomListCatalogConfig;
import org.fugerit.java.core.cfg.xml.XmlBeanHelper;
import org.fugerit.java.core.io.helper.CustomPrintWriter;
import org.fugerit.java.core.io.helper.StreamHelper;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.util.collection.ListMapStringKey;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DaogenCatalogConfig extends CustomListCatalogConfig<DaogenCatalogField, DaogenCatalogEntity> {

	public static final String ATT_DAOGEN_ROOT = "daogen-config";
	
	public static final String ATT_DAOGEN_ENTITY = "entity";
	
	public static final String ATT_DAOGEN_FIELD = "field";
	
	public static final String ATT_DAOGEN_RELATION= "relation";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491328999494021347L;

	private ListMapStringKey<DaogenCatalogRelation> relations;

	/**
	 * This ensures that source code generated in different systems are always with the same line separator
	 * (So that source control will not consider them different, if there are no other change)
	 * Default value is CustomPrintWriter.WINDOWS_LINE_SEPARATOR
	 */
	public static final String DEFAULT_LINE_SEPARATOR = CustomPrintWriter.WINDOWS_LINE_SEPARATOR;
	
	public DaogenCatalogConfig() {
		super( ATT_DAOGEN_ENTITY, ATT_DAOGEN_FIELD );
		this.getGeneralProps().setProperty( ATT_TYPE , DaogenCatalogField.class.getName() );
		this.getGeneralProps().setProperty( ATT_LIST_TYPE , DaogenCatalogEntity.class.getName() );
		this.classConfig = new Properties();
		this.relations = new ListMapStringKey<>();
		this.generatorCatalogs = new ArrayList<>();
		this.lineSeparator = DEFAULT_LINE_SEPARATOR;
	}
	
	public String getGeneralProp( String key ) {
		return this.getGeneralProps().getProperty( key );
	}

	public String getGeneralProp( String key, String defValue ) {
		return this.getGeneralProps().getProperty( key, defValue );
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input ) throws ConfigException {
		return loadConfig(input, DaogenCatalogConfig.class, null);
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input, Properties overrideProperties ) throws ConfigException {
		return loadConfig( input, DaogenCatalogConfig.class, overrideProperties );
	}
	
	private static void loadGeneratorCatalog( DaogenCatalogConfig config, Class<?> c ) throws ConfigException {
		// generator catalog
		String pathGeneratorCatalog = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG, DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT );
		try ( InputStream is = StreamHelper.resolveStream( pathGeneratorCatalog, null, c ) ) {
			DaogenGeneratorCatalog generatorCatalog = new DaogenGeneratorCatalog();
			load( is , generatorCatalog );
			config.getGeneratorCatalogs().add( generatorCatalog );
			if ( !DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT.equalsIgnoreCase( pathGeneratorCatalog ) ) {
				String extendsDefault = generatorCatalog.getGeneralProps().getProperty(  DaogenGeneratorCatalog.KEY_EXTENDS_DEFAULT );
				if ( "true".equalsIgnoreCase( extendsDefault ) ) {
					try ( InputStream isDef = StreamHelper.resolveStream( DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT, null, c ) ) {
						DaogenGeneratorCatalog generatorCatalogDef = new DaogenGeneratorCatalog();
						load( isDef , generatorCatalogDef );
						config.getGeneratorCatalogs().add( generatorCatalogDef );
					}
				}
			}
		} catch (Exception e) {
			throw ConfigException.convertExMethod( "loadGeneratorCatalog" , e );
		}
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input, Class<?> c ) throws ConfigException {
		return loadConfig(input, c, null);
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input, Class<?> c, Properties overrideProperties ) throws ConfigException {
		DaogenCatalogConfig config = new DaogenCatalogConfig();
		try {
			load( input , config );
			// override properties START
			if ( overrideProperties != null ) {
				config.getLogger().info( "override properties -> {}", overrideProperties );
				for ( Object k : overrideProperties.keySet() ) {
					String key = k.toString();
					String value = overrideProperties.getProperty(key);
					config.getLogger().info( "ovverride {} -> {}", key, value );
					config.getGeneralProps().setProperty(key, value);
				}
				config.getGeneralProps().keySet().stream().sorted().forEach( 
						k -> config.getLogger().info( "prop key : {} value : {}", k, config.getGeneralProps().getProperty( k.toString() ) ) );
			}
			// override properties END
			// class config
			String classConfigPath = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_CLASS_CONFIG, DaogenCatalogConstants.GEN_PROP_CLASS_CONFIG_DEFAULT );
			try ( InputStream is = StreamHelper.resolveStream( classConfigPath, null, c ) ) {
				config.classConfig.loadFromXML( is );
			}
			// type mapper
			String typeMapperClass = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_TYPE_MAPPER, DaogenCatalogConstants.GEN_PROP_TYPE_MAPPER_DEFAULT );
			DaogenTypeMapper typeMapper = (DaogenTypeMapper)ClassHelper.newInstance( typeMapperClass );
			typeMapper.init( config );
			config.typeMapper = typeMapper;
			// decorator catalog
			String pathDecoratorCatalog = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_DECORATOR_CATALOG, DaogenCatalogConstants.GEN_PROP_DECORATOR_CATALOG_DEFAULT );
			DaogenGeneratorCatalog daogenDecoratorCatalog = new DaogenGeneratorCatalog();
			if ( pathDecoratorCatalog != null ) {
				try ( InputStream is = StreamHelper.resolveStream( pathDecoratorCatalog, null, c )  ) {
					daogenDecoratorCatalog.configureXML( is );
				}
			}
			config.setDecoratorCatalog( daogenDecoratorCatalog );
			loadGeneratorCatalog(config, c);
		} catch (Exception e) {
			throw ConfigException.convertExMethod( "loadConfig", e );
		}
		return config;
	}

	private void finishingTouch() {
		this.getIdSet().forEach( entityId -> this.getListMap( entityId ).finishingTouch() );
	}

	@Override
	public void configure(Element tag) throws ConfigException {
		super.configure(tag);
		// load relations 
		NodeList relationTags = tag.getElementsByTagName( ATT_DAOGEN_RELATION );
		for ( int k=0; k<relationTags.getLength(); k++ ) {
			Element relationTag = (Element) relationTags.item( k );
			DaogenCatalogRelation relation = new DaogenCatalogRelation();
			try {
				XmlBeanHelper.setFromElement( relation , relationTag );
				this.getRelations().add( relation );
				DaogenCatalogEntity entity = this.getListMap( relation.getFrom() );
				entity.getRelations().add( relation );
			} catch (Exception e) {
				throw new ConfigException( e );
			}
		}
		// finishing touch
		this.finishingTouch();
	}

	private Properties classConfig;

	public Properties getClassConfig() {
		return classConfig;
	}
	
	private DaogenTypeMapper typeMapper;

	public DaogenTypeMapper getTypeMapper() {
		return typeMapper;
	}
	
	private List<DaogenGeneratorCatalog> generatorCatalogs;

	public Collection<DaogenGeneratorCatalog> getGeneratorCatalogs() {
		return generatorCatalogs;
	}

	public ListMapStringKey<DaogenCatalogRelation> getRelations() {
		return relations;
	}
	
	private DaogenGeneratorCatalog decoratorCatalog;


	public DaogenGeneratorCatalog getDecoratorCatalog() {
		return decoratorCatalog;
	}

	public void setDecoratorCatalog(DaogenGeneratorCatalog decoratorCatalog) {
		this.decoratorCatalog = decoratorCatalog;
	}
	
	private String lineSeparator;

	public String getLineSeparator() {
		return lineSeparator;
	}

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

}
