package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.gen.helper.RestLoadHelperGenerator;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

public class RestLoadGenerator extends RestLoadHelperGenerator {

	public static final String KEY = RestLoadGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}

}
