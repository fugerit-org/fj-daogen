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
import org.fugerit.java.daogen.sample.def.facade.AddressFinder;
import org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadAddressHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class LoadAddressHelper extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper<ModelAddress> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 933291703565L;

	public static SimpleServiceResult<ModelAddress> loadByIdWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityAddressFacade facade = factory.getEntityAddressFacade();
		ModelAddress model = facade.loadById( context , id );
		SimpleServiceResult<ModelAddress>  result = SimpleServiceResult.newDefaultResult( model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelAddress>  result = loadByIdWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadAddress - getByID - "+e, e );
		}
		return res;
	}

	public static SimpleServiceResult<ModelAddress> loadByIdDeepWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityAddressFacade facade = factory.getEntityAddressFacade();
		ModelAddress model = facade.loadById( context , id );
		SimpleServiceResult<ModelAddress>  result = SimpleServiceResult.newDefaultResult( model );
		if ( result.getContent() != null ) {
			result.getContent().setUser(LoadUser.loadByIdWorker( context, result.getContent().getIdUser() ).getContent());
		}
		return result;
	}

	@GET
	@Path("/deep/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByIDdeep(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelAddress>  result = loadByIdDeepWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadAddress - getByID - "+e, e );
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
		EntityAddressFacade facade = factory.getEntityAddressFacade();
			BasicDaoResult<ModelAddress> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelAddress>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadAddress - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelAddress.
	 * Property ModelAddress is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelAddress to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelAddress>> loadByModelWorker( DAOContext context, ModelAddress model ) throws DAOException {
		AddressFinder finder = AddressFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityAddressFacade facade = factory.getEntityAddressFacade();
		BasicDaoResult<ModelAddress> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelAddress>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

	/**
	 * Service method to load entity of type ModelAddress.
	 * Property idUser is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property idUser to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelAddress>> loadByIdUser( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperAddress model = new org.fugerit.java.daogen.sample.impl.helper.HelperAddress();
		model.setIdUser( current );
		SimpleServiceResult<List<ModelAddress>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/id_user/{id_user}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllIdUser(@PathParam( "id_user" ) String idUser) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			java.math.BigDecimal value = new java.math.BigDecimal(idUser);
			SimpleServiceResult<List<ModelAddress>>  result = loadByIdUser( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadAddress - getAllIdUser - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelAddress.
	 * Property info is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property info to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelAddress>> loadByInfo( DAOContext context, java.lang.String current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperAddress model = new org.fugerit.java.daogen.sample.impl.helper.HelperAddress();
		model.setInfo( current );
		SimpleServiceResult<List<ModelAddress>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/info/{info}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInfo(@PathParam( "info" ) String info) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = info;
			SimpleServiceResult<List<ModelAddress>>  result = loadByInfo( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadAddress - getAllInfo - "+e, e );
		}
		return res;
	}

}
