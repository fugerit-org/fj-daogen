package tool;
import java.util.Properties;

import org.fugerit.java.core.cli.ArgUtils;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.daogen.base.tool.DaoGenToolHandler;

/**
 * 
 * 
 * @author fugerit79
 *
 */
public class DaoGen {

	public static void main( String[] args ) {
		SafeFunction.apply( () -> {
			Properties params = ArgUtils.getArgs( args, true );
			DaoGenToolHandler.handle( params );
		} );
	}
	
}
