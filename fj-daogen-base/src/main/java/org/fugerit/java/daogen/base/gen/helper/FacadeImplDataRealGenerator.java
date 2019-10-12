package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class FacadeImplDataRealGenerator extends DaogenBasicGenerator {

	public static final String KEY = FacadeImplDataRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DATA_IMPL ), DaogenCatalogConstants.facadeImplDataName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFacadeDefName() );
		this.setImplementsInterface( this.getEntityFacadeDefName() );
		this.setExtendsClass( DaogenHelperGenerator.toHelperClassName( this.getJavaName() ) );
		try {
			this.checkSkipRealClass();
		} catch (IOException e) {
			throw new ConfigException( e );
		}
	}
	
	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		this.println( REAL_CLASS_COMMENT );
	}

}
