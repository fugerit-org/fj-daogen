package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUploadFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUserFacade;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * FugeritLogicFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface FugeritLogicFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	public static final String ATT_NAME = "FugeritLogicFacade";

	/**
	 * Facade incapsulating persistance for entity : ADDRESS
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	EntityAddressFacade getEntityAddressFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : LOG_DATA
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	EntityLogDataFacade getEntityLogDataFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : UPLOAD
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	EntityUploadFacade getEntityUploadFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : USER
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	EntityUserFacade getEntityUserFacade() throws DAOException;

}
