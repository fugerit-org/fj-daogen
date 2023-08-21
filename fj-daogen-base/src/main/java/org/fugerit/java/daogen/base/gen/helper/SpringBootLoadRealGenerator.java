package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

public class SpringBootLoadRealGenerator extends DaogenBasicGenerator {

	public static final String KEY = SpringBootLoadRealGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}


	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_SPRING_REST_LOAD ), DaogenCatalogConstants.restLoadName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( "org.springframework.web.bind.annotation.RequestMapping" );
		this.getImportList().add( "org.springframework.web.bind.annotation.RestController" );
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
		String urlBase = this.getCurrentEntity().getName().replace( "_" , "" ).toLowerCase();
		this.getWriter().println( "@RestController" );
		this.getWriter().println( "@RequestMapping(\"/"+urlBase+"/load\")" );
	}

	@Override
	public void generateDaogenBody() throws Exception {
		this.addSerialVerUID();
		this.println( REAL_CLASS_COMMENT );
	}

}
