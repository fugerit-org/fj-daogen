package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class HelperGenerator extends DaogenBasicGenerator {

	public static final String KEY = "HelperGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ), DaogenCatalogConstants.helperName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.setClassBaseHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_HELPER_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.setExtendsClass( this.getClassBaseHelper() );
		this.setImplementsInterface( this.getEntityModelName() );
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			// property 
			String javaProperty = GeneratorNameHelper.toPropertyName( field.getId() );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String realJavaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			this.getWriter().println( "	private "+realJavaType+" "+javaProperty+";" );
			this.getWriter().println();
			// metodo set
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
			this.getWriter().println( "		this."+javaProperty+" = value;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
			// metodo get
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+realJavaType+" get"+javaSuffix+"() {" );
			this.getWriter().println( "		return this."+javaProperty+";" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
		}
		// metodo toString()
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public String toString() {" );
		this.getWriter().println( "		StringBuilder buffer = new StringBuilder();" );
		this.getWriter().println( "		buffer.append( this.getClass().getSimpleName() );" );
		boolean firstColumn = true;
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String javaProperty = GeneratorNameHelper.toPropertyName( field.getId() );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			if ( firstColumn ) {
				this.getWriter().println( "		buffer.append( \"["+javaProperty+"=\" );" );
				firstColumn = false;
			} else {
				this.getWriter().println( "		buffer.append( \","+javaProperty+"=\" );" );
			}
			
			this.getWriter().println( "		buffer.append( this.get"+javaSuffix+"() );" );
		}
		this.getWriter().println( "		buffer.append( \"]\" );" );
		this.getWriter().println( "		return buffer.toString();" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
	}

	
	
}
