package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCatalogRelation;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class WrapperGenerator extends DaogenBasicGenerator {

	public static final String KEY = "WraoerGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ), DaogenCatalogConstants.wrapperName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.setClassBaseWrapper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_WRAPPER_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.setExtendsClass( this.getClassBaseWrapper()+"<"+this.getEntityModelName()+">" );
		this.setImplementsInterface( this.getEntityModelName() );
		for ( DaogenCatalogRelation relation : this.getCurrentEntity().getRelations() ) {
			DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( relation.getTo() );
			this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+DaogenCatalogConstants.modelName( entityTo ) );
		}
	}
	
	private void generateRelations() {
		if ( !this.getCurrentEntity().getRelations().isEmpty() ) {
			this.getWriter().println( "\t/*" );
			this.getWriter().println( "\t * fields generated for relations " );
			this.getWriter().println( "\t */" );
			this.getWriter().println();
			for ( DaogenCatalogRelation relation : this.getCurrentEntity().getRelations() ) {
				DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( relation.getTo() );
				String baseType = DaogenCatalogConstants.modelName( entityTo );
				String className = GeneratorNameHelper.toClassName( relation.getName() );
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
					baseType = "java.util.List<"+baseType+">";
				}
				// metodo set
				this.getWriter().println( "\t@Override" );
				this.getWriter().println( "\tpublic void set"+className+"( "+baseType+" value ) {" );
				this.getWriter().println( "\t\tthis.unwrapModel().set"+className+"( value );" );
				this.getWriter().println( "\t}" );
				this.getWriter().println();
				// metodo get
				this.getWriter().println( "\t@Override" );
				this.getWriter().println( "\tpublic "+baseType+" get"+className+"() {" );
				this.getWriter().println( "\t\treturn this.unwrapModel().get"+className+"();" );
				this.getWriter().println( "\t}" );
				this.getWriter().println();
			}
		}
	}

	@Override
	public void generateDaogenBody() throws Exception {
		this.addSerialVerUID();
		
		this.getWriter().println( "\tpublic "+this.getEntityWrapperName()+"( "+this.getEntityModelName()+" wrapped ) {" );
		this.getWriter().println( "\t\tsuper( wrapped );" );
		this.getWriter().println( "\t}" );
		this.getWriter().println();
		
		this.getWriter().println( "\tpublic "+this.getEntityModelName()+" unwrap( "+this.getEntityWrapperName()+" wrapper ) {" );
		this.getWriter().println( "\t\t"+this.getEntityModelName()+" res = wrapper;" );
		this.getWriter().println( "\t\twhile ( res != null && res instanceof "+this.getEntityWrapperName()+" ) { " );
		this.getWriter().println( "\t\t\tres = (("+this.getEntityWrapperName()+")res).unwrapModel();" );
		this.getWriter().println( "\t\t}" );
		this.getWriter().println( "\t\treturn res;" );
		this.getWriter().println( "\t}" );
		this.getWriter().println();
		
		boolean relationLast = "true".equalsIgnoreCase( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
		if ( !relationLast ) {
			this.generateRelations();
		}
		
		this.getWriter().println( "\t/*" );
		this.getWriter().println( "\t * fields generated for entity attributes " );
		this.getWriter().println( "\t */" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			// property 
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String realJavaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			// metodo set
			this.getWriter().println( "\t@Override" );
			this.getWriter().println( "\tpublic void set"+javaSuffix+"( "+realJavaType+" value ) {" );
			this.getWriter().println( "\t\tthis.unwrapModel().set"+javaSuffix+"( value );" );
			this.getWriter().println( "\t}" );
			this.getWriter().println();
			// metodo get
			this.getWriter().println( "\t@Override" );
			this.getWriter().println( "\tpublic "+realJavaType+" get"+javaSuffix+"() {" );
			this.getWriter().println( "\t\treturn this.unwrapModel().get"+javaSuffix+"();" );
			this.getWriter().println( "\t}" );
			this.getWriter().println();
		}
		
		if ( relationLast ) {
			this.generateRelations();
		}

		if ( BooleanUtils.isTrue( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_CHECK_EMPTY_INTERFACE ) ) ) {
			this.getWriter().println( "\t@Override" );
			this.getWriter().println( "\tpublic boolean isEmpty() {" );
			this.getWriter().println( "\t\treturn this.unwrapModel().isEmpty();" );
			this.getWriter().println( "\t}" );
			this.getWriter().println();	
		}
		
	}

	
	
}
