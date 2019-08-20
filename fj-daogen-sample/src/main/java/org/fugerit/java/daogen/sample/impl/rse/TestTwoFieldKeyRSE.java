package org.fugerit.java.daogen.sample.impl.rse;

import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestTwoFieldKey;
import java.sql.ResultSet;
import java.sql.SQLException;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestTwoFieldKeyRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestTwoFieldKeyRSE extends BasicRSExtractor<ModelTestTwoFieldKey> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 164046700498L;

	public static final TestTwoFieldKeyRSE DEFAULT = new TestTwoFieldKeyRSE();

	@Override
	public ModelTestTwoFieldKey extractNext( ResultSet rs ) throws SQLException { 
		HelperTestTwoFieldKey current = new HelperTestTwoFieldKey();
		current.setIdOne( rs.getBigDecimal( "ID_ONE" )  );
		current.setIdTwo( rs.getBigDecimal( "ID_TWO" )  );
		current.setInfo( rs.getString( "INFO" )  );
		return current;
	} 
}
