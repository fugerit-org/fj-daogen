package org.fugerit.java.daogen.sample.impl.rest.load;

import java.util.List;
import javax.ejb.Stateless;
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
import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;
import org.fugerit.java.daogen.sample.def.facade.LogDataFinder;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadLogData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@Stateless
@Path("/logdata/load")
public class LoadLogData extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper<ModelLogData> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 778275664140L;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityLogDataFacade facade = factory.getEntityLogDataFacade();
			BasicDaoResult<ModelLogData> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelLogData>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadLogData - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelLogData.
	 * Property ModelLogData is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelLogData to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelLogData>> loadByModelWorker( DAOContext context, ModelLogData model ) throws DAOException {
		LogDataFinder finder = LogDataFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityLogDataFacade facade = factory.getEntityLogDataFacade();
		BasicDaoResult<ModelLogData> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelLogData>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

	/**
	 * Service method to load entity of type ModelLogData.
	 * Property id is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property id to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelLogData>> loadById( DAOContext context, java.math.BigDecimal current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperLogData model = new org.fugerit.java.daogen.sample.impl.helper.HelperLogData();
		model.setId( current );
		SimpleServiceResult<List<ModelLogData>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllId(@PathParam( "id" ) String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			java.math.BigDecimal value = new java.math.BigDecimal(id);
			SimpleServiceResult<List<ModelLogData>>  result = loadById( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadLogData - getAllId - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelLogData.
	 * Property info is being used as filter
	 * 
	 * @param context	DAO context
	 * @param current	Tee value of property info to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelLogData>> loadByInfo( DAOContext context, java.lang.String current ) throws DAOException {
		org.fugerit.java.daogen.sample.impl.helper.HelperLogData model = new org.fugerit.java.daogen.sample.impl.helper.HelperLogData();
		model.setInfo( current );
		SimpleServiceResult<List<ModelLogData>>  result = loadByModelWorker( context , model );
		return result;
	}

	@GET
	@Path("/info/{info}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInfo(@PathParam( "info" ) String info) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			String value = info;
			SimpleServiceResult<List<ModelLogData>>  result = loadByInfo( context, value );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadLogData - getAllInfo - "+e, e );
		}
		return res;
	}

}
