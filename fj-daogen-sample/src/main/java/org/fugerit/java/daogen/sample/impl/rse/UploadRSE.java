package org.fugerit.java.daogen.sample.impl.rse;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.fugerit.java.core.db.daogen.BasicRSExtractor;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * UploadRSE, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class UploadRSE extends BasicRSExtractor<ModelUpload> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 807254852474L;

	@Override
	public ModelUpload extractNext( ResultSet rs ) throws SQLException { 
		HelperUpload current = new HelperUpload();
		current.setId( rs.getBigDecimal( "ID" )  );
		current.setDateInsert( rs.getTimestamp( "DATE_INSERT" )  );
		current.setDateUpdate( rs.getTimestamp( "DATE_UPDATE" )  );
		try { 
			current.setContent( org.fugerit.java.core.db.daogen.ByteArrayDataHandler.newHandlerPreload( rs.getBlob( "CONTENT" ) )  );
		} catch (Exception e) {
			throw new SQLException( "Errore estrazione campo : CONTENT", e );
		}
		return current;
	} 
}
