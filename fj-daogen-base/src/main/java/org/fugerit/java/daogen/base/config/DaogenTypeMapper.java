package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.io.helper.StreamHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaogenTypeMapper {

	protected static Logger logger = LoggerFactory.getLogger( DaogenTypeMapper.class );
	
	private DaogenCatalogConfig config;
	
	private Properties typeMapConfig;
	
	public DaogenTypeMapper() {
		this.typeMapConfig = new Properties();
	}

	public void init( DaogenCatalogConfig config ) throws ConfigException {
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
		type = this.getTypeMapConfig().getProperty( "model_"+type, type );
		return type;
	}
	
}
