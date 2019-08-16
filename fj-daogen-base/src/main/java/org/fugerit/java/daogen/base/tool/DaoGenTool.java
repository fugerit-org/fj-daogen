package org.fugerit.java.daogen.base.tool;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoGenTool {

	private static final Logger logger = LoggerFactory.getLogger( DaoGenTool.class ); 
	
	public static final String ARG_DAOGEN_CONFIG = "daogen-config"; 
	
	public static void main( String[] args ) {
		try {
			Properties params = ArgUtils.getArgs( args , true );
			String daogenConfig = params.getProperty( ARG_DAOGEN_CONFIG );
			if ( StringUtils.isEmpty( daogenConfig ) ) {
				throw new ConfigException( "Argument "+ARG_DAOGEN_CONFIG+" must be provided" );
			} else {
				logger.info( "DAOGEN START!" );
				try ( FileInputStream fis = new FileInputStream( new File( daogenConfig ) ) ) {
					DaoGenFacade.generate( fis );
				}
				logger.info( "DAOGEN END!" );
			}
		} catch (Exception e) {
			logger.error( "Error "+e, e );
		}
	}
	
}
