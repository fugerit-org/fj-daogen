package org.fugerit.java.daogen.sample.def.facade;

import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * EntityUploadFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface EntityUploadFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * NOTE: It is advised to use a finder for incapsulating search params, except searches for primary key.
	 */

	/**
	 * Method to load all the items for entity : ModelUpload
	 *
	 * @param context	DAOContext
	 *
	 * @return search result
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUpload> loadAll( DAOContext context ) throws DAOException;

	/**
	 * Method to load all the items for entity : ModelUpload
	 *
	 * @param context	DAOContext
	 * @param finder	the finder incapsulating search params
	 *
	 * @return search result
	 * @throws DAOException			in caso di errori
	 */
	BasicDaoResult<ModelUpload> loadAllByFinder( DAOContext context, UploadFinder finder ) throws DAOException;

	/**
	 * Load method by primary key for entity : ModelUpload
	 *
	 * @param context	DAO Context
	 * @param id part of the key

	 *
	 * @return The found object or <code>null</code>
	 * @throws DAOException			in case of errors
	 */
	ModelUpload loadById( DAOContext context, java.math.BigDecimal id ) throws DAOException;

	/**
	 * Method to create an new entity of type : ModelUpload
	 *
	 * A new ID should be assigned by this method.
	 *
	 * @param context	DAO context
	 * @param model		Entity to create
	 *
	 * @return 			The created entity
	 * @throws DAOException		In case of any error.
	 */
	BasicDaoResult<ModelUpload> create( DAOContext context, ModelUpload model ) throws DAOException;

	/**
	 * Delete method by primary key for entity : ModelUpload
	 *
	 * @param context	DAO Context
	 * @param id part of the key

	 *
	 * @return Delete result (resultCode=0, delete ok)
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUpload> deleteById( DAOContext context, java.math.BigDecimal id ) throws DAOException;

	/**
	 * Delete method by primary key for entity : ModelUpload
	 *
	 * @param context	DAO Context
	 * @param 	model	entity to update
	 *
	 * @return Update result (resultCode=0, update ok)
	 * @throws DAOException			in case of errors
	 */
	BasicDaoResult<ModelUpload> updateById( DAOContext context, ModelUpload model ) throws DAOException;

}
