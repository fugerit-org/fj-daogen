package org.fugerit.java.daogen.base.gen.decorator;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.gen.DaogenBasicDecorator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class JacksonJsonDeserializeModelHelperDecorator extends DaogenBasicDecorator {

	@Override
	public void init(DaogenBasicGenerator generator) {
		super.init(generator);
	}

	@Override
	public void addBeforeClass() throws DAOException {
		super.addBeforeClass();		
		this.accessGenerator().println( "@org.codehaus.jackson.map.annotate.JsonDeserialize(as = "+this.accessGenerator().getEntityHelperName()+".class)" );
	}

	@Override
	public void addImports() throws DAOException {
		super.addImports();
		String helperClass = this.accessGenerator().getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.accessGenerator().getEntityHelperName();
		this.accessGenerator().getImportList().add( helperClass );
	}

}
