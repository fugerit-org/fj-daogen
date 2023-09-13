package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.daogen.sample.def.model.ModelUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * EntityUserFacadeHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface EntityUserFacadeHelper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * NOTE: It is advised to use a finder for incapsulating search params, except searches for primary key.
	 */

	/**
	 * Method to load all the items for entity : ModelUser
	 *
	 * @param context	DAOContext
	 *
	 * @return search result
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUser> loadAll( DAOContext context ) throws DAOException;

	/**
	 * Method to load all the items for entity : ModelUser
	 *
	 * @param context	DAOContext
	 * @param finder	the finder incapsulating search params
	 *
	 * @return search result
	 * @throws DAOException			in caso di errori
	 */
	BasicDaoResult<ModelUser> loadAllByFinder( DAOContext context, UserFinder finder ) throws DAOException;

	/**
	 * Load method by primary key for entity : ModelUser
	 *
	 * @param context	DAO Context
	 * @param id User system id

	 *
	 * @return The found object or <code>null</code>
	 * @throws DAOException			in case of errors
	 */
	ModelUser loadById( DAOContext context, java.math.BigDecimal id ) throws DAOException;

	/**
	 * Method to create an new entity of type : ModelUser
	 *
	 * A new ID should be assigned by this method.
	 *
	 * @param context	DAO context
	 * @param model		Entity to create
	 *
	 * @return 			The created entity
	 * @throws DAOException		In case of any error.
	 */
	BasicDaoResult<ModelUser> create( DAOContext context, ModelUser model ) throws DAOException;

	/**
	 * Delete method by primary key for entity : ModelUser
	 *
	 * @param context	DAO Context
	 * @param id User system id

	 *
	 * @return Delete result (resultCode=0, delete ok)
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUser> deleteById( DAOContext context, java.math.BigDecimal id ) throws DAOException;

	/**
	 * Delete method by primary key for entity : ModelUser
	 *
	 * @param context	DAO Context
	 * @param 	model	entity to update
	 *
	 * @return Update result (resultCode=0, update ok)
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUser> updateById( DAOContext context, ModelUser model ) throws DAOException;

}
