package org.fugerit.java.daogen.base.config;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.metadata.ColumnModel;
import org.fugerit.java.core.db.metadata.DataBaseModel;
import org.fugerit.java.core.db.metadata.ForeignKeyModel;
import org.fugerit.java.core.db.metadata.IndexModel;
import org.fugerit.java.core.db.metadata.MetaDataUtils;
import org.fugerit.java.core.db.metadata.TableModel;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DaogenConfigDump {

	private static Logger logger = LoggerFactory.getLogger( DaogenConfigDump.class );
	
	public static final String PARAM_TABLE_LIST = "table-list";
	public static final String PARAM_TABLE_LIST_ALL = "*";
	
	public static final String PARAM_CATALOG = "catalog";
	public static final String PARAM_SCHEMA = "schema";
	
	private static void addIfNotEmpty( String attName, String attValue, Element tag ) {
		if ( StringUtils.isNotEmpty( attValue ) ) {
			tag.setAttribute( attName , attValue );
		}
	}
	
	public static void dumpConfig( ConnectionFactory cf, Properties params, Writer writer, List<String> tableNameList ) throws Exception {
		String catalog = params.getProperty( PARAM_CATALOG );
		String schema = params.getProperty( PARAM_SCHEMA );
		DataBaseModel dbModel = MetaDataUtils.createModel( cf, catalog, schema, tableNameList );
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element root = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_ROOT );
		for ( TableModel tableModel : dbModel.getTableList() ) {
			Element currentEntityTag = doc.createElement( DaogenCatalogConfig.ATT_DAOGEN_ENTITY );
			currentEntityTag.setAttribute( DaogenCatalogEntity.ATT_ID , tableModel.getTableId().toIdString() );
			addIfNotEmpty( DaogenCatalogEntity.ATT_NAME , tableModel.getName(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_SCHEMA , tableModel.getSchema(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_CATALOG , tableModel.getCatalog(), currentEntityTag );
			addIfNotEmpty( DaogenCatalogEntity.ATT_COMMENTS, tableModel.getComment(), currentEntityTag );
			root.appendChild( currentEntityTag );
			logger.info( "current table : "+tableModel+" ("+tableModel.getComment()+")" );
			IndexModel primaryKey = tableModel.getPrimaryKey();
			if ( primaryKey != null ) {
				logger.info( "primary key : "+primaryKey+" - "+primaryKey.getColumnList().size() );
				List<String> temp = new ArrayList<String>();
				for ( ColumnModel columnModel : primaryKey.getColumnList() ) {
					temp.add( columnModel.getName() );
				}
				currentEntityTag.setAttribute( "primaryKey" , StringUtils.concat( ",", temp ) );
			}
			for ( IndexModel indexModel : tableModel.getIndexList() ) {
				logger.info( "index : "+indexModel );
			}
			List<String> fk = new ArrayList<String>();
			for ( ForeignKeyModel foreignKeyModel : tableModel.getForeignKeyList() ) {
				logger.info( "foreign key : "+foreignKeyModel );
				fk.add( foreignKeyModel.getForeignTableId().toIdString() );
			}
			if ( !fk.isEmpty() ) {
				currentEntityTag.setAttribute( "foreignKeys" , StringUtils.concat( ",", fk ) );
			}
			for ( ColumnModel columnModel : tableModel.getColumnList() ) {
				logger.info( "column : "+columnModel+" ("+columnModel.getComment()+")" );
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
		DOMIO.writeDOMIndent( root , writer );
	}
	
}
