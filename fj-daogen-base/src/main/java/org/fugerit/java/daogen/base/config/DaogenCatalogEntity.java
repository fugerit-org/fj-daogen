package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fugerit.java.core.cfg.xml.IdConfigType;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.collection.ListMapStringKey;

import lombok.Getter;
import lombok.Setter;

public class DaogenCatalogEntity extends ListMapStringKey<DaogenCatalogField> implements IdConfigType {

	@Override
	public int hashCode() {
		// super class implementation is ok
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		// super class implementation is ok
		return super.equals(o);
	}
	
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
	public static final String ATT_QUERY_VIEW = "queryView";	// DEFAULT TO "SELECT * FROM ${catalog.schema.name}"
	public static final String ATT_FACADE_MODE = "facadeMode";
	
	public static final String ATT_STRUCT_SQL_TYPE = "structSqlType=";
	public static final String ATT_STRUCT_SQL_TYPE_USENAME = "(USE_NAME)";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4440733564820863888L;

	public DaogenCatalogEntity() {
		this.relations = new ListMapStringKey<>();
		this.allFields = new ListMapStringKey<>();
	}

	private ListMapStringKey<DaogenCatalogRelation> relations;

	@Getter @Setter private String id;
	
	private String catalog;
	
	private String schema;
	
	private String name;
	
	private String comments;
	
	private String primaryKey;
	
	private String foreignKeys;
	
	private String sequenceName;
	
	private String orderBy;
	
	private String mapToTable;
	
	private String queryView;
	
	private String facadeMode;
	
	private String structSqlType;

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

	public String getQueryView() {
		return queryView;
	}

	public void setQueryView(String queryView) {
		this.queryView = queryView;
	}

	public String toClassName() {
		return GeneratorNameHelper.toClassName( this.getName() );
	}

	public String getFacadeMode() {
		return facadeMode;
	}

	public void setFacadeMode(String facadeMode) {
		this.facadeMode = facadeMode;
	}

	public String getStructSqlType() {
		return structSqlType;
	}

	public void setStructSqlType(String structSqlType) {
		this.structSqlType = structSqlType;
	}

	public ListMapStringKey<DaogenCatalogRelation> getRelations() {
		return relations;
	}

	private static final String SEP = ":";
	
	public String describe() {
		 List<String> list = new ArrayList<>();
		 list.add( StringUtils.concat( SEP , ATT_NAME, this.getName() ) );
		 list.add( StringUtils.concat( SEP , ATT_SCHEMA, this.getSchema() ) );
		 list.add( StringUtils.concat( SEP , ATT_CATALOG, this.getCatalog() ) );
		 list.add( StringUtils.concat( SEP , ATT_PRIMARY_KEY, this.getPrimaryKey() ) );
		 list.add( StringUtils.concat( SEP , ATT_FOREIGN_KEYS, this.getForeignKeys() ) );
		 list.add( StringUtils.concat( SEP , ATT_COMMENTS, this.getComments() ) );
		 list.add( StringUtils.concat( SEP , ATT_SEQUENCE_NAME, this.getSequenceName() ) );
		 list.add( StringUtils.concat( SEP , ATT_ORDER_BY, this.getOrderBy() ) );
		 list.add( StringUtils.concat( SEP , ATT_MAP_TO_TABLE, this.getMapToTable() ) );
		 list.add( StringUtils.concat( SEP , ATT_QUERY_VIEW, this.getQueryView() ) );
		 list.add( StringUtils.concat( SEP , ATT_FACADE_MODE, this.getFacadeMode() ) );
		 list.add( StringUtils.concat( SEP , ATT_STRUCT_SQL_TYPE_USENAME, this.getStructSqlType() ) );
		 list.add( StringUtils.concat( SEP , DaogenCatalogConfig.ATT_DAOGEN_RELATION, this.getRelations().toString() ) );
		 return StringUtils.concat(  ",", list );
	}
	
	public boolean containsDefaultId() {
		return this.get( DEFAULT_ID_FIELD.toLowerCase() ) != null || this.get( DEFAULT_ID_FIELD.toUpperCase() ) != null;
	}

	private ListMapStringKey<DaogenCatalogField> allFields;

	public ListMapStringKey<DaogenCatalogField> getAllFields() {
		return allFields;
	}

	public void setAllFields(ListMapStringKey<DaogenCatalogField> allFields) {
		this.allFields = allFields;
	}

	protected void finishingTouch() {
		this.allFields.clear();
		this.allFields.addAll( this );
		Iterator<DaogenCatalogField> it = this.iterator();
		while ( it.hasNext() ) {
			if (BooleanUtils.isTrue( it.next().getVirtual() )) {
				it.remove();
			}
		}
	}

}
