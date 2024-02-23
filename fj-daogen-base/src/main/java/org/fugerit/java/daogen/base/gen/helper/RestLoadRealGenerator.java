package org.fugerit.java.daogen.base.gen.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;

public class RestLoadRealGenerator extends BaseRestLoadRealGenerator {

	public static final String KEY = RestLoadRealGenerator.class.getSimpleName();
	@Override
	public Collection<String> getImportList() {
		String jeeTarget = this.getJeeTargetMode();
		super.getImportList().add( jeeTarget+".ws.rs.Path" );
		return super.getImportList();
	}

	public RestLoadRealGenerator() {
		super( KEY, new ArrayList<>(), DaogenCatalogConstants.GEN_PROP_PACKAGE_REST_LOAD, null, "@Path(\"/{0}/load\")" );
	}

}
