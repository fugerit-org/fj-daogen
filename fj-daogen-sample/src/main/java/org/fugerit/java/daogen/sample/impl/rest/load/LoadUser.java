package org.fugerit.java.daogen.sample.impl.rest.load;

import java.util.List;
import java.math.BigDecimal;
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
import org.fugerit.java.daogen.sample.def.model.ModelUser;
import org.fugerit.java.daogen.sample.impl.helper.HelperUser;
import org.fugerit.java.daogen.sample.def.facade.UserFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityUserFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadUser, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@Stateless
@Path("/user/load")
public class LoadUser extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 960947509004L;

	public static SimpleServiceResult<ModelUser> loadByIdWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUserFacade facade = factory.getEntityUserFacade();
		ModelUser model = facade.loadById( context , id );
		SimpleServiceResult<ModelUser>  result = SimpleServiceResult.newDefaultResult( model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelUser>  result = loadByIdWorker( context, new java.math.BigDecimal(id) );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getByID - "+e, e );
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
		EntityUserFacade facade = factory.getEntityUserFacade();
			BasicDaoResult<ModelUser> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelUser>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUser.
	 * Property ModelUser is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelUser to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUser>> loadByModelWorker( DAOContext context, ModelUser model ) throws DAOException {
		UserFinder finder = UserFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUserFacade facade = factory.getEntityUserFacade();
		BasicDaoResult<ModelUser> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelUser>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

	/**
	 * Service method to load entity of type ModelUser.
	 * Property id is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property id to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUser>> loadById( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		HelperUser model = new HelperUser();
		model.setId( current );
		SimpleServiceResult<List<ModelUser>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllId(@PathParam( "id" ) String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			BigDecimal value = new BigDecimal(id);
			SimpleServiceResult<List<ModelUser>>  result = loadById( context, value );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getAllId - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUser.
	 * Property username is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property username to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUser>> loadByUsername( DAOContext context, java.lang.String current ) throws DAOException {
		HelperUser model = new HelperUser();
		model.setUsername( current );
		SimpleServiceResult<List<ModelUser>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsername(@PathParam( "username" ) String username) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = username;
			SimpleServiceResult<List<ModelUser>>  result = loadByUsername( context, value );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getAllUsername - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUser.
	 * Property password is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property password to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUser>> loadByPassword( DAOContext context, java.lang.String current ) throws DAOException {
		HelperUser model = new HelperUser();
		model.setPassword( current );
		SimpleServiceResult<List<ModelUser>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/password/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPassword(@PathParam( "password" ) String password) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = password;
			SimpleServiceResult<List<ModelUser>>  result = loadByPassword( context, value );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getAllPassword - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUser.
	 * Property state is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property state to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUser>> loadByState( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		HelperUser model = new HelperUser();
		model.setState( current );
		SimpleServiceResult<List<ModelUser>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/state/{state}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllState(@PathParam( "state" ) String state) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			BigDecimal value = new BigDecimal(state);
			SimpleServiceResult<List<ModelUser>>  result = loadByState( context, value );
			res = Response.ok( result ).build();
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUser - getAllState - "+e, e );
		}
		return res;
	}

}
