package org.fugerit.java.daogen.sample.def.facade;

import java.math.BigDecimal;
import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.BasicDaoResult;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * EntityAddressFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public interface EntityAddressFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	/*
	 * NOTA: Eccetto la ricerca per id, e' preferibile usare dei finder per incapsulare i parametri di ricerca.
	 */

	/**
	 * Metodo di caricamento per id della entity : ModelAddress
	 *
	 * @param context	il contesto dell'operazione
	 * @param id		id dell'oggetto da cercare
	 *
	 * @return l'oggetto trovato o <code>null</code>.
	 * @throws DAOException			in caso di errori
	 */
	ModelAddress loadById( DAOContext context, BigDecimal id ) throws DAOException;

	/**
	 * Metodo di caricamento per tutte le entity : ModelAddress
	 *
	 * @param context	il contesto dell'operazione
	 *
	 * @return Il risultato della ricerca
	 * @throws DAOException			in caso di errori
	 */
	BasicDaoResult<ModelAddress> loadAll( DAOContext context ) throws DAOException;

	/**
	 * Metodo di caricamento per tutte le entity : ModelAddress
	 *
	 * @param context	il contesto dell'operazione
	 * @param finder	il finder
	 *
	 * @return Il risultato della ricerca
	 * @throws DAOException			in caso di errori
	 */
	BasicDaoResult<ModelAddress> loadAllByFinder( DAOContext context, AddressFinder finder ) throws DAOException;

	/**
	 * Method to create an new entity of type : ModelAddress
	 *
	 * A new ID should be assigned by this method.
	 *
	 * @param context	DAO context
	 * @param model		Entity to create
	 *
	 * @return 			The created entity
	 * @throws AnprBasicException		In case of any error.
	 */
	BasicDaoResult<ModelAddress> create( DAOContext context, ModelAddress model ) throws DAOException;

	/**
	 * Method to modify an entity of type : ModelAddress
	 *
	 * @param context	DAO context
	 * @param model		Entity to modify
	 *
	 * @return 			The modified entity
	 * @throws AnprBasicException		In case of any error.
	 */
	BasicDaoResult<ModelAddress> updateById( DAOContext context, ModelAddress model ) throws DAOException;

	/**
	 * Method to delete an entity of type : ModelAddress
	 *
	 * @param context	il contesto dell'operazione
	 * @param id		id entity da cancellare
	 *
	 * @return Il risultato della cancellazione
	 * @throws AnprBasicException		In case of any error.
	 */
	BasicDaoResult<ModelAddress> deleteById( DAOContext context, BigDecimal id ) throws DAOException;

}
