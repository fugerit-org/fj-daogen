package org.fugerit.java.daogen.base.gen.decorator;

import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.gen.DaogenBasicDecorator;

public class AddAnnotationModelHelperDecorator extends DaogenBasicDecorator {

	private String annotationClassName;
	
	public AddAnnotationModelHelperDecorator(String annotationClassName) {
		super();
		this.annotationClassName = annotationClassName;
	}

	@Override
	public void addBeforeClass() throws DAOException {
		super.addBeforeClass();		
		this.accessGenerator().println( "@"+this.annotationClassName+"(as = "+this.accessGenerator().getEntityHelperName()+".class)" );
	}

	@Override
	public void addImports() throws DAOException {
		super.addImports();
		String helperClass = this.accessGenerator().getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.accessGenerator().getEntityHelperName();
		this.accessGenerator().getImportList().add( helperClass );
	}
	
}
