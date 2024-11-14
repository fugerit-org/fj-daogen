package org.fugerit.java.daogen.sample.impl.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;
import org.fugerit.java.daogen.sample.impl.helper.HelperUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserDataRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserDataRSE extends BasicRSExtractor<ModelUserData> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 673296869401L;

	@Override
	public ModelUserData extractNext( ResultSet rs ) throws SQLException { 
		HelperUserData current = new HelperUserData();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setUsername( rs.getString( "USERNAME" )  );
		current.setPassword( rs.getString( "PASSWORD" )  );
		current.setLastLogin( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( rs.getTimestamp( "LAST_LOGIN" ) )  );
		current.setDateInsert( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( rs.getTimestamp( "DATE_INSERT" ) )  );
		current.setDateUpdate( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( rs.getTimestamp( "DATE_UPDATE" ) )  );
		current.setState( rs.getBigDecimal( "STATE" )  );
		return current;
	} 
}
