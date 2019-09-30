package org.fugerit.java.daogen.base.gen;

import java.io.File;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;

public class DocOpenAPIV3RestGenerator extends DaogenBasicGenerator {


	public static final String KEY = "DocOpenAPIRestGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	

	@Override
	public void init(DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_DOC_OPENAPI ), 
				fullObjectName( "", entity.getId() ), 
				STYLE_CLASS, daogenConfig, entity );
		File file = this.getJavaFile();
		file = new File( file.getParentFile(), file.getName().replace( "java" , "yaml") );
		this.setJavaFile( file );
	}

	@Override
	public void generateBody() throws Exception {
		// donothing()
	}
	
	private static String prepareText( String s ) {
		String r = "";
		if ( s != null ) {
			r = s.replace( '\'' , ' ' );
		}
		return r;
	}
	
	@Override	
	public void generate() throws Exception {
		String urlBase = "/"+this.getCurrentEntity().getName().replaceAll( "_" , "" ).toLowerCase()+"/load";
		String description = "Comments : "+prepareText( this.getCurrentEntity().getComments() );
		String title = "OpenAPI load specification for "+prepareText( this.getCurrentEntity().getId() );
		String version = prepareText( this.getDaogenConfig().getGeneralProp( "gen-version" ) );
		String openapiHost = prepareText( this.getDaogenConfig().getGeneralProp( "openapi_host" ) );
		String openapiPath = prepareText( this.getDaogenConfig().getGeneralProp( "openapi_path" ) )+urlBase;
		DaogenCustomCode.addDocGenOpenAPI( "openapi.swagger.info.v3", "", this.getWriter(), 
				description, title, version, openapiHost, openapiPath );
		String baseType = GeneratorNameHelper.toClassName( this.getCurrentEntity().getName() );
		// primary key
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() ).setForLoadInterface();
			this.printlnWithTabs( 1, primaryKeyHelper.getUrlParams()+":" );
			this.printlnWithTabs( 2, "get:" );
			this.printlnWithTabs( 3, "summary: Get "+this.getCurrentEntity().getId()+" by primary key" );
			this.printlnWithTabs( 3, "parameters:" );
			for ( String currentField : primaryKeyHelper.getKeyFields() ) {
				addPathParam( 4, currentField );
			}
			this.addBasicResponse( 3 );
			this.printlnWithTabs( 8, "$ref: '#/components/schemas/"+baseType+"'" );
		}
		// load all
		this.printlnWithTabs( 1, "/all:" );
		this.printlnWithTabs( 2, "get:" );
		this.printlnWithTabs( 3, "summary: Get all "+this.getCurrentEntity().getId() );
		this.addBasicResponse( 3 );
		this.printlnWithTabs( 9, "type: array" );
		this.printlnWithTabs( 9, "items: " );
		this.printlnWithTabs( 10, "$ref: '#/components/schemas/"+baseType+"'" );
		this.printlnWithTabs( 0, "components: " );
		this.printlnWithTabs( 1, "schemas: " );
		this.printlnWithTabs( 2, baseType+": " );
		this.printObjectWithBasicIndent( 3 );
	}
	
	private void addPathParam( int indent, String currentField ) {
		this.printlnWithTabs( indent, "- name: "+currentField.toLowerCase() );
		this.printlnWithTabs( indent+1, "in: path" );
		this.printlnWithTabs( indent+1, "required: true" );
		this.printlnWithTabs( indent+1, "description: Value of field "+currentField );
		this.printlnWithTabs( indent+1, "schema:" );
		this.printlnWithTabs( indent+2, "type: string" );
	}
	
	private void addBasicResponse( int indent ) {
		this.printlnWithTabs( indent, "responses:" );
		this.printlnWithTabs( indent+1, "'200':" );
		this.printlnWithTabs( indent+2, "description: Success response" );
		this.printlnWithTabs( indent+2, "content:" );
		this.printlnWithTabs( indent+3, "application/json:" );
		this.printlnWithTabs( indent+4, "schema:" );
	}
	
	private void printObjectWithBasicIndent( int indent ) {
		this.printlnWithTabs( indent, "properties:" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.printlnWithTabs( indent+1, field.getId().toLowerCase()+":" );
			this.printlnWithTabs( indent+2, "type: string" );
			this.printlnWithTabs( indent+2, "example: ''" );
		}
	}

}
