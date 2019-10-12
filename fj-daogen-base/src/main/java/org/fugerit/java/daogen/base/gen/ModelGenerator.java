package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCatalogRelation;
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

	private void generateRelations() throws Exception {
		for ( DaogenCatalogRelation relation : this.getCurrentEntity().getRelations() ) {
			DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( relation.getTo() );
			try {
				String baseType = DaogenCatalogConstants.modelName( entityTo );
				String className = GeneratorNameHelper.toClassName( relation.getName() );
				String propertyName = GeneratorNameHelper.toClassName( relation.getName() );
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
					baseType = "java.util.List<"+baseType+">";
				}
				DaogenCustomCode.addCommentCommon( "comments.common.getter", DaogenCustomCode.INDENT_1, this.getWriter(), propertyName, "yes", "relation to entity : "+entityTo.getName() );
				this.println( "	"+baseType+" get"+className+"();" );
				this.println();
				DaogenCustomCode.addCommentCommon( "comments.common.setter", DaogenCustomCode.INDENT_1, this.getWriter(), propertyName, "yes", "relation to entity : "+entityTo.getName() );
				this.println( "	void set"+className+"( "+baseType+" value );" );
				this.println();	
			} catch (Exception e) {
				logger.error( "error on relation : "+relation.getId() );
				throw e;
			}
			
		}
	}	
	
	@Override
	public void generateBody() throws Exception {
		boolean relationLast = "true".equalsIgnoreCase( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
		if ( !relationLast ) {
			this.generateRelations();
		}
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
		if ( relationLast ) {
			this.generateRelations();
		}
	}

	
	
}
