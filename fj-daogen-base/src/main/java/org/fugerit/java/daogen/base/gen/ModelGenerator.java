package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;

public class ModelGenerator extends DaogenBasicGenerator {

	public static final String KEY = "ModelGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL ), DaogenCatalogConstants.modelName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
	}

	@Override
	public void generateBody() throws Exception {
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String propertyName = GeneratorNameHelper.toPropertyName( field.getId() );
			String className = GeneratorNameHelper.toClassName( field.getId() );
			String type = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			DaogenCustomCode.addCommentCommon( "comments.common.getter", 
					DaogenCustomCode.INDENT_1, this.getWriter(), propertyName, field.getNullable(), field.getComments() );
			this.println( "	"+type+" get"+className+"();" );
			this.println();
			DaogenCustomCode.addCommentCommon( "comments.common.setter", 
					DaogenCustomCode.INDENT_1, this.getWriter(), propertyName, field.getNullable(), field.getComments() );
			this.println( "	void set"+className+"( "+type+" value );" );
			this.println();
		}
	}

	
	
}
