package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;

public class ModelGenerator extends DaogenBasicGenerator {

	public ModelGenerator( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		super( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL ), DaogenCatalogConstants.modelName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
	}

	@Override
	public void generateBody() throws Exception {
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String propertyName = GeneratorNameHelper.toPropertyName( field.getId() );
			String className = GeneratorNameHelper.toClassName( field.getId() );
			String type = "String";
			this.println( "	"+type+" get"+className+"();" );
			this.println();
			this.println( "	void set"+className+"( "+type+" value );" );
			this.println();
		}
	}

	
	
}
