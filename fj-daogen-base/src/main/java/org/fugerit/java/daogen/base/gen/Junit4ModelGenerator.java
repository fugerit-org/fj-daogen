package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.*;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class Junit4ModelGenerator extends DaogenBasicGenerator {

	public static final String KEY = Junit4ModelGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_TEST_JAVA ),
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_JUNIT4_MODEL ), DaogenCatalogConstants.junit4ModelName( entity ) ),
				STYLE_INTERFACE, daogenConfig, entity );
		this.setJavaStyle( STYLE_CLASS );
		DaogenCatalogField idField = entity.get( DaogenCatalogEntity.DEFAULT_ID_FIELD );
		this.getImportList().add( "org.slf4j.Logger" );
		this.getImportList().add( "org.slf4j.LoggerFactory" );
		this.getImportList().add( "org.junit.Assert" );
		this.getImportList().add( "org.junit.Test" );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName() );
	}

	@Override
	public void generateDaogenBody() throws IOException {
		String blobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Blob" );
		String clobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Clob" );
		// logger
		this.getWriter().println( TAB+"private static final Logger logger = LoggerFactory.getLogger( "+DaogenCatalogConstants.junit4ModelName( this.getCurrentEntity() )+".class );" );
		// creates a new instance
		this.getWriter().println( TAB+"public "+this.getEntityModelName()+" newInstance() { " );
		this.getWriter().println( TAB_2+""+this.getEntityHelperName()+" current = new "+this.getEntityHelperName()+"();" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.handleFieldNewInstance(field, blobHandlerType, clobHandlerType);
		}
		this.getWriter().println( TAB_2+"return current;" );
		this.getWriter().println( TAB+"}" );
		// read all fields
		this.getWriter().println( TAB+"public void printAll( "+this.getEntityModelName()+" current ) { " );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.getWriter().println( TAB_2+" logger.info( \""+field.getId()+"-> {}\", current.get"+GeneratorNameHelper.toClassName( field.getId() )+"() );" );
		}
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		// create / print test
		this.getWriter().println( TAB+"@Test" );
		this.getWriter().println( TAB+"public void testModel"+GeneratorNameHelper.toClassName( this.getCurrentEntity().getName() )+"() { " );
		this.getWriter().println( TAB_2+this.getEntityModelName()+" current = this.newInstance();" );
		this.getWriter().println( TAB_2+"this.printAll( current );" );
		this.getWriter().println( TAB_2+"Assert.assertNotNull( current );" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
	}

	private void handleFieldNewInstance( DaogenCatalogField field, String blobHandlerType, String clobHandlerType ) {
		String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
		StringBuilder line = new StringBuilder();
		line.append( TAB_2);
		line.append( "current.set" );
		line.append(  GeneratorNameHelper.toClassName( field.getId() ) );
		line.append( "(" );
		if ( columnType.equalsIgnoreCase( "java.lang.String" ) ) {
			line.append( "\"1\"" );
		} else 	if ( columnType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
			line.append( "new java.math.BigDecimal( \"1\" )" );
		} else 	if ( columnType.equalsIgnoreCase( "java.lang.Integer" ) ) {
			line.append( "1" );
		} else 	if ( columnType.equalsIgnoreCase( "java.sql.Date" ) ) {
			line.append( "new java.sql.Date( System.currentTimeMillis )" );
		} else 	if ( columnType.equalsIgnoreCase( "java.sql.Timestamp" ) ) {
			line.append( "new java.sql.Timestamp( System.currentTimeMillis )" );
		} else 	if ( columnType.equalsIgnoreCase( "java.util.Date" ) ) {
			line.append( "new java.util.Date()" );
		} else {
			line.append( "null" );
		}
		line.append( ");" );
		this.getWriter().println( line.toString() );
	}

}
