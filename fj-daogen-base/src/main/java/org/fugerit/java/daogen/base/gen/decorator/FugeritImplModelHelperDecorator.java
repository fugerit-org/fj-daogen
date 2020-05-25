package org.fugerit.java.daogen.base.gen.decorator;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.gen.DaogenBasicDecorator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class FugeritImplModelHelperDecorator extends DaogenBasicDecorator {

	@Override
	public void init(DaogenBasicGenerator generator) {
		super.init(generator);
	}

	@Override
	public void addBeforeClass() throws Exception {
		super.addBeforeClass();		
		this.accessGenerator().println( "@org.fugerit.java.core.lang.annotate.FugeritImpl(as = "+this.accessGenerator().getEntityHelperName()+".class)" );
	}

	@Override
	public void addImports() throws Exception {
		super.addImports();
		String helperClass = this.accessGenerator().getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.accessGenerator().getEntityHelperName();
		this.accessGenerator().getImportList().add( helperClass );
	}
	
}
