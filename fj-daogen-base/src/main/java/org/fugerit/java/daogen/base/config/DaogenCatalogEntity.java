package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.xml.ListMapConfig;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.collection.ListMapStringKey;

public class DaogenCatalogEntity extends ListMapConfig<DaogenCatalogField> {


	public static final String DEFAULT_ID_FIELD = "ID";
	
	public static final String ATT_ID = "id";
	public static final String ATT_NAME = "name";
	public static final String ATT_SCHEMA = "schema";
	public static final String ATT_CATALOG = "catalog";
	public static final String ATT_PRIMARY_KEY = "primaryKey";
	public static final String ATT_FOREIGN_KEYS = "foreigneys";
	public static final String ATT_COMMENTS = "comments";
	public static final String ATT_SEQUENCE_NAME = "sequenceName";
	public static final String ATT_ORDER_BY = "orderBy";
	public static final String ATT_MAP_TO_TABLE = "mapToTable";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440733564820863888L;

	public DaogenCatalogEntity() {
		this.relations = new ListMapStringKey<>();
	}
	
	private ListMapStringKey<DaogenCatalogRelation> relations;
	
	private String catalog;
	
	private String schema;
	
	private String name;
	
	private String comments;
	
	private String primaryKey;
	
	private String foreignKeys;
	
	private String sequenceName;
	
	private String orderBy;
	
	private String mapToTable;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

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
	
	public String getMapToTable() {
		return mapToTable;
	}

	public void setMapToTable(String mapToTable) {
		this.mapToTable = mapToTable;
	}

	public String toClassName() {
		return GeneratorNameHelper.toClassName( this.getName() );
	}

	public ListMapStringKey<DaogenCatalogRelation> getRelations() {
		return relations;
	}

	public String describe() {
		 List<String> list = new ArrayList<String>();
		 list.add( StringUtils.concat( ":" , ATT_NAME, this.getName() ) );
		 list.add( StringUtils.concat( ":" , ATT_SCHEMA, this.getSchema() ) );
		 list.add( StringUtils.concat( ":" , ATT_CATALOG, this.getCatalog() ) );
		 list.add( StringUtils.concat( ":" , ATT_PRIMARY_KEY, this.getPrimaryKey() ) );
		 list.add( StringUtils.concat( ":" , ATT_FOREIGN_KEYS, this.getForeignKeys() ) );
		 list.add( StringUtils.concat( ":" , ATT_COMMENTS, this.getComments() ) );
		 list.add( StringUtils.concat( ":" , ATT_SEQUENCE_NAME, this.getSequenceName() ) );
		 list.add( StringUtils.concat( ":" , ATT_ORDER_BY, this.getOrderBy() ) );
		 list.add( StringUtils.concat( ":" , ATT_MAP_TO_TABLE, this.getMapToTable() ) );
		 list.add( StringUtils.concat( ":" , DaogenCatalogConfig.ATT_DAOGEN_RELATION, this.getRelations().toString() ) );
		 return StringUtils.concat(  ",", list );
	}
	
	public boolean containsDefaultId() {
		return this.get( DEFAULT_ID_FIELD.toLowerCase() ) != null || this.get( DEFAULT_ID_FIELD.toUpperCase() ) != null;
	}
	
}
