package org.fugerit.java.daogen.base.gen;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;

public class GeneratorKeyHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892117120996279405L;

	private DaogenCatalogConfig config;
	
	private DaogenCatalogEntity entity;
	
	private String key;
	
	private StringBuilder keyBuilder = new StringBuilder();
	
	private StringBuilder restBuilder = new StringBuilder();
	
	private StringBuilder paramBuilder = new StringBuilder();
	
	private StringBuilder forwardBuilder = new StringBuilder();
	
	private StringBuilder urlParams = new StringBuilder();
	
	private StringBuilder pathParams = new StringBuilder();
	
	private Set<String> keyFields;
	
	public GeneratorKeyHelper( DaogenCatalogConfig config, DaogenCatalogEntity entity, String key ) {
		this.config = config;
		this.entity = entity;
		this.key = key;
		if ( StringUtils.isNotEmpty( key ) ) {
			String[] split =  key.split( "," );
			this.keyFields = new HashSet<String>();
			for ( int k=0; k<split.length; k++ ) {
				this.keyFields.add( split[k] );	
			}
		}
	}
	
	public GeneratorKeyHelper setForLoadInterface() {
		this.reset();
		if ( StringUtils.isNotEmpty( key ) ) {
			boolean first = true;
			for ( String currentField : this.keyFields ) {
				DaogenCatalogField field = entity.get( currentField );
				if ( first ) {
					first = false;
				} else {
					this.keyBuilder.append( ", " );
					this.forwardBuilder.append( ", " );
					this.restBuilder.append( ", " );
					this.pathParams.append( ", " );
				}
				String fieldName = GeneratorNameHelper.toPropertyName( currentField );
				this.keyBuilder.append( config.getTypeMapper().mapForModel( field ) );
				this.keyBuilder.append( " " );
				this.keyBuilder.append( fieldName );
				this.paramBuilder.append( "	 * @param " );
				this.paramBuilder.append( fieldName );
				this.forwardBuilder.append( fieldName );
				if ( StringUtils.isNotEmpty( field.getComments() ) ) {
					this.paramBuilder.append( field.getComments() );	
				} else {
					this.paramBuilder.append( " part of the key" );
				}
				String javaType = config.getTypeMapper().mapForModel( field );
				this.paramBuilder.append( System.lineSeparator() );
				// rest data
				if ( javaType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
					this.restBuilder.append( "new java.math.BigDecimal(" );
					this.restBuilder.append( fieldName );
					this.restBuilder.append( ")" );
				} else {
					this.restBuilder.append( fieldName );	
				}
				urlParams.append( "/" );
				urlParams.append( fieldName );
				urlParams.append( "/{" );
				urlParams.append( fieldName );
				urlParams.append( "}" );
				pathParams.append( "@PathParam( \"" );
				pathParams.append( fieldName );
				pathParams.append( "\") String " );
				pathParams.append( fieldName );
			}
		}
		return this;
	}
	
	public GeneratorKeyHelper setForUpdateInterface() {
		this.reset();
		if ( StringUtils.isNotEmpty( key ) ) {
			this.keyBuilder.append( DaogenCatalogConstants.modelName( entity ) );
			this.keyBuilder.append( " model" );
			this.paramBuilder.append( "	 * @param 	model	entity to update" );
		}
		return this;
	}
	
	private void reset() {
		this.keyBuilder = new StringBuilder();
		this.paramBuilder = new StringBuilder();
		this.forwardBuilder = new StringBuilder();
		this.restBuilder = new StringBuilder();
		this.urlParams = new StringBuilder();
		this.pathParams = new StringBuilder();
	}
	
	public Set<String> getKeyFields() {
		return keyFields;
	}

	public String getKeyParams() {
		return this.keyBuilder.toString();
	}
	
	public String getJavadocParams() {
		return this.paramBuilder.toString();
	}
	
	public String getForwardParams() {
		return this.forwardBuilder.toString();
	}
	
	public String getRestParams() {
		return this.restBuilder.toString();
	}
	
	public String getUrlParams() {
		return this.urlParams.toString();
	}
	
	public String getPathParams() {
		return this.pathParams.toString();
	}
	
}

