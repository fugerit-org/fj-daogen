package tool;
import java.util.Properties;

import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.daogen.base.tool.DaoGenToolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author fugerit79
 *
 */
public class DaoGen {

	private static final Logger logger = LoggerFactory.getLogger( DaoGen.class ); 

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
