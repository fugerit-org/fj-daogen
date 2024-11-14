package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.gen.util.WrapperUtils;

public class WrapperGenerator extends DaogenBasicGenerator {

	public static final String KEY = "WraoerGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ), DaogenCatalogConstants.wrapperName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		WrapperUtils.init( daogenConfig, entity, this );
	}

	@Override
	public void generateDaogenBody() throws IOException {
		WrapperUtils.generateBody( this, f -> null );
	}
	
}
