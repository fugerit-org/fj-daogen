package org.fugerit.java.daogen.sample.helper;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.db.daogen.CloseableDAOContext;
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
		Connection connData =  null;
		try {
			connData = MemDBHelper.newConnection();
		} catch (Exception e) {
			throw new DAOException( e );
		}
		final Connection conn = connData;
		CloseableDAOContext context = new CloseableDAOContext() {
			private Map<String, Object> map = new HashMap<String, Object>();
			@Override
			public void close() throws Exception {
				conn.close();
			}
			@Override
			public void setAttribute(String key, Object value) {
				this.map.put( key , value );
			}
			@Override
			public Object getAttribute(String key) {
				return this.map.get( key );
			}
			
			@Override
			public Connection getConnection() throws DAOException {
				return conn;
			}
		};
		context.setAttribute( FugeritLogicFacade.ATT_NAME, new FugeritDataLogicFacade() );
		return context;
	}

}
