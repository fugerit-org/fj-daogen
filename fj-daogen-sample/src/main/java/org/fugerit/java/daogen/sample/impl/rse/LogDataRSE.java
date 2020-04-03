package org.fugerit.java.daogen.sample.impl.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.daogen.sample.impl.helper.HelperLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LogDataRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class LogDataRSE extends BasicRSExtractor<ModelLogData> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 543407837219L;

	public static final LogDataRSE DEFAULT = new LogDataRSE();

	@Override
	public ModelLogData extractNext( ResultSet rs ) throws SQLException { 
		HelperLogData current = new HelperLogData();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setLogTime( rs.getTimestamp( "LOG_TIME" )  );
		current.setInfo( rs.getString( "INFO" )  );
		return current;
	} 
}
