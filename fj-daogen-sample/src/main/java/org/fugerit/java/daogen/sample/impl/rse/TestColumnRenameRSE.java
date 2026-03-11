package org.fugerit.java.daogen.sample.impl.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestColumnRename;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * TestColumnRenameRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class TestColumnRenameRSE extends BasicRSExtractor<ModelTestColumnRename> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 164915904532L;

	@Override
	public ModelTestColumnRename extractNext( ResultSet rs ) throws SQLException { 
		HelperTestColumnRename current = new HelperTestColumnRename();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setRenamedFieldOne( rs.getString( "FIELD_ONE" )  );
		return current;
	} 
}
