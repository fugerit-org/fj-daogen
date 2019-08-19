package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;

public class GeneratorKeyHelper {

	private StringBuilder keyBuilder = new StringBuilder();
	
	private StringBuilder paramBuilder = new StringBuilder();
	
	public GeneratorKeyHelper( DaogenCatalogConfig config, DaogenCatalogEntity entity, String key ) {
		super();
		if ( StringUtils.isNotEmpty( key ) ) {
			String[] keyFields = key.split( "," );
			boolean first = true;
			for ( int k=0; k<keyFields.length; k++ ) {
				String currentField = keyFields[k];
				DaogenCatalogField field = entity.get( currentField );
				if ( first ) {
					first = false;
				} else {
					this.keyBuilder.append( ", " );
				}
				String fieldName = GeneratorNameHelper.toPropertyName( currentField );
				this.keyBuilder.append( config.getTypeMapper().mapForModel( field ) );
				this.keyBuilder.append( " " );
				this.keyBuilder.append( fieldName );
				this.paramBuilder.append( "	 * @param " );
				this.paramBuilder.append( fieldName );
				if ( StringUtils.isNotEmpty( field.getComments() ) ) {
					this.paramBuilder.append( field.getComments() );	
				} else {
					this.paramBuilder.append( " part of the key" );
				}
				this.paramBuilder.append( System.lineSeparator() );
			}
		}
		
	}
	
	public String getKeyParams() {
		return this.keyBuilder.toString();
	}
	
	public String getJavadocParams() {
		return this.paramBuilder.toString();
	}
	
}
