package org.fugerit.java.daogen.sample.impl.facade.data;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityTestTwoFieldKeyFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUploadFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUserFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.DataEntityAddressFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.DataEntityLogDataFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.DataEntityTestTwoFieldKeyFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.DataEntityUploadFacade;
import org.fugerit.java.daogen.sample.impl.facade.data.DataEntityUserFacade;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * FugeritDataLogicFacade, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class FugeritDataLogicFacade implements org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacade {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private EntityAddressFacade entityaddressfacade = new DataEntityAddressFacade();

	@Override
	public EntityAddressFacade getEntityAddressFacade() throws DAOException {
		return this.entityaddressfacade;
	}

	private EntityLogDataFacade entitylogdatafacade = new DataEntityLogDataFacade();

	@Override
	public EntityLogDataFacade getEntityLogDataFacade() throws DAOException {
		return this.entitylogdatafacade;
	}

	private EntityTestTwoFieldKeyFacade entitytesttwofieldkeyfacade = new DataEntityTestTwoFieldKeyFacade();

	@Override
	public EntityTestTwoFieldKeyFacade getEntityTestTwoFieldKeyFacade() throws DAOException {
		return this.entitytesttwofieldkeyfacade;
	}

	private EntityUploadFacade entityuploadfacade = new DataEntityUploadFacade();

	@Override
	public EntityUploadFacade getEntityUploadFacade() throws DAOException {
		return this.entityuploadfacade;
	}

	private EntityUserFacade entityuserfacade = new DataEntityUserFacade();

	@Override
	public EntityUserFacade getEntityUserFacade() throws DAOException {
		return this.entityuserfacade;
	}

}
