package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.xml.ListMapConfig;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;

public class DaogenCatalogEntity extends ListMapConfig<DaogenCatalogField> {

	public static final String ATT_ID = "id";
	public static final String ATT_NAME = "name";
	public static final String ATT_SCHEMA = "schema";
	public static final String ATT_CATALOG = "catalog";
	public static final String ATT_PRIMARY_KEY = "primaryKey";
	public static final String ATT_FOREIGN_KEYS = "foreigneys";
	public static final String ATT_COMMENTS = "comments";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440733564820863888L;

	public DaogenCatalogEntity() {
		
	}
	
	private String catalog;
	
	private String schema;
	
	private String name;
	
	private String comments;
	
	private String primaryKey;
	
	private String foreignKeys;

	public String getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(String foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toClassName() {
		return GeneratorNameHelper.toClassName( this.getName() );
	}
	
	public String describe() {
		 List<String> list = new ArrayList<String>();
		 list.add( StringUtils.concat( ":" , ATT_NAME, this.getName() ) );
		 list.add( StringUtils.concat( ":" , ATT_SCHEMA, this.getSchema() ) );
		 list.add( StringUtils.concat( ":" , ATT_CATALOG, this.getCatalog() ) );
		 list.add( StringUtils.concat( ":" , ATT_PRIMARY_KEY, this.getPrimaryKey() ) );
		 list.add( StringUtils.concat( ":" , ATT_FOREIGN_KEYS, this.getForeignKeys() ) );
		 list.add( StringUtils.concat( ":" , ATT_COMMENTS, this.getComments() ) );
		 return StringUtils.concat(  ",", list );
	}
	
}
