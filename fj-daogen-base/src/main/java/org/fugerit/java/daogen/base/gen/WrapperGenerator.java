package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

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
			this.getWriter().println( TAB+"/*" );
			this.getWriter().println( TAB+" * fields generated for relations " );
			this.getWriter().println( TAB+" */" );
			this.getWriter().println();
			for ( DaogenCatalogRelation relation : this.getCurrentEntity().getRelations() ) {
				DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( relation.getTo() );
				String baseType = DaogenCatalogConstants.modelName( entityTo );
				String className = GeneratorNameHelper.toClassName( relation.getName() );
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
					baseType = "java.util.List<"+baseType+">";
				}
				// metodo set
				this.getWriter().println( TAB+"@Override" );
				this.getWriter().println( TAB+"public void set"+className+"( "+baseType+" value ) {" );
				this.getWriter().println( TAB_2+"this.unwrapModel().set"+className+"( value );" );
				this.getWriter().println( TAB+"}" );
				this.getWriter().println();
				// metodo get
				this.getWriter().println( TAB+"@Override" );
				this.getWriter().println( TAB+"public "+baseType+" get"+className+"() {" );
				this.getWriter().println( TAB_2+"return this.unwrapModel().get"+className+"();" );
				this.getWriter().println( TAB+"}" );
				this.getWriter().println();
			}
		}
	}

	@Override
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		
		this.getWriter().println( TAB+"public "+this.getEntityWrapperName()+"( "+this.getEntityModelName()+" wrapped ) {" );
		this.getWriter().println( TAB_2+"super( wrapped );" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		
		this.getWriter().println( TAB+"public "+this.getEntityModelName()+" unwrap( "+this.getEntityWrapperName()+" wrapper ) {" );
		this.getWriter().println( TAB_2+""+this.getEntityModelName()+" res = wrapper;" );
		this.getWriter().println( TAB_2+"while ( res != null && res instanceof "+this.getEntityWrapperName()+" ) { " );
		this.getWriter().println( TAB_3+"res = (("+this.getEntityWrapperName()+")res).unwrapModel();" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+"return res;" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		
		boolean relationLast = "true".equalsIgnoreCase( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
		if ( !relationLast ) {
			this.generateRelations();
		}
		
		this.getWriter().println( TAB+"/*" );
		this.getWriter().println( TAB+" * fields generated for entity attributes " );
		this.getWriter().println( TAB+" */" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			// property 
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String realJavaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			// metodo set
			this.getWriter().println( TAB+"@Override" );
			this.getWriter().println( TAB+"public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
			this.getWriter().println( TAB_2+"this.unwrapModel().set"+javaSuffix+"( value );" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
			// metodo get
			this.getWriter().println( TAB+"@Override" );
			this.getWriter().println( TAB+"public "+realJavaType+" get"+javaSuffix+"() {" );
			this.getWriter().println( TAB_2+"return this.unwrapModel().get"+javaSuffix+"();" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
		}
		
		if ( relationLast ) {
			this.generateRelations();
		}

		if ( BooleanUtils.isTrue( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_CHECK_EMPTY_INTERFACE ) ) ) {
			this.getWriter().println( TAB+"@Override" );
			this.getWriter().println( TAB+"public boolean isEmpty() {" );
			this.getWriter().println( TAB_2+"return this.unwrapModel().isEmpty();" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();	
		}
		
	}

	
	
}
