package org.fugerit.java.daogen.base.config;

import java.util.Collection;

import org.fugerit.java.core.cfg.ConfigRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaogenClassConfigHelper {

	private DaogenClassConfigHelper() {}
	
	public static final String DAO_BASE_PACKAGE = "package";
	public static final String DAO_BASE_CLASS = "class";
	
	public static final String DAO_CONTEXT_BASE = "dao.context";
	public static final String DAO_CLOSEABLECONTEXT_BASE = "dao.closeablecontext";
	public static final String DAO_EXCEPTION_BASE = "dao.exception";
	public static final String DAO_BASEFINDER_BASE = "dao.basefinder";
	public static final String DAO_GENERICFINDER_BASE = "dao.genericfinder";
	public static final String DAO_RESULT_BASE = "dao.result";
	public static final String DAO_SERVICERESULT_BASE = "dao.serviceresult";
	public static final String DAO_HELPER_BASE = "dao.helper";
	public static final String DAO_WRAPPER_BASE = "dao.wrapper";

	public static final String DAO_HELPER_NG_BASE = "dao.helper.ng";

	public static final String DAO_WRAPPER_NG_BASE = "dao.wrapper.ng";

	public static final String DAO_FINDER_NG_BASE = "dao.finder.ng";

	public static final String DAO_DAOHELPER_BASE = "dao.daohelper";
	public static final String DAO_SELECTHELPER_BASE = "dao.selecthelper";
	public static final String DAO_DELETEHELPER_BASE = "dao.deletehelper";
	public static final String DAO_UPDATEHELPER_BASE = "dao.updatehelper";
	public static final String DAO_INSERTHELPER_BASE = "dao.inserthelper";
	
	public static final String DAO_DATAFACADE_BASE = "dao.datafacade";
	
	public static final String DAO_RSEHELPER_BASE = "dao.rsehelper";
	
	public static final String DAO_STRUCTMAPPER_BASE = "dao.structmapper";

	public static String findClassConfigProp( DaogenCatalogConfig config, String base, String type ) {
		log.info( "search, base:{} - type:{}", base, type );
		String fullKey = base+"."+type;
		String fullValue = config.getClassConfig().getProperty( fullKey );
		log.info( "search, fullKey:{} - fullValue:{}", fullKey, fullValue );
		return fullValue;
	}

	public static String addImport( DaogenCatalogConfig config, String base, Collection<String> imports ) {
		log.info( "props > {}", config.getClassConfig() );
		String simpleName = findClassConfigProp(config, base, DAO_BASE_CLASS );
		String packageName = findClassConfigProp(config, base, DAO_BASE_PACKAGE );
		if ( simpleName == null || packageName == null ) {
			throw new ConfigRuntimeException( "Daogen class configuration not set properly" );
		}
		String fullName = packageName+"."+simpleName;
		imports.add( fullName );
		return simpleName;
	}
	
	public static String fullName( DaogenCatalogConfig config, String base ) {
		log.info( "props > {}", config.getClassConfig() );
		String classPropKey = base+"."+DAO_BASE_CLASS;
		String pacakgePropKey = base+"."+DAO_BASE_PACKAGE;
		String simpleName = config.getClassConfig().getProperty( classPropKey );
		String packageName = config.getClassConfig().getProperty( pacakgePropKey );
		if ( simpleName == null || packageName == null ) {
			throw new ConfigRuntimeException( "Daogen class configuration not set properly" );
		}
		return packageName+"."+simpleName;
	}
	
}


