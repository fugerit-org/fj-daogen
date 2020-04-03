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
import org.fugerit.java.daogen.sample.def.facade.EntityUploadFacadeHelper;
import org.fugerit.java.daogen.sample.def.facade.UploadFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.rse.UploadRSE;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * DataEntityUploadFacadeHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class DataEntityUploadFacadeHelper extends BasicDataFacade<ModelUpload> implements EntityUploadFacadeHelper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 783597393751L;

	private final static String TABLE_NAME = "PUBLIC.FUGERIT.UPLOAD";

	public DataEntityUploadFacadeHelper() {
		super( TABLE_NAME, UploadRSE.DEFAULT, null );
	}

 	public final static String SEQUENCE_NAME = "seq_id_fugerit";

 	@Override
 	public String getSequenceName() {
 		return SEQUENCE_NAME;
 	}

 	public final static String COL_ID = "ID";
 	public final static String COL_DATE_INSERT = "DATE_INSERT";
 	public final static String COL_DATE_UPDATE = "DATE_UPDATE";
 	public final static String COL_CONTENT = "CONTENT";

	/* loadAll( context ) is inherited from BasicDataFacade */

	@Override
	public BasicDaoResult<ModelUpload> loadAllByFinder( DAOContext context, UploadFinder finder ) throws DAOException {
		BasicDaoResult<ModelUpload> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUpload> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getQueryView(), this.getTableName() );
		query.andEqualParam( COL_ID, finder.getId() );
		if ( finder.getModel() != null ) {
			ModelUpload model = finder.getModel();
			query.andEqualParam( COL_ID, model.getId() );
			query.andEqualParam( COL_DATE_INSERT, model.getDateInsert() );
			query.andEqualParam( COL_DATE_UPDATE, model.getDateUpdate() );
			query.andEqualParam( COL_CONTENT, model.getContent() );
		}
		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); 
		result.evaluateResultFromList(); 
		return result;
	}

	@Override
	public BasicDaoResult<ModelUpload> create( DAOContext context, ModelUpload model ) throws DAOException {
		BasicDaoResult<ModelUpload> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUpload> daoHelper = new BasicDAOHelper<>( context );
		if ( model.getId() == null ) { 
			model.setId( this.generateId( context ) ); 
		} 
		//  default-column-time-insert : true - i will set insert time
		model.setDateInsert( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		//  default-column-time-update : true - i will set update time
		model.setDateUpdate( model.getDateInsert() ); 
		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );
		query.addParam( COL_ID, model.getId() );
		query.addParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addParam( COL_CONTENT, model.getContent() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public ModelUpload loadById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		ModelUpload result = null;
		BasicDAOHelper<ModelUpload> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getQueryView(), this.getTableName() );
		query.andEqualParam( COL_ID, id );
		result = daoHelper.loadOneHelper( query, this.getRse() );
		return result;
	}

	@Override
	public BasicDaoResult<ModelUpload> deleteById( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		BasicDaoResult<ModelUpload> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUpload> daoHelper = new BasicDAOHelper<>( context );
		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );
		query.andWhereParam( COL_ID, id );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, null, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelUpload> updateById( DAOContext context, ModelUpload model ) throws DAOException {
		BasicDaoResult<ModelUpload> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelUpload> daoHelper = new BasicDAOHelper<>( context );
		//  default-column-time-update : true - i will set update time
		model.setDateUpdate( new java.sql.Timestamp( System.currentTimeMillis() ) ); 
		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );
		query.addSetParam( COL_DATE_INSERT, model.getDateInsert() );
		query.addSetParam( COL_DATE_UPDATE, model.getDateUpdate() );
		query.addSetParam( COL_CONTENT, model.getContent() );
		query.andWhereParam( COL_ID, model.getId() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

}
