package org.fugerit.java.daogen.base.tool;

import java.util.Properties;

import org.fugerit.java.core.cli.ArgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoGenTool {

	private static final Logger logger = LoggerFactory.getLogger( DaoGenTool.class ); 

	public static void main( String[] args ) {
		int exit = 0;
		try {
			Properties params = ArgUtils.getArgs( args, true );
			DaoGenToolHandler.handle( params );
		} catch (Exception e) {
			logger.error( "Error "+e, e );
			exit = 1;
		}
		System.exit( exit );
	}
	
}
