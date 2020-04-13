package org.fugerit.java.daogen.sample.impl.facade.data;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.sample.def.facade.EntityAddressFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityLogDataFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityTestTwoFieldKeyFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUploadFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUserDataFacade;
import org.fugerit.java.daogen.sample.def.facade.EntityUserFacade;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * FugeritDataLogicFacadeHelper, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class FugeritDataLogicFacadeHelper implements org.fugerit.java.daogen.sample.def.facade.FugeritLogicFacadeHelper, java.io.Serializable {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 434639213864L;


	public FugeritDataLogicFacadeHelper() {
		this.entityaddressfacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityAddressFacade();
		this.entitylogdatafacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityLogDataFacade();
		this.entitytesttwofieldkeyfacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityTestTwoFieldKeyFacade();
		this.entityuploadfacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityUploadFacade();
		this.entityuserfacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityUserFacade();
		this.entityuserdatafacade = new org.fugerit.java.daogen.sample.impl.facade.data.DataEntityUserDataFacade();
	}

	private EntityAddressFacade entityaddressfacade;

	@Override
	public EntityAddressFacade getEntityAddressFacade() throws DAOException {
		return this.entityaddressfacade;
	}

	protected void setEntityAddressFacade( EntityAddressFacade facade ) throws DAOException {
		this.entityaddressfacade = facade;
	}

	private EntityLogDataFacade entitylogdatafacade;

	@Override
	public EntityLogDataFacade getEntityLogDataFacade() throws DAOException {
		return this.entitylogdatafacade;
	}

	protected void setEntityLogDataFacade( EntityLogDataFacade facade ) throws DAOException {
		this.entitylogdatafacade = facade;
	}

	private EntityTestTwoFieldKeyFacade entitytesttwofieldkeyfacade;

	@Override
	public EntityTestTwoFieldKeyFacade getEntityTestTwoFieldKeyFacade() throws DAOException {
		return this.entitytesttwofieldkeyfacade;
	}

	protected void setEntityTestTwoFieldKeyFacade( EntityTestTwoFieldKeyFacade facade ) throws DAOException {
		this.entitytesttwofieldkeyfacade = facade;
	}

	private EntityUploadFacade entityuploadfacade;

	@Override
	public EntityUploadFacade getEntityUploadFacade() throws DAOException {
		return this.entityuploadfacade;
	}

	protected void setEntityUploadFacade( EntityUploadFacade facade ) throws DAOException {
		this.entityuploadfacade = facade;
	}

	private EntityUserFacade entityuserfacade;

	@Override
	public EntityUserFacade getEntityUserFacade() throws DAOException {
		return this.entityuserfacade;
	}

	protected void setEntityUserFacade( EntityUserFacade facade ) throws DAOException {
		this.entityuserfacade = facade;
	}

	private EntityUserDataFacade entityuserdatafacade;

	@Override
	public EntityUserDataFacade getEntityUserDataFacade() throws DAOException {
		return this.entityuserdatafacade;
	}

	protected void setEntityUserDataFacade( EntityUserDataFacade facade ) throws DAOException {
		this.entityuserdatafacade = facade;
	}

}
