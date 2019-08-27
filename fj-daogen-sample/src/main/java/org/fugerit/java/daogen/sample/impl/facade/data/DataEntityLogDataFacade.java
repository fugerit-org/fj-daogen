package org.fugerit.java.daogen.sample.impl.facade.data;

import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.daogen.sample.def.facade.LogDataFinder;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.sample.impl.rse.LogDataRSE;
import org.fugerit.java.core.db.daogen.SelectHelper;
import org.fugerit.java.core.db.daogen.BasicDataFacade;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.BasicDAOHelper;
import org.fugerit.java.core.db.daogen.BasicDaoResult;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * DataEntityLogDataFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class DataEntityLogDataFacade extends BasicDataFacade<ModelLogData> implements EntityLogDataFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 84221996652L;

	public DataEntityLogDataFacade() {
		super( "PUBLIC.FUGERIT.LOG_DATA", LogDataRSE.DEFAULT );
	}

 	public final static String SEQUENCE_NAME = "seq_id_fugerit";

 	@Override
 	public String getSequenceName() {
 		return SEQUENCE_NAME;
 	}

 	public final static String COL_ID = "ID";
 	public final static String COL_LOG_TIME = "LOG_TIME";
 	public final static String COL_INFO = "INFO";

	/* loadAll( context ) is inherited from BasicDataFacade */

	@Override
	public BasicDaoResult<ModelLogData> loadAllByFinder( DAOContext context, LogDataFinder finder ) throws DAOException {
		BasicDaoResult<ModelLogData> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelLogData> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );
		query.andEqualParam( COL_ID, finder.getId() );
		if ( finder.getModel() != null ) {
			ModelLogData model = finder.getModel();
			query.andEqualParam( COL_ID, model.getId() );
			query.andEqualParam( COL_LOG_TIME, model.getLogTime() );
			query.andEqualParam( COL_INFO, model.getInfo() );
		}
		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); 
		return result;
	}

}
