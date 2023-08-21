package org.fugerit.java.daogen.sample.helper;

import org.fugerit.java.core.db.dao.CloseDAOHelper;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.CloseableDAOContext;
import org.fugerit.java.core.db.daogen.CloseableDAOContextSC;
import org.fugerit.java.core.db.daogen.SimpleServiceProvider;
import org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.FugeritDataLogicFacade;
import org.fugerit.java.test.db.helper.MemDBHelper;

public class ServiceProviderHelper<T> extends SimpleServiceProvider<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131242678928301009L;

	@Override
	protected CloseableDAOContext newDefaultContext() throws DAOException {
		CloseableDAOContextSC context = null;
		try {
			/*
			 * The object asking for the context is responsible for closing it.
			 */
			context = new CloseableDAOContextSC( MemDBHelper.newConnection() );		// NOSONAR
			context.setAttribute( FugeritLogicFacade.ATT_NAME, new FugeritDataLogicFacade() );
			return context;	
		} catch (Exception e) {
			if ( context != null ) {
				CloseDAOHelper.close(context);
			}
			throw DAOException.convertEx( e );
		}
	}

}