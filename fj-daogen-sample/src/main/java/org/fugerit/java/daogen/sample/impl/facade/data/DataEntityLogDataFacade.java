package org.fugerit.java.daogen.sample.impl.facade.data;

import java.math.BigDecimal;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.daogen.sample.impl.rse.LogDataRSE;
import org.fugerit.java.daogen.sample.def.facade.LogDataFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.BasicDAOHelper;
import org.fugerit.java.core.db.daogen.BasicDataFacade;
import org.fugerit.java.core.db.daogen.InsertHelper;
import org.fugerit.java.core.db.daogen.UpdateHelper;
import org.fugerit.java.core.db.daogen.DeleteHelper;
import org.fugerit.java.core.db.daogen.SelectHelper;
import org.fugerit.java.core.db.dao.DAOException;
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

 	public final static String COL_ID = "ID";
 	public final static String COL_LOG_TIME = "LOG_TIME";
 	public final static String COL_INFO = "INFO";

	/* loadById( context, id ) e loadAll( context ) ereditati da BasicDataFacade */

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

	@Override
	public BasicDaoResult<ModelLogData> create( DAOContext context, ModelLogData model ) throws DAOException {
		BasicDaoResult<ModelLogData> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelLogData> daoHelper = new BasicDAOHelper<>( context );
		if ( model.getId() == null ) { 
			model.setId( daoHelper.newSequenceValue( SEQUENCE_NAME) ); 
		} 
		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );
		query.addParam( COL_ID, model.getId() );
		query.addParam( COL_LOG_TIME, model.getLogTime() );
		query.addParam( COL_INFO, model.getInfo() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelLogData> updateById( DAOContext context, ModelLogData model ) throws DAOException {
		BasicDaoResult<ModelLogData> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelLogData> daoHelper = new BasicDAOHelper<>( context );
		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );
		query.addSetParam( COL_ID, model.getId() );
		query.addSetParam( COL_LOG_TIME, model.getLogTime() );
		query.addSetParam( COL_INFO, model.getInfo() );
		query.andWhereParam( COL_ID, model.getId() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelLogData> deleteById( DAOContext context, BigDecimal id ) throws DAOException {
		BasicDaoResult<ModelLogData> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelLogData> daoHelper = new BasicDAOHelper<>( context );
		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );
		query.andWhereParam( COL_ID, id );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, null, result);
		return result;
	}

}
