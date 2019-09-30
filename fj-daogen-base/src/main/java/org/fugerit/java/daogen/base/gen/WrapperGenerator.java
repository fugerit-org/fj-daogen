package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
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
			this.getWriter().println( "	/*" );
			this.getWriter().println( "	 * fields generated for relations " );
			this.getWriter().println( "	 */" );
			this.getWriter().println();
			for ( DaogenCatalogRelation relation : this.getCurrentEntity().getRelations() ) {
				DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( relation.getTo() );
				String baseType = DaogenCatalogConstants.modelName( entityTo );
				String className = GeneratorNameHelper.toClassName( relation.getName() );
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
					baseType = "java.util.List<"+baseType+">";
				}
				// metodo set
				this.getWriter().println( "	@Override" );
				this.getWriter().println( "	public void set"+className+"( "+baseType+" value ) {" );
				this.getWriter().println( "		this.unwrapModel().set"+className+"( value );" );
				this.getWriter().println( "	}" );
				this.getWriter().println();
				// metodo get
				this.getWriter().println( "	@Override" );
				this.getWriter().println( "	public "+baseType+" get"+className+"() {" );
				this.getWriter().println( "		return this.unwrapModel().get"+className+"();" );
				this.getWriter().println( "	}" );
				this.getWriter().println();
			}
		}
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		
		this.getWriter().println( "	public "+this.getEntityWrapperName()+"( "+this.getEntityModelName()+" wrapped ) {" );
		this.getWriter().println( "		super( wrapped );" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
		
		this.getWriter().println( "	public "+this.getEntityModelName()+" unwrap( "+this.getEntityWrapperName()+" wrapper ) {" );
		this.getWriter().println( "		"+this.getEntityModelName()+" res = wrapper;" );
		this.getWriter().println( "		while ( res != null && res instanceof "+this.getEntityWrapperName()+" ) { " );
		this.getWriter().println( "			res = (("+this.getEntityWrapperName()+")res).unwrapModel();" );
		this.getWriter().println( "		}" );
		this.getWriter().println( "		return res;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
		
		boolean relationLast = "true".equalsIgnoreCase( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
		if ( !relationLast ) {
			this.generateRelations();
		}
		
		this.getWriter().println( "	/*" );
		this.getWriter().println( "	 * fields generated for entity attributes " );
		this.getWriter().println( "	 */" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			// property 
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String realJavaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			// metodo set
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
			this.getWriter().println( "		this.unwrapModel().set"+javaSuffix+"( value );" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
			// metodo get
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+realJavaType+" get"+javaSuffix+"() {" );
			this.getWriter().println( "		return this.unwrapModel().get"+javaSuffix+"();" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
		}
		
		if ( relationLast ) {
			this.generateRelations();
		}

	}

	
	
}
