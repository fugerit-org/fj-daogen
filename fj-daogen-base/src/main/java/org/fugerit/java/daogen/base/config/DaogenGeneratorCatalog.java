package org.fugerit.java.daogen.base.config;

import org.fugerit.java.core.cfg.xml.FactoryCatalog;
import org.fugerit.java.core.cfg.xml.FactoryType;
import org.fugerit.java.core.cfg.xml.ListMapConfig;

public class DaogenGeneratorCatalog extends FactoryCatalog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3844355683266844186L;

	public ListMapConfig<FactoryType> getEntityGenerators() {
		return this.getListMap( "entity_generators" );
	}

	public ListMapConfig<FactoryType> getFactoryGenerators() {
		return this.getListMap( "factory_generators" );
	}
	
}
