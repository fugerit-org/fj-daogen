package org.fugerit.java.daogen.base.config;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.metadata.ColumnModel;
import org.fugerit.java.core.db.metadata.DataBaseModel;
import org.fugerit.java.core.db.metadata.ForeignKeyModel;
import org.fugerit.java.core.db.metadata.IndexModel;
import org.fugerit.java.core.db.metadata.MetaDataUtils;
import org.fugerit.java.core.db.metadata.TableId;
import org.fugerit.java.core.db.metadata.TableModel;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.collection.ListMapStringKey;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaogenConfigDump {
	
	private DaogenConfigDump() {}
	
	public static final String PARAM_TABLE_LIST = "table-list";
	public static final String PARAM_TABLE_LIST_ALL = "*";
	
	public static final String PARAM_CATALOG = "catalog";
	public static final String PARAM_SCHEMA = "schema";
	
	public static final String PARAM_ENTITY_TYPE = "entity-types";
	public static final String PARAM_ENTITY_TYPE_ALL = "all";
	public static final String PARAM_ENTITY_TYPE_DEFAULT = PARAM_ENTITY_TYPE_ALL;
	
	private static void addIfNotEmpty( String attName, String attValue, Element tag ) {
		if ( StringUtils.isNotEmpty( attValue ) ) {
			tag.setAttribute( attName , attValue );
		}
	}
	
	public static void dumpConfig( ConnectionFactory cf, Properties params, Writer writer, List<String> tableNameList ) throws ConfigException {
		dumpConfig(cf, params, writer, tableNameList, new Properties());
	}
	
	private static void dumpConfigFK( TableModel tableModel, Element currentEntityTag, ListMapStringKey<DaogenCatalogRelation> relations ) {
		List<String> fk = new ArrayList<String>();
		for ( ForeignKeyModel foreignKeyModel : tableModel.getForeignKeyList() ) {
			log.info( "foreign key : {}", foreignKeyModel );
			fk.add( foreignKeyModel.getForeignTableId().toIdString() );
			DaogenCatalogRelation relation = new DaogenCatalogRelation();
			relation.setName( tableModel.getTableId().getTableName()+"_"+foreignKeyModel.getForeignTableId().getTableName() );
			relation.setMode( DaogenCatalogRelation.MODE_ONE );
			relation.setFrom( tableModel.getTableId().toIdString() );
			relation.setTo( foreignKeyModel.getForeignTableId().toIdString() );
			relation.setId( relation.getFrom()+"_"+relation.getTo() );
			relation.setComment( "Dump generated relation" );
			relations.add( relation );
			List<String> currentKey = new ArrayList<>();
			for ( ColumnModel col : foreignKeyModel.internalColumnList( tableModel ) ) {
				currentKey.add( col.getName() );
			}
			relation.setKey( StringUtils.concat( ",", currentKey ) );
		}
		if ( !fk.isEmpty() ) {
			currentEntityTag.setAttribute( "foreignKeys" , StringUtils.concat( ",", fk ) );
		}
	}
	
	private static void dumpConfigColumns( TableModel tableModel, Element currentEntityTag, Document doc ) {
		for ( ColumnModel columnModel : tableModel.getColumnList() ) {
			log.info( "column : {} ({})", columnModel, columnModel.getComment() );
			Element currentFieldTag = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_FIELD );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_ID , columnModel.getName() );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_COMMENTS , columnModel.getComment() );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_SQL_TYPE , String.valueOf( columnModel.getTypeSql() ) );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_SQL_TYPE_NAME , columnModel.getTypeName() );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_SIZE , String.valueOf( columnModel.getSize() ) );
			String nullable = "unknown";
			if ( columnModel.getNullable() == ColumnModel.NULLABLE_TRUE ) {
				nullable = "yes";
			} else if ( columnModel.getNullable() == ColumnModel.NULLABLE_FALSE ) { 
				nullable = "no";
			}
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_NULLABLE , nullable );
			currentFieldTag.setAttribute( DaogenCatalogField.ATT_JAVA_TYPE , columnModel.getJavaType() );
			currentEntityTag.appendChild( currentFieldTag );
		}
	}
	
	private static void dumpRelations( ListMapStringKey<DaogenCatalogRelation> relations, Document doc, Element root ) {
		for ( DaogenCatalogRelation rel : relations ) {
			Element currentRelationTag = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_RELATION );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_ID , rel.getId() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_NAME , rel.getName() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_FROM , rel.getFrom() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_TO , rel.getTo() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_KEY, rel.getKey() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_MODE , rel.getMode() );
			currentRelationTag.setAttribute( DaogenCatalogRelation.ATT_COMMENT , rel.getComment() );
			root.appendChild( currentRelationTag );
		}
	}
	
	public static void dumpConfigWorker( DataBaseModel dbModel, Document doc, Element root, Properties mapToTables ) throws ConfigException {
		ListMapStringKey<DaogenCatalogRelation> relations = new ListMapStringKey<>();
		for ( TableModel tableModel : dbModel.getTableList() ) {
			Element currentEntityTag = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_ENTITY );
			String tableName = tableModel.getName();
			String mapToTable = mapToTables.getProperty( tableName );
			if ( mapToTable != null ) {
				TableId tableId = tableModel.getTableId();
				tableId.setTableName( mapToTable );
				addIfNotEmpty( DaogenCatalogEntity.ATT_MAP_TO_TABLE , tableName, currentEntityTag );
			}
			currentEntityTag.setAttribute( DaogenCatalogEntity.ATT_ID , tableModel.getTableId().toIdString() );
			addIfNotEmpty( DaogenCatalogEntity.ATT_NAME , tableModel.getName(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_SCHEMA , tableModel.getSchema(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_CATALOG , tableModel.getCatalog(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_COMMENTS, tableModel.getComment(), currentEntityTag );
			root.appendChild( currentEntityTag );
			log.info( "current table : {} ({})", tableModel, tableModel.getComment() );
			IndexModel primaryKey = tableModel.getPrimaryKey();
			if ( primaryKey != null ) {
				log.info( "primary key : {} - {}", primaryKey, primaryKey.getColumnList().size() );
				List<String> temp = new ArrayList<String>();
				for ( ColumnModel columnModel : primaryKey.getColumnList() ) {
					temp.add( columnModel.getName() );
				}
				currentEntityTag.setAttribute( "primaryKey" , StringUtils.concat( ",", temp ) );
			}
			for ( IndexModel indexModel : tableModel.getIndexList() ) {
				log.info( "index : {}", indexModel );
			}
			dumpConfigFK(tableModel, currentEntityTag, relations);
			dumpConfigColumns(tableModel, currentEntityTag, doc);
		}
		dumpRelations(relations, doc, root);
	}
	
	public static void dumpConfig( ConnectionFactory cf, Properties params, Writer writer, List<String> tableNameList, Properties mapToTables ) throws ConfigException {
		try {
			String catalog = params.getProperty( PARAM_CATALOG );
			String schema = params.getProperty( PARAM_SCHEMA );
			String paramEntityType = params.getProperty( PARAM_ENTITY_TYPE , PARAM_ENTITY_TYPE_DEFAULT );
			String[] types = { paramEntityType };
			if ( PARAM_ENTITY_TYPE_ALL.equalsIgnoreCase( paramEntityType )  ) {
				types = MetaDataUtils.TYPES_ALL;
			}
			DataBaseModel dbModel = MetaDataUtils.createModel( cf, catalog, schema, tableNameList, types );
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_ROOT );
			dumpConfigWorker(dbModel, doc, root, mapToTables);
			DOMIO.writeDOMIndent( root , writer );
		} catch (Exception e) {
			throw ConfigException.convertExMethod( "dumpConfig", e );
		}
	}
	
}
