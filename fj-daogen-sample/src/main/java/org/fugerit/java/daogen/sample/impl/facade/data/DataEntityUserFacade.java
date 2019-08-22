package org.fugerit.java.daogen.sample.impl.facade.data;

import org.fugerit.java.daogen.sample.def.model.ModelUser;
import org.fugerit.java.daogen.sample.impl.rse.UserRSE;
import org.fugerit.java.daogen.sample.def.facade.UserFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityUserFacade;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.BasicDAOHelper;
import org.fugerit.java.core.db.daogen.BasicDataFacade;
import org.fugerit.java.core.db.daogen.SelectHelper;
import org.fugerit.java.core.db.daogen.InsertHelper;
import org.fugerit.java.core.db.daogen.UpdateHelper;
import org.fugerit.java.core.db.daogen.DeleteHelper;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * DataEntityUserFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class DataEntityUserFacade extends BasicDataFacade<ModelUser> implements EntityUserFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 576230448583L;

	public DataEntityUserFacade() {
		super( "PUBLIC.FUGERIT.USER", UserRSE.DEFAULT );
	}

 	public final static String SEQUENCE_NAME = "seq_id_fugerit";

 	@Override
 	public String getSequenceName() {
 		return SEQUENCE_NAME;
 	}

 	public final static String COL_ID = "ID";
 	public final static String COL_USERNAME = "USERNAME";
 	public final static String COL_PASSWORD = "PASSWORD";
 	public final static String COL_LAST_LOGIN = "LAST_LOGIN";
 	public final static String COL_DATE_INSERT = "DATE_INSERT";
 	public final static String COL_DATE_UPDATE = "DATE_UPDATE";
 	public final static String COL_STATE = "STATE";

	/* loadAll( context ) is inherited from BasicDataFacade */

	@Override
	public BasicDaoResult<ModelUser> loadAllByFinder( DAOContext context, UserFinder finder ) throws DAOException {
		BasicDaoResult<ModelUser> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUser> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );
		query.andEqualParam( COL_ID, finder.getId() );
		if ( finder.getModel() != null ) {
			ModelUser model = finder.getModel();
			query.andEqualParam( COL_ID, model.getId() );
			query.andEqualParam( COL_USERNAME, model.getUsername() );
			query.andEqualParam( COL_PASSWORD, model.getPassword() );
			query.andEqualParam( COL_LAST_LOGIN, model.getLastLogin() );
			query.andEqualParam( COL_DATE_INSERT, model.getDateInsert() );
			query.andEqualParam( COL_DATE_UPDATE, model.getDateUpdate() );
			query.andEqualParam( COL_STATE, model.getState() );
		}
		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); 
		return result;
	}

	@Override
	public BasicDaoResult<ModelUser> create( DAOContext context, ModelUser model ) throws DAOException {
		BasicDaoResult<ModelUser> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUser> daoHelper = new BasicDAOHelper<>( context );
		if ( model.getId() == null ) { 
			model.setId( this.generateId( context ) ); 
		} 
		//  default-column-time-insert : true - i will set insert time
		model.setDateInsert( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );
		query.addParam( COL_ID, model.getId() );
		query.addParam( COL_USERNAME, model.getUsername() );
		query.addParam( COL_PASSWORD, model.getPassword() );
		query.addParam( COL_LAST_LOGIN, model.getLastLogin() );
		query.addParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addParam( COL_STATE, model.getState() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public ModelUser loadById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		ModelUser result = null;
		BasicDAOHelper<ModelUser> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );
		query.andEqualParam( COL_ID, id );
		result = daoHelper.loadOneHelper( query, this.getRse() );
		return result;
	}

	@Override
	public BasicDaoResult<ModelUser> deleteById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		BasicDaoResult<ModelUser> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUser> daoHelper = new BasicDAOHelper<>( context );
		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );
		query.andWhereParam( COL_ID, id );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, null, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelUser> updateById( DAOContext context, ModelUser model ) throws DAOException {
		BasicDaoResult<ModelUser> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUser> daoHelper = new BasicDAOHelper<>( context );
		//  default-column-time-update : true - i will set update time
		model.setDateUpdate( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );
		query.addSetParam( COL_USERNAME, model.getUsername() );
		query.addSetParam( COL_PASSWORD, model.getPassword() );
		query.addSetParam( COL_LAST_LOGIN, model.getLastLogin() );
		query.addSetParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addSetParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addSetParam( COL_STATE, model.getState() );
		query.andWhereParam( COL_ID, model.getId() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

}
