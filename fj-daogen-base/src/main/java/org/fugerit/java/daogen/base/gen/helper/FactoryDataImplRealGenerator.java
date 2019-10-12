package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class FactoryDataImplRealGenerator extends DaogenBasicGenerator {

	public static final String KEY = FactoryDataImplRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DATA_IMPL ), 
				STYLE_CLASS, daogenConfig, null );
		this.setExtendsClass( DaogenHelperGenerator.toHelperClassName( this.getJavaName() ) );
		try {
			this.checkSkipRealClass();
		} catch (IOException e) {
			throw new ConfigException( e );
		}
	}


	@Override
	public void generateBody() throws Exception {
		this.println( REAL_CLASS_COMMENT );
	}

}
