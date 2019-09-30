package org.fugerit.java.daogen.sample.impl.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelUser;
import org.fugerit.java.daogen.sample.impl.helper.HelperUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UserRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UserRSE extends BasicRSExtractor<ModelUser> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 577630883979L;

	public static final UserRSE DEFAULT = new UserRSE();

	@Override
	public ModelUser extractNext( ResultSet rs ) throws SQLException { 
		HelperUser current = new HelperUser();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setUsername( rs.getString( "USERNAME" )  );
		current.setPassword( rs.getString( "PASSWORD" )  );
		current.setLastLogin( rs.getTimestamp( "LAST_LOGIN" )  );
		current.setDateInsert( rs.getTimestamp( "DATE_INSERT" )  );
		current.setDateUpdate( rs.getTimestamp( "DATE_UPDATE" )  );
		current.setState( rs.getBigDecimal( "STATE" )  );
		return current;
	} 
}
