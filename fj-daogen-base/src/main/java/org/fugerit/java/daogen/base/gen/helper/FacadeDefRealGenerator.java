package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class FacadeDefRealGenerator extends DaogenBasicGenerator {

	public static final String KEY = FacadeDefRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public static final String PRIMARY_KEY = "primary key";
	public static final String METHOD_LOAD_BY_PK = "loadById";
	public static final String METHOD_DELETE_BY_PK = "deleteById";
	public static final String METHOD_UPDATE_BY_PK = "updateById";
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.facadeDefName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
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
