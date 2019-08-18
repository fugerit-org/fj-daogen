package org.fugerit.java.daogen.base.gen;

import java.io.File;

import org.fugerit.java.core.javagen.SimpleJavaGenerator;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;

public abstract class DaogenBasicGenerator extends SimpleJavaGenerator {

	private DaogenCatalogConfig daogenConfig;
	
	private DaogenCatalogEntity currentEntity;
	
	public static String fullObjectName( String packageName, String simpleClassName ) {
		return packageName+"."+simpleClassName;
	}
	
	public DaogenBasicGenerator( String sourceFolder, String fullObjectBName, String javaStyle, DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		super( new File( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER ), sourceFolder ), fullObjectBName, javaStyle, daogenConfig.getGeneralProps() );
		this.daogenConfig = daogenConfig;
		this.currentEntity = entity;
	}

	protected DaogenCatalogConfig getDaogenConfig() {
		return daogenConfig;
	}

	protected void setDaogenConfig(DaogenCatalogConfig daogenConfig) {
		this.daogenConfig = daogenConfig;
	}

	protected DaogenCatalogEntity getCurrentEntity() {
		return currentEntity;
	}

	protected void setCurrentEntity(DaogenCatalogEntity currentEntity) {
		this.currentEntity = currentEntity;
	}

	@Override
	public abstract void generateBody() throws Exception;
	
	public String getEntityModelName() {
		return "Model"+this.getJavaName();
	}
	
}
