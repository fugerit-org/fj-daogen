package org.fugerit.java.daogen.sample.impl.rse;

import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.impl.helper.HelperAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * AddressRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class AddressRSE extends BasicRSExtractor<ModelAddress> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 582538368203L;

	public static final AddressRSE DEFAULT = new AddressRSE();

	@Override
	public ModelAddress extractNext( ResultSet rs ) throws SQLException { 
		HelperAddress current = new HelperAddress();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setIdUser( rs.getBigDecimal( "ID_USER" )  );
		current.setDateInsert( rs.getTimestamp( "DATE_INSERT" )  );
		current.setDateUpdate( rs.getTimestamp( "DATE_UPDATE" )  );
		current.setInfo( rs.getString( "INFO" )  );
		return current;
	} 
}
