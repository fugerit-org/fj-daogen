package org.fugerit.java.daogen.base.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.ConnectionFactoryImpl;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.daogen.base.config.DaogenFacade;
import org.fugerit.java.daogen.base.config.DaogenConfigDump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoGenToolHandler {

	private DaoGenToolHandler() {}
	
	private static final Logger logger = LoggerFactory.getLogger( DaoGenToolHandler.class ); 
	
	public static final String ARG_DAOGEN_CONFIG = "daogen-config";
	
	public static final String ARG_DB_CONFIG = "db-config";
	
	public static final String ARG_TABLE_LIST = "table-list";
	public static final String ARG_TABLE_LIST_DEFAULT = DaogenConfigDump.PARAM_TABLE_LIST_ALL;
	
	public static final String ARG_ACTION = "action";
	public static final String ARG_ACTION_DAOGEN = "daogen";
	public static final String ARG_ACTION_DUMP = "dump";
	
	public static void handle( Properties params ) {
		String action = params.getProperty( ARG_ACTION );
		if ( ARG_ACTION_DAOGEN.equalsIgnoreCase( action ) ) {
			handleDaogen(params);
		} else if ( ARG_ACTION_DUMP.equalsIgnoreCase( action ) ) {
			handleDump(params);
		} else {
			throw new ConfigRuntimeException( "Reuired parameter : "+ARG_ACTION+" ("+ARG_ACTION_DAOGEN+"|"+ARG_ACTION_DUMP+")" );
		}
	}
	
	public static void  handleDaogen( Properties params ) {
		try {
			String daogenConfig = params.getProperty( ARG_DAOGEN_CONFIG );
			if ( StringUtils.isEmpty( daogenConfig ) ) {
				throw new ConfigException( "Argument "+ARG_DAOGEN_CONFIG+" must be provided" );
			} else {
				logger.info( "DAOGEN START!" );
				try ( FileInputStream fis = new FileInputStream( new File( daogenConfig ) ) ) {
					DaogenFacade.generate( fis );
				}
				logger.info( "DAOGEN END!" );
			}
		} catch (Exception e) {
			throw ConfigRuntimeException.convertExMethod("handleDaogen", e);
		}
	}
	
	public static void  handleDump( Properties params ) {
		try {
			String dbConfig = params.getProperty( ARG_DB_CONFIG );
			String daogenConfig = params.getProperty( ARG_DAOGEN_CONFIG );
			String tableList = params.getProperty( ARG_TABLE_LIST, ARG_TABLE_LIST_DEFAULT );
			if ( dbConfig == null || daogenConfig == null ) {
				throw new ConfigException( "Missing required parameter "+ARG_DAOGEN_CONFIG+", "+ARG_DB_CONFIG );
			}
			Properties dbProps = PropsIO.loadFromFile( dbConfig );
			ConnectionFactory cf = ConnectionFactoryImpl.newInstance( dbProps );
			try ( FileWriter writer = new FileWriter( new File( daogenConfig ) ) ) {
				List<String> tableNameList = Arrays.asList( tableList.split( "," ) );
				DaogenConfigDump.dumpConfig( cf , params, writer, tableNameList );	
			}
		} catch (Exception e) {
			throw ConfigRuntimeException.convertExMethod("handleDump", e);
		}
	}
	
}
