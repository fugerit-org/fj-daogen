package org.fugerit.java.daogen.base.config;

import java.io.InputStream;

import org.fugerit.java.core.cfg.xml.CustomListCatalogConfig;

public class DaogenCatalogConfig extends CustomListCatalogConfig<DaogenCatalogField, DaogenCatalogEntity> {

	public static final String ATT_DAOGEN_ROOT = "daogen-config";
	
	public static final String ATT_DAOGEN_ENTITY = "entity";
	
	public static final String ATT_DAOGEN_FIELD = "field";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491328999494021347L;

	public DaogenCatalogConfig() {
		super( ATT_DAOGEN_ENTITY, ATT_DAOGEN_FIELD );
		this.getGeneralProps().setProperty( ATT_TYPE , DaogenCatalogField.class.getName() );
		this.getGeneralProps().setProperty( ATT_LIST_TYPE , DaogenCatalogEntity.class.getName() );
	}
	
	public String getGeneralProp( String key ) {
		return this.getGeneralProps().getProperty( key );
	}
	
	public static DaogenCatalogConfig loadConfig( InputStream input ) throws Exception {
		DaogenCatalogConfig config = new DaogenCatalogConfig();
		load( input , config );
		return config;
	}
	
}
