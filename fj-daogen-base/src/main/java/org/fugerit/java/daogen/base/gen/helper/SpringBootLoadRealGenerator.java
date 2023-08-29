package org.fugerit.java.daogen.base.gen.helper;

import java.util.Arrays;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;

public class SpringBootLoadRealGenerator extends BaseRestLoadRealGenerator {

	public static final String KEY = SpringBootLoadRealGenerator.class.getSimpleName();
	
	private static final String[] IMPORTS = { "org.springframework.web.bind.annotation.RequestMapping", "org.springframework.web.bind.annotation.RestController" };

	public SpringBootLoadRealGenerator() {
		super( KEY, Arrays.asList( IMPORTS ), DaogenCatalogConstants.GEN_PROP_PACKAGE_SPRING_REST_LOAD, "@RestController", "@RequestMapping(\"/{0}/load\")" );
	}

}
