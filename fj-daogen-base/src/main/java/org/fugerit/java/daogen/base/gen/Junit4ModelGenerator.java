package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;
import org.fugerit.java.daogen.base.gen.util.UnitTestHelper;

public class Junit4ModelGenerator extends DaogenBasicGenerator {

	public static final String KEY = Junit4ModelGenerator.class.getSimpleName();
	
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
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_JUNIT4_MODEL ), DaogenCatalogConstants.junit4ModelName( entity ) ),
				STYLE_INTERFACE, daogenConfig, entity );
		this.setJavaStyle( STYLE_CLASS );
		UnitTestHelper.genInitBasicTest( this, 4 );
	}

	@Override
	public void generateDaogenBody() throws IOException {
		UnitTestHelper.genBodyBasicTest( this, 4 );
	}

}
