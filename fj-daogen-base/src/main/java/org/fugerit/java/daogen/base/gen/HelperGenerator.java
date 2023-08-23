package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.compare.CheckEmptyHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCatalogRelation;
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
				String propertyName = GeneratorNameHelper.toPropertyName( relation.getName() );
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
					baseType = "java.util.List<"+baseType+">";
				}
				this.getWriter().println( TAB+"private "+baseType+" "+propertyName+";" );
				this.getWriter().println();
				// setter metthod
				this.getWriter().println( TAB+AT_OVERRIDE );
				this.getWriter().println( TAB+"public void set"+className+"( "+baseType+" value ) {" );
				this.getWriter().println( TAB_2+"this."+propertyName+" = value;" );
				this.getWriter().println( TAB+"}" );
				this.getWriter().println();
				// getter method
				this.getWriter().println( TAB+AT_OVERRIDE );
				this.getWriter().println( TAB+"public "+baseType+" get"+className+"() {" );
				this.getWriter().println( TAB_2+"return this."+propertyName+";" );
				this.getWriter().println( TAB+"}" );
				this.getWriter().println();
			}
		}
	}
	
	@Override
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		boolean relationLast = "true".equalsIgnoreCase( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
		if ( !relationLast ) {
			this.generateRelations();
		}
		this.getWriter().println( TAB+"/*" );
		this.getWriter().println( TAB+" * fields generated for entity attributes " );
		this.getWriter().println( TAB+" */" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			// property 
			String javaProperty = GeneratorNameHelper.toPropertyName( field.getId() );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String realJavaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			this.getWriter().println( TAB+"private "+realJavaType+" "+javaProperty+";" );
			this.getWriter().println();
			// setter method
			this.getWriter().println( TAB+AT_OVERRIDE );
			this.getWriter().println( TAB+"public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
			this.getWriter().println( TAB_2+"this."+javaProperty+" = value;" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
			// getter method
			this.getWriter().println( TAB+AT_OVERRIDE );
			this.getWriter().println( TAB+"public "+realJavaType+" get"+javaSuffix+"() {" );
			this.getWriter().println( TAB_2+"return this."+javaProperty+";" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
		}
		if ( relationLast ) {
			this.generateRelations();
		}
		// metodo toString()
		this.getWriter().println( TAB+AT_OVERRIDE );
		this.getWriter().println( TAB+"public String toString() {" );
		this.getWriter().println( TAB_2+"StringBuilder buffer = new StringBuilder();" );
		this.getWriter().println( TAB_2+"buffer.append( this.getClass().getSimpleName() );" );
		boolean firstColumn = true;
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String javaProperty = GeneratorNameHelper.toPropertyName( field.getId() );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			if ( firstColumn ) {
				this.getWriter().println( TAB_2+"buffer.append( \"["+javaProperty+"=\" );" );
				firstColumn = false;
			} else {
				this.getWriter().println( TAB_2+"buffer.append( \","+javaProperty+"=\" );" );
			}
			
			this.getWriter().println( TAB_2+"buffer.append( this.get"+javaSuffix+"() );" );
		}
		this.getWriter().println( TAB_2+"buffer.append( \"]\" );" );
		this.getWriter().println( TAB_2+"return buffer.toString();" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		if ( BooleanUtils.isTrue( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_CHECK_EMPTY_INTERFACE ) ) ) {
			this.getWriter().println( TAB+AT_OVERRIDE );
			this.getWriter().println( TAB+"public boolean isEmpty() {" );
			String start = "";
			String end = "";
			for ( int k=0; k<this.getCurrentEntity().size(); k++ ) {
				String javaSuffix = GeneratorNameHelper.toClassName( this.getCurrentEntity().get( k ).getId() );
				if ( k == 0 ) {
					start = "return ";
				} else if ( k == this.getCurrentEntity().size()-1 ) {
					start = " && ";
					end = ";";
				} else {
					start = " && ";
				}
				this.getWriter().println( TAB_2+""+start+" ( "+CheckEmptyHelper.class.getName()+".isEmpty( this.get"+javaSuffix+"() ) )"+end );
			}
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();	
		}
	}

	
	
}
