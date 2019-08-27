package org.fugerit.java.daogen.sample.impl.facade.data;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDAOHelper;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.core.db.daogen.BasicDataFacade;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.DeleteHelper;
import org.fugerit.java.core.db.daogen.InsertHelper;
import org.fugerit.java.core.db.daogen.SelectHelper;
import org.fugerit.java.core.db.daogen.UpdateHelper;
import org.fugerit.java.daogen.sample.def.facade.AddressFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.impl.rse.AddressRSE;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * DataEntityAddressFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class DataEntityAddressFacade extends BasicDataFacade<ModelAddress> implements EntityAddressFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 209491675380L;

	public DataEntityAddressFacade() {
		super( "PUBLIC.FUGERIT.ADDRESS", AddressRSE.DEFAULT );
	}

 	public final static String SEQUENCE_NAME = "seq_id_fugerit";

 	@Override
 	public String getSequenceName() {
 		return SEQUENCE_NAME;
 	}

 	public final static String COL_ID = "ID";
 	public final static String COL_ID_USER = "ID_USER";
 	public final static String COL_DATE_INSERT = "DATE_INSERT";
 	public final static String COL_DATE_UPDATE = "DATE_UPDATE";
 	public final static String COL_INFO = "INFO";

	/* loadAll( context ) is inherited from BasicDataFacade */

	@Override
	public BasicDaoResult<ModelAddress> loadAllByFinder( DAOContext context, AddressFinder finder ) throws DAOException {
		BasicDaoResult<ModelAddress> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelAddress> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );
		query.andEqualParam( COL_ID, finder.getId() );
		if ( finder.getModel() != null ) {
			ModelAddress model = finder.getModel();
			query.andEqualParam( COL_ID, model.getId() );
			query.andEqualParam( COL_ID_USER, model.getIdUser() );
			query.andEqualParam( COL_DATE_INSERT, model.getDateInsert() );
			query.andEqualParam( COL_DATE_UPDATE, model.getDateUpdate() );
			query.andEqualParam( COL_INFO, model.getInfo() );
		}
		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); 
		return result;
	}

	@Override
	public BasicDaoResult<ModelAddress> create( DAOContext context, ModelAddress model ) throws DAOException {
		BasicDaoResult<ModelAddress> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelAddress> daoHelper = new BasicDAOHelper<>( context );
		if ( model.getId() == null ) { 
			model.setId( this.generateId( context ) ); 
		} 
		//  default-column-time-insert : true - i will set insert time
		model.setDateInsert( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );
		query.addParam( COL_ID, model.getId() );
		query.addParam( COL_ID_USER, model.getIdUser() );
		query.addParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addParam( COL_INFO, model.getInfo() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public ModelAddress loadById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		ModelAddress result = null;
		BasicDAOHelper<ModelAddress> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );
		query.andEqualParam( COL_ID, id );
		result = daoHelper.loadOneHelper( query, this.getRse() );
		return result;
	}

	@Override
	public BasicDaoResult<ModelAddress> deleteById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		BasicDaoResult<ModelAddress> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelAddress> daoHelper = new BasicDAOHelper<>( context );
		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );
		query.andWhereParam( COL_ID, id );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, null, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelAddress> updateById( DAOContext context, ModelAddress model ) throws DAOException {
		BasicDaoResult<ModelAddress> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelAddress> daoHelper = new BasicDAOHelper<>( context );
		//  default-column-time-update : true - i will set update time
		model.setDateUpdate( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );
		query.addSetParam( COL_ID_USER, model.getIdUser() );
		query.addSetParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addSetParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addSetParam( COL_INFO, model.getInfo() );
		query.andWhereParam( COL_ID, model.getId() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

}
