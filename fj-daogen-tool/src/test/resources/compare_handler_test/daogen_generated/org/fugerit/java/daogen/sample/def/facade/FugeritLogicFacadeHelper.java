package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.dao.DAOException;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * FugeritLogicFacadeHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface FugeritLogicFacadeHelper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	public static final String ATT_NAME = "FugeritLogicFacadeHelper";

	/**
	 * Facade incapsulating persistance for entity : ADDRESS
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade getEntityAddressFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : LOG_DATA
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade getEntityLogDataFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : TEST_TWO_FIELD_KEY
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityTestTwoFieldKeyFacade getEntityTestTwoFieldKeyFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : UPLOAD
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityUploadFacade getEntityUploadFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : USER
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityUserFacade getEntityUserFacade() throws DAOException;

	/**
	 * Facade incapsulating persistance for entity : USER_DATA
	 *
	 * @return	the facade
	 * @throws DAOException	in case of problems
	 */
	org.fugerit.java.daogen.sample.def.facade.EntityUserDataFacade getEntityUserDataFacade() throws DAOException;

}
