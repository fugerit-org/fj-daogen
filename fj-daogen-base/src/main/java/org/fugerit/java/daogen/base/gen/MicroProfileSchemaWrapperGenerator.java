package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.*;
import org.fugerit.java.daogen.base.gen.util.WrapperUtils;

import java.io.IOException;

public class MicroProfileSchemaWrapperGenerator extends DaogenBasicGenerator {

	public static final String KEY = "MicroProfileSchemaWrapperGenerator";

	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ),
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER_MICROPROFILE ), DaogenCatalogConstants.mpSchemaName( entity ) ),
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( "org.eclipse.microprofile.openapi.annotations.media.Schema" );
		WrapperUtils.init( daogenConfig, entity, this );
		new DaogenBasicDecorator() {
			@Override
			public void addBeforeClass() throws DAOException {
				super.addBeforeClass();
				if ( StringUtils.isNotEmpty( entity.getComments() ) ) {
					this.accessGenerator().println( String.format( "@Schema( description = \"%s\")" , entity.getComments() ) );
				}
			}
		}.init( this );
	}

	@Override
	public String getEntityWrapperName() {
		return DaogenCatalogConstants.mpSchemaName( this.getCurrentEntity() );
	}

	@Override
	public void generateDaogenBody() throws IOException {
		WrapperUtils.generateBody( this, f -> {
			if (StringUtils.isNotEmpty( f.getComments() ) ) {
				String base = "@Schema( description = \"%s\" %s)";
				String extra = "";
				if ( StringUtils.isNotEmpty( f.getExampleData() ) ) {
					extra = String.format( ", example =\"%s\"", f.getExampleData() );
				}
				return String.format( base , f.getComments(), extra );
			} else {
				return null;
			}
		} );
	}
	
}
