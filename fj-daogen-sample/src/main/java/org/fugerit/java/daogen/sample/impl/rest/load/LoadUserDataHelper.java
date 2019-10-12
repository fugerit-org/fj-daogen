package org.fugerit.java.daogen.sample.impl.rest.load;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.core.db.daogen.CloseableDAOContext;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.SimpleServiceResult;
import org.fugerit.java.daogen.sample.def.facade.EntityUserDataFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;
import org.fugerit.java.daogen.sample.def.facade.UserDataFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadUserDataHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class LoadUserDataHelper extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper<ModelUserData> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 774382501938L;

	public static SimpleServiceResult<ModelUserData> loadByIdWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUserDataFacade facade = factory.getEntityUserDataFacade();
		ModelUserData model = facade.loadById( context , id );
		SimpleServiceResult<ModelUserData>  result = SimpleServiceResult.newDefaultResult( model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelUserData>  result = loadByIdWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getByID - "+e, e );
		}
		return res;
	}

	public static SimpleServiceResult<ModelUserData> loadByIdDeepWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUserDataFacade facade = factory.getEntityUserDataFacade();
		ModelUserData model = facade.loadById( context , id );
		SimpleServiceResult<ModelUserData>  result = SimpleServiceResult.newDefaultResult( model );
		if ( result.getContent() != null ) {
		}
		return result;
	}

	@GET
	@Path("/deep/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIDdeep(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelUserData>  result = loadByIdDeepWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getByID - "+e, e );
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
		EntityUserDataFacade facade = factory.getEntityUserDataFacade();
			BasicDaoResult<ModelUserData> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelUserData>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUserData.
	 * Property ModelUserData is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelUserData to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUserData>> loadByModelWorker( DAOContext context, ModelUserData model ) throws DAOException {
		UserDataFinder finder = UserDataFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUserDataFacade facade = factory.getEntityUserDataFacade();
		BasicDaoResult<ModelUserData> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelUserData>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

	/**
	 * Service method to load entity of type ModelUserData.
	 * Property username is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property username to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUserData>> loadByUsername( DAOContext context, java.lang.String current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperUserData model = new org.fugerit.java.daogen.sample.impl.helper.HelperUserData();
		model.setUsername( current );
		SimpleServiceResult<List<ModelUserData>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsername(@PathParam( "username" ) String username) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = username;
			SimpleServiceResult<List<ModelUserData>>  result = loadByUsername( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getAllUsername - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUserData.
	 * Property password is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property password to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUserData>> loadByPassword( DAOContext context, java.lang.String current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperUserData model = new org.fugerit.java.daogen.sample.impl.helper.HelperUserData();
		model.setPassword( current );
		SimpleServiceResult<List<ModelUserData>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/password/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPassword(@PathParam( "password" ) String password) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = password;
			SimpleServiceResult<List<ModelUserData>>  result = loadByPassword( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getAllPassword - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUserData.
	 * Property state is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property state to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUserData>> loadByState( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperUserData model = new org.fugerit.java.daogen.sample.impl.helper.HelperUserData();
		model.setState( current );
		SimpleServiceResult<List<ModelUserData>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/state/{state}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllState(@PathParam( "state" ) String state) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			java.math.BigDecimal value = new java.math.BigDecimal(state);
			SimpleServiceResult<List<ModelUserData>>  result = loadByState( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUserData - getAllState - "+e, e );
		}
		return res;
	}

}
