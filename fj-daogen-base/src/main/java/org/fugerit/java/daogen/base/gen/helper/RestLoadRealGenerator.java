package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class RestLoadRealGenerator extends DaogenBasicGenerator {

	public static final String KEY = RestLoadRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_REST_LOAD ), DaogenCatalogConstants.restLoadName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( "javax.ejb.Stateless" );
		this.getImportList().add( "javax.ws.rs.Path" );
		this.setExtendsClass( DaogenHelperGenerator.toHelperClassName( this.getJavaName() ) );
		try {
			this.checkSkipRealClass();
		} catch (IOException e) {
			throw new ConfigException( e );
		}
	}

	@Override
	protected void beforeClass() {
		super.beforeClass();
		String urlBase = this.getCurrentEntity().getName().replaceAll( "_" , "" ).toLowerCase();
		this.getWriter().println( "@Stateless" );
		this.getWriter().println( "@Path(\"/"+urlBase+"/load\")" );
	}

	@Override
	public void generateDaogenBody() throws Exception {
		this.addSerialVerUID();
		this.println( REAL_CLASS_COMMENT );
	}

}
