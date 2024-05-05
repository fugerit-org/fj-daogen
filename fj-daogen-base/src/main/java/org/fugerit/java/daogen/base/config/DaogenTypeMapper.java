package org.fugerit.java.daogen.base.config;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.io.helper.StreamHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class DaogenTypeMapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9010791577199355112L;

	protected static Logger logger = LoggerFactory.getLogger( DaogenTypeMapper.class );

	private static final String PREFIX_MODEL = "model_";

	private DaogenCatalogConfig config;
	
	private Properties typeMapConfig;

	private boolean ngMode;

	private String testAltPrefix;

	public DaogenTypeMapper() {
		this.typeMapConfig = new Properties();
	}

	public void init( DaogenCatalogConfig config ) throws ConfigException {
		this.ngMode = DaogenCatalogConstants.GEN_PROP_TIME_MAPPER_NG_ENABLED.equalsIgnoreCase( config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_TIME_MAPPER_NG, DaogenCatalogConstants.GEN_PROP_TIME_MAPPER_NG_DISABLED ) );
		if ( this.ngMode ) {
			this.testAltPrefix = "ng_";
		} else {
			this.testAltPrefix = "";
		}
		String path = config.getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_TYPE_MAP_CONFIG, DaogenCatalogConstants.GEN_PROP_TYPE_MAP_CONFIG_DEFAULT );
		try ( InputStream is = StreamHelper.resolveStream( path ) ) {
			this.getTypeMapConfig().loadFromXML( is );
		} catch (Exception e) {
			throw new ConfigException( e );
		}
	}
	
	public Properties getTypeMapConfig() {
		return typeMapConfig;
	}

	protected DaogenCatalogConfig getConfig() {
		return config;
	}

	public String mapForModel( DaogenCatalogField field ) {
		String type = field.getJavaType();
		if ( this.ngMode ) {
			type = this.getTypeMapConfig().getProperty( PREFIX_MODEL+this.testAltPrefix+type, this.getTypeMapConfig().getProperty( PREFIX_MODEL+type, type ) );
		} else {
			type = this.getTypeMapConfig().getProperty( PREFIX_MODEL+type, type );
		}
		return type;
	}
	
}
