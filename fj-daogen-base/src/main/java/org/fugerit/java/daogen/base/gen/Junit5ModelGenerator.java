package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;
import org.fugerit.java.daogen.base.gen.util.GenUtils;
import org.fugerit.java.daogen.base.gen.util.UnitTestHelper;

import java.io.IOException;

public class Junit5ModelGenerator extends DaogenBasicGenerator {

	public static final String KEY = Junit5ModelGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_TEST_JAVA ),
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_JUNIT5_MODEL ), DaogenCatalogConstants.junit5ModelName( entity ) ),
				STYLE_INTERFACE, daogenConfig, entity );
		this.setPublicClass( false );
		this.setJavaStyle( STYLE_CLASS );
		GenUtils.addAll( this.getImportList(), "org.slf4j.Logger", "org.slf4j.LoggerFactory", "org.junit.jupiter.api.Assertions", "org.junit.jupiter.api.Test",
				this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName(),
				this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName());
	}

	@Override
	public void generateDaogenBody() throws IOException {
		UnitTestHelper.genBodyBasicTest( this, 5 );
	}

}
