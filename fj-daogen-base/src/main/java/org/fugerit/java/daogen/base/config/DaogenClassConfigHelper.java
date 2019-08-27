package org.fugerit.java.daogen.base.config;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DaogenClassConfigHelper {

	private static Logger logger = LoggerFactory.getLogger( DaogenClassConfigHelper.class );
	
	public static final String DAO_BASE_PACKAGE = "package";
	public static final String DAO_BASE_CLASS = "class";
	
	public static final String DAO_CONTEXT_BASE = "dao.context";
	public static final String DAO_CLOSEABLECONTEXT_BASE = "dao.closeablecontext";
	public static final String DAO_EXCEPTION_BASE = "dao.exception";
	public static final String DAO_BASEFINDER_BASE = "dao.basefinder";
	public static final String DAO_RESULT_BASE = "dao.result";
	public static final String DAO_SERVICERESULT_BASE = "dao.serviceresult";
	public static final String DAO_HELPER_BASE = "dao.helper";
	public static final String DAO_WRAPPER_BASE = "dao.wrapper";
	
	public static final String DAO_DAOHELPER_BASE = "dao.daohelper";
	public static final String DAO_SELECTHELPER_BASE = "dao.selecthelper";
	public static final String DAO_DELETEHELPER_BASE = "dao.deletehelper";
	public static final String DAO_UPDATEHELPER_BASE = "dao.updatehelper";
	public static final String DAO_INSERTHELPER_BASE = "dao.inserthelper";
	
	public static final String DAO_DATAFACADE_BASE = "dao.datafacade";
	
	public static final String DAO_RSEHELPER_BASE = "dao.rsehelper";
	
	public static final String DAO_STRUCTMAPPER_BASE = "dao.structmapper";
	
	public static String addImport( DaogenCatalogConfig config, String base, Collection<String> imports ) {
		logger.info( "props > "+config.getClassConfig() );
		String classPropKey = base+"."+DAO_BASE_CLASS;
		String pacakgePropKey = base+"."+DAO_BASE_PACKAGE;
		String simpleName = config.getClassConfig().getProperty( classPropKey );
		String packageName = config.getClassConfig().getProperty( pacakgePropKey );
		if ( simpleName == null || packageName == null ) {
			throw new RuntimeException( "Daogen class configuration not set properly" );
		}
		String fullName = packageName+"."+simpleName;
		imports.add( fullName );
		return simpleName;
	}
	
	public static String fullName( DaogenCatalogConfig config, String base ) {
		logger.info( "props > "+config.getClassConfig() );
		String classPropKey = base+"."+DAO_BASE_CLASS;
		String pacakgePropKey = base+"."+DAO_BASE_PACKAGE;
		String simpleName = config.getClassConfig().getProperty( classPropKey );
		String packageName = config.getClassConfig().getProperty( pacakgePropKey );
		if ( simpleName == null || packageName == null ) {
			throw new RuntimeException( "Daogen class configuration not set properly" );
		}
		String fullName = packageName+"."+simpleName;
		return fullName;
	}
	
}


