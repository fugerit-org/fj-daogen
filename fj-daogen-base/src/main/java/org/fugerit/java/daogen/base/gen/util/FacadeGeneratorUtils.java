package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;

public class FacadeGeneratorUtils {
	
	public static final String ATT_ENTITY_FACADE_MODE_COMPLETE = "complete";
	
	public static final String ATT_ENTITY_FACADE_MODE_SELECT = "select";
	
	public static final String ATT_ENTITY_FACADE_MODE_INSERT = "insert";
	
	public static final String ATT_ENTITY_FACADE_MODE_DELETE = "delete";
	
	public static final String ATT_ENTITY_FACADE_MODE_UPDATE = "update";
	
	public static final String ATT_ENTITY_FACADE_MODE_NONE = "none";
	
	public static final String ATT_ENTITY_FACADE_MODE_DEFAULT = ATT_ENTITY_FACADE_MODE_COMPLETE;
	
	public static boolean isFacadeModeComplete( DaogenCatalogEntity entity ) {
		return StringUtils.isEmpty( entity.getFacadeMode() ) || entity.getFacadeMode().equalsIgnoreCase( ATT_ENTITY_FACADE_MODE_COMPLETE );
	}
	
	public static boolean isFacadeModeDelete( DaogenCatalogEntity entity ) {
		return isFacadeModeComplete( entity ) || contains( entity , ATT_ENTITY_FACADE_MODE_DELETE );
	}
	
	public static boolean isFacadeModeUpdate( DaogenCatalogEntity entity ) {
		return isFacadeModeComplete( entity ) || contains( entity , ATT_ENTITY_FACADE_MODE_UPDATE );
	}
	
	public static boolean isFacadeModeInsert( DaogenCatalogEntity entity ) {
		return isFacadeModeComplete( entity ) || contains( entity , ATT_ENTITY_FACADE_MODE_INSERT );
	}
	
	public static boolean isFacadeModeSelect( DaogenCatalogEntity entity ) {
		return isFacadeModeComplete( entity ) || contains( entity , ATT_ENTITY_FACADE_MODE_SELECT );
	}
	
	private static boolean contains( DaogenCatalogEntity entity, String value ) {
		return entity.getFacadeMode() != null && entity.getFacadeMode().toLowerCase().contains( value );
	}
	
	public static boolean isFacadeGenerate( DaogenCatalogEntity entity ) {
		return !ATT_ENTITY_FACADE_MODE_NONE.equalsIgnoreCase( entity.getFacadeMode() );
	}
	
	
}
