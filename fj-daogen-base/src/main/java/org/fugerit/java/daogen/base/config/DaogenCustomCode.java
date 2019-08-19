package org.fugerit.java.daogen.base.config;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.MessageFormat;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.PropertyCatalog;
import org.fugerit.java.core.lang.helpers.ClassHelper;

public class DaogenCustomCode {

	public static final String ID_LIST_COMMENTS = "comments";
	
	public static final String ID_COMMENTS_COMMON = "common";
	
	public static final String ID_COMMENTS_FACADE_DEF = "facade_def";
	
	public static final String ID_COMMENTS_REST = "rest";
	
	public static final String NO_INDENT = "";
	
	public static final String INDENT_1 = "	";
	
	public static void addCustomCode( String catalog, String holder, String key, String indent, PrintWriter pw, Object... params ) throws ConfigException {
		try {
			String propValue = getInstance().getProperty( catalog , holder, key );
			String content = propValue.trim().replaceAll( "'" , "''" );
			String newContent = MessageFormat.format( content , params );
			newContent = newContent.replaceAll( "OPB", "{" );
			newContent = newContent.replaceAll( "CLB", "}" );
			try ( BufferedReader reader = new BufferedReader( new StringReader( newContent ) ) ) {
				String line = reader.readLine();
				while ( line != null ) {
					pw.println( indent+line );
					line = reader.readLine();
				}
			}
		} catch (Exception e) {
			throw new ConfigException( e );
		}
	}
	
	public static void addComment( String holder, String key, String indent, PrintWriter pw, Object... params ) throws ConfigException {
		addCustomCode( ID_LIST_COMMENTS, holder, key, indent, pw, params);
	}
	
	public static void addCommentCommon( String key, String indent, PrintWriter pw, Object... params ) throws ConfigException {
		addComment( ID_COMMENTS_COMMON, key, indent, pw, params);
	}
	
	public static void addCommentFacadeDef( String key, String indent, PrintWriter pw, Object... params ) throws ConfigException {
		addComment( ID_COMMENTS_FACADE_DEF, key, indent, pw, params);
	}
	
	public static void addCommentRest( String key, String indent, PrintWriter pw, Object... params ) throws ConfigException {
		addComment( ID_COMMENTS_REST, key, indent, pw, params);
	}
	
	private static PropertyCatalog instance = null;
	
	public static PropertyCatalog getInstance() throws ConfigException {
		if ( instance == null ) {
			PropertyCatalog config = new PropertyCatalog();
			try {
				PropertyCatalog.load( ClassHelper.loadFromDefaultClassLoader( "config/property-catalog-config.xml" ) , config );
			} catch (Exception e) {
				throw new ConfigException( e );
			}
			instance = config;
		}
		return instance;
	}
	
}
