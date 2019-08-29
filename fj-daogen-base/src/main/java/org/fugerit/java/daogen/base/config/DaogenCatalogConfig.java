package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.CustomListCatalogConfig;
import org.fugerit.java.core.cfg.xml.XmlBeanHelper;
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

	
	public DaogenCatalogConfig() {
		super( ATT_DAOGEN_ENTITY, ATT_DAOGEN_FIELD );
		this.getGeneralProps().setProperty( ATT_TYPE , DaogenCatalogField.class.getName() );
		this.getGeneralProps().setProperty( ATT_LIST_TYPE , DaogenCatalogEntity.class.getName() );
		this.classConfig = new Properties();
		this.relations = new ListMapStringKey<>();
		this.generatorCatalogs = new ArrayList<DaogenGeneratorCatalog>();
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
			config.getGeneratorCatalogs().add( generatorCatalog );
			if ( !DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT.equalsIgnoreCase( pathGeneratorCatalog ) ) {
				String extendsDefault = generatorCatalog.getGeneralProps().getProperty(  DaogenGeneratorCatalog.KEY_EXTENDS_DEFAULT );
				if ( "true".equalsIgnoreCase( extendsDefault ) ) {
					try ( InputStream isDef = StreamHelper.resolveStream( DaogenCatalogConstants.GEN_PROP_GENERATOR_CATALOG_DEFAULT ) ) {
						DaogenGeneratorCatalog generatorCatalogDef = new DaogenGeneratorCatalog();
						DaogenGeneratorCatalog.load( isDef , generatorCatalogDef );
						config.getGeneratorCatalogs().add( generatorCatalogDef );
					}
				}
			}
		}
		return config;
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
	
}
