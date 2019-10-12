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
import org.fugerit.java.daogen.sample.def.facade.EntityUploadFacade;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;
import org.fugerit.java.daogen.sample.def.facade.UploadFinder;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * LoadUpload, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
@Stateless
@Path("/upload/load")
public class LoadUpload extends org.fugerit.java.daogen.sample.helper.ServiceProviderHelper<ModelUpload> {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 509829836417L;

	public static SimpleServiceResult<ModelUpload> loadByIdWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUploadFacade facade = factory.getEntityUploadFacade();
		ModelUpload model = facade.loadById( context , id );
		SimpleServiceResult<ModelUpload>  result = SimpleServiceResult.newDefaultResult( model );
		return result;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByID(@PathParam( "id") String id) throws Exception {
		Response res = null;
		try (CloseableDAOContext context = this.newDefaultContext() ) {
			SimpleServiceResult<ModelUpload>  result = loadByIdWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUpload - getByID - "+e, e );
		}
		return res;
	}

	public static SimpleServiceResult<ModelUpload> loadByIdDeepWorker( DAOContext context, java.math.BigDecimal id ) throws DAOException {
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUploadFacade facade = factory.getEntityUploadFacade();
		ModelUpload model = facade.loadById( context , id );
		SimpleServiceResult<ModelUpload>  result = SimpleServiceResult.newDefaultResult( model );
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
			SimpleServiceResult<ModelUpload>  result = loadByIdDeepWorker( context, new java.math.BigDecimal(id) );
			res = this.createResponseFromObject( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUpload - getByID - "+e, e );
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
		EntityUploadFacade facade = factory.getEntityUploadFacade();
			BasicDaoResult<ModelUpload> resultFacade = facade.loadAll( context );
			SimpleServiceResult<List<ModelUpload>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
			res = this.createResponseFromList( result );
		} catch(Exception e) {
			logger.error("ERRORE - REST- LoadUpload - getAll - "+e, e );
		}
		return res;
	}

	/**
	 * Service method to load entity of type ModelUpload.
	 * Property ModelUpload is being used as filter
	 * 
	 * @param context	DAO context
	 * @param model	Tee value of property ModelUpload to use as a filter
	 * @return			the result found
	 * @throws DAOException		in case of any issue
	 */
	public static SimpleServiceResult<List<ModelUpload>> loadByModelWorker( DAOContext context, ModelUpload model ) throws DAOException {
		UploadFinder finder = UploadFinder.newInstance( model );
		FugeritLogicFacade factory = (FugeritLogicFacade) context.getAttribute(FugeritLogicFacade.ATT_NAME );
		EntityUploadFacade facade = factory.getEntityUploadFacade();
		BasicDaoResult<ModelUpload> resultFacade = facade.loadAllByFinder( context , finder );
		SimpleServiceResult<List<ModelUpload>>  result = SimpleServiceResult.newDefaultResult( resultFacade.getList() );
		return result;
	}

}
