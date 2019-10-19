package org.fugerit.java.daogen.base.config;

import org.fugerit.java.core.cfg.xml.FactoryCatalog;
import org.fugerit.java.core.cfg.xml.FactoryType;
import org.fugerit.java.core.cfg.xml.ListMapConfig;
import org.fugerit.java.core.lang.helpers.StringUtils;

public class DaogenDecoratorCatalog extends FactoryCatalog {

	public final static String KEY_EXTENDS_DEFAULT = "extends-default";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3844355683266844186L;

	public ListMapConfig<FactoryType> getEntityGenerators( DaogenCatalogConfig config ) {
		String id = "entity_generators";
		if ( StringUtils.isNotEmpty( config.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_HELPERS ) ) ) {
			id = id+="_helper";
		}
		return this.getListMap( id );
	}

	public ListMapConfig<FactoryType> getFactoryGenerators( DaogenCatalogConfig config ) {
		String id = "factory_generators";
		if ( StringUtils.isNotEmpty( config.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_HELPERS ) ) ) {
			id = id+="_helper";
		}
		return this.getListMap( id );
	}
	
}
