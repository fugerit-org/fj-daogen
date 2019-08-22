package org.fugerit.java.daogen.sample.impl.rest.load;

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.CloseableDAOContext;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.core.db.daogen.SimpleServiceResult;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestTwoFieldKey;
import org.fugerit.java.daogen.sample.def.facade.TestTwoFieldKeyFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityTestTwoFieldKeyFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadTestTwoFieldKey, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@Stateless
@Path("/testtwofieldkey/load")
public class LoadTestTwoFieldKey extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper<ModelTestTwoFieldKey> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 892898388143L;

	public static SimpleServiceResult<ModelTestTwoFieldKey> loadByIdWorker( DAOContext context, java.math.BigDecimal idOne, java.math.BigDecimal idTwo ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityTestTwoFieldKeyFacade facade = factory.getEntityTestTwoFieldKeyFacade();
		ModelTestTwoFieldKey model = facade.loadById( context , idOne, idTwo );
		SimpleServiceResult<ModelTestTwoFieldKey>  result = SimpleServiceResult.newDefaultResult( model );
		return result;
	}

	@GET
	@Path("/idOne/{idOne}/idTwo/{idTwo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam( "idOne") String idOne, @PathParam( "idTwo") String idTwo) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelTestTwoFieldKey>  result = loadByIdWorker( context, new java.math.BigDecimal(idOne), new java.math.BigDecimal(idTwo) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getByID - "+e, e );
		}
		return res;
	}

	public static SimpleServiceResult<ModelTestTwoFieldKey> loadByIdDeepWorker( DAOContext context, java.math.BigDecimal idOne, java.math.BigDecimal idTwo ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityTestTwoFieldKeyFacade facade = factory.getEntityTestTwoFieldKeyFacade();
		ModelTestTwoFieldKey model = facade.loadById( context , idOne, idTwo );
		SimpleServiceResult<ModelTestTwoFieldKey>  result = SimpleServiceResult.newDefaultResult( model );
		if ( result.getContent() != null ) {
		}
		return result;
	}

	@GET
	@Path("/deep/idOne/{idOne}/idTwo/{idTwo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIDdeep(@PathParam( "idOne") String idOne, @PathParam( "idTwo") String idTwo) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelTestTwoFieldKey>  result = loadByIdDeepWorker( context, new java.math.BigDecimal(idOne), new java.math.BigDecimal(idTwo) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getByID - "+e, e );
		}
		return res;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityTestTwoFieldKeyFacade facade = factory.getEntityTestTwoFieldKeyFacade();
			BasicDaoResult<ModelTestTwoFieldKey> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelTestTwoFieldKey.
	 * Property ModelTestTwoFieldKey is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelTestTwoFieldKey to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelTestTwoFieldKey>> loadByModelWorker( DAOContext context, ModelTestTwoFieldKey model ) throws DAOException {
		TestTwoFieldKeyFinder finder = TestTwoFieldKeyFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityTestTwoFieldKeyFacade facade = factory.getEntityTestTwoFieldKeyFacade();
		BasicDaoResult<ModelTestTwoFieldKey> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

	/**
	 * Service method to load entity of type ModelTestTwoFieldKey.
	 * Property idOne is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property idOne to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelTestTwoFieldKey>> loadByIdOne( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		HelperTestTwoFieldKey model = new HelperTestTwoFieldKey();
		model.setIdOne( current );
		SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/id_one/{id_one}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllIdOne(@PathParam( "id_one" ) String idOne) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			java.math.BigDecimal value = new java.math.BigDecimal(idOne);
			SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByIdOne( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getAllIdOne - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelTestTwoFieldKey.
	 * Property idTwo is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property idTwo to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelTestTwoFieldKey>> loadByIdTwo( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		HelperTestTwoFieldKey model = new HelperTestTwoFieldKey();
		model.setIdTwo( current );
		SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/id_two/{id_two}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllIdTwo(@PathParam( "id_two" ) String idTwo) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			java.math.BigDecimal value = new java.math.BigDecimal(idTwo);
			SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByIdTwo( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getAllIdTwo - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelTestTwoFieldKey.
	 * Property info is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property info to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelTestTwoFieldKey>> loadByInfo( DAOContext context, java.lang.String current ) throws DAOException {
		HelperTestTwoFieldKey model = new HelperTestTwoFieldKey();
		model.setInfo( current );
		SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/info/{info}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInfo(@PathParam( "info" ) String info) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = info;
			SimpleServiceResult<List<ModelTestTwoFieldKey>>  result = loadByInfo( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadTestTwoFieldKey - getAllInfo - "+e, e );
		}
		return res;
	}

}
