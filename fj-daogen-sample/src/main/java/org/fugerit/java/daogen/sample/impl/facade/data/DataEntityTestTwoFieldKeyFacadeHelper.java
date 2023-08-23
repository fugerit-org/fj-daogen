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
import org.fugerit.java.daogen.sample.def.facade.EntityTestTwoFieldKeyFacadeHelper;
import org.fugerit.java.daogen.sample.def.facade.TestTwoFieldKeyFinder;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.rse.TestTwoFieldKeyRSE;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * DataEntityTestTwoFieldKeyFacadeHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class DataEntityTestTwoFieldKeyFacadeHelper extends BasicDataFacade<ModelTestTwoFieldKey> implements EntityTestTwoFieldKeyFacadeHelper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 426075792345L;

	private static final String TABLE_NAME = "PUBLIC.FUGERIT.TEST_TWO_FIELD_KEY";

	public DataEntityTestTwoFieldKeyFacadeHelper() {
		super( TABLE_NAME, TestTwoFieldKeyRSE.DEFAULT, null );
	}

	public DataEntityTestTwoFieldKeyFacadeHelper( String tableName, String queryView ) {
		super( tableName, TestTwoFieldKeyRSE.DEFAULT, queryView );
	}

 	public static final String SEQUENCE_NAME = "seq_id_fugerit";

 	@Override
 	public String getSequenceName() {
 		return SEQUENCE_NAME;
 	}

 	public static final String COL_ID_ONE = "ID_ONE";
 	public static final String COL_ID_TWO = "ID_TWO";
 	public static final String COL_INFO = "INFO";

	/* loadAll( context ) is inherited from BasicDataFacade */

	@Override
	public BasicDaoResult<ModelTestTwoFieldKey> loadAllByFinder( DAOContext context, TestTwoFieldKeyFinder finder ) throws DAOException {
		BasicDaoResult<ModelTestTwoFieldKey> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelTestTwoFieldKey> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getQueryView(), this.getTableName() );
		if ( finder.getModel() != null ) {
			ModelTestTwoFieldKey model = finder.getModel();
			query.andEqualParam( COL_ID_ONE, model.getIdOne() );
			query.andEqualParam( COL_ID_TWO, model.getIdTwo() );
			query.andEqualParam( COL_INFO, model.getInfo() );
		}
		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); 
		result.evaluateResultFromList(); 
		return result;
	}

	@Override
	public BasicDaoResult<ModelTestTwoFieldKey> create( DAOContext context, ModelTestTwoFieldKey model ) throws DAOException {
		BasicDaoResult<ModelTestTwoFieldKey> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelTestTwoFieldKey> daoHelper = new BasicDAOHelper<>( context );
		if ( model.getIdOne() == null ) { 
			model.setIdOne( this.generateId( context ) ); 
		} 
		if ( model.getIdTwo() == null ) { 
			model.setIdTwo( this.generateId( context ) ); 
		} 
		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );
		query.addParam( COL_ID_ONE, model.getIdOne() );
		query.addParam( COL_ID_TWO, model.getIdTwo() );
		query.addParam( COL_INFO, model.getInfo() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

	@Override
	public ModelTestTwoFieldKey loadById( DAOContext context, java.math.BigDecimal idOne, java.math.BigDecimal idTwo ) throws DAOException {
		ModelTestTwoFieldKey result = null;
		BasicDAOHelper<ModelTestTwoFieldKey> daoHelper = new BasicDAOHelper<>( context );
		SelectHelper query = daoHelper.newSelectHelper( this.getQueryView(), this.getTableName() );
		if ( idOne == null  || idTwo == null  ) { 
			 throw new DAOException( "Null parameter in key java.math.BigDecimal idOne, java.math.BigDecimal idTwo" );
		} else { 
			query.andEqualParam( COL_ID_ONE, idOne );
			query.andEqualParam( COL_ID_TWO, idTwo );
		}
		result = daoHelper.loadOneHelper( query, this.getRse() );
		return result;
	}

	@Override
	public BasicDaoResult<ModelTestTwoFieldKey> deleteById( DAOContext context, java.math.BigDecimal idOne, java.math.BigDecimal idTwo ) throws DAOException {
		BasicDaoResult<ModelTestTwoFieldKey> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelTestTwoFieldKey> daoHelper = new BasicDAOHelper<>( context );
		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );
		query.andWhereParam( COL_ID_ONE, idOne );
		query.andWhereParam( COL_ID_TWO, idTwo );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, null, result);
		return result;
	}

	@Override
	public BasicDaoResult<ModelTestTwoFieldKey> updateById( DAOContext context, ModelTestTwoFieldKey model ) throws DAOException {
		BasicDaoResult<ModelTestTwoFieldKey> result = new BasicDaoResult<>();
		BasicDAOHelper<ModelTestTwoFieldKey> daoHelper = new BasicDAOHelper<>( context );
		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );
		query.addSetParam( COL_INFO, model.getInfo() );
		query.andWhereParam( COL_ID_ONE, model.getIdOne() );
		query.andWhereParam( COL_ID_TWO, model.getIdTwo() );
		int res = daoHelper.update( query );
		this.evaluteSqlUpdateResult(res, model, result);
		return result;
	}

}
