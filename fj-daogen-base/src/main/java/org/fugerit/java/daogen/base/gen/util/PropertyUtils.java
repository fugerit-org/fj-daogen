package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.core.io.helper.CustomPrintWriter;
import org.fugerit.java.core.javagen.GeneratorNameHelper;

public class PropertyUtils {

	public static void newPropertyOverride( CustomPrintWriter writer, String fieldId, String realJavaType  ) {
		newProperty( writer, fieldId, realJavaType, true, false );
	}
	
	public static void newProperty( CustomPrintWriter writer, String fieldId, String realJavaType  ) {
		newProperty( writer, fieldId, realJavaType, false, false );
	}
	
	public static void newProperty( CustomPrintWriter writer, String fieldId, String realJavaType, boolean override, boolean modTransient  ) {
		String javaProperty = GeneratorNameHelper.toPropertyName( fieldId );
		String javaSuffix = GeneratorNameHelper.toClassName( fieldId );
		String addTransient = "";
		if ( modTransient ) {
			addTransient = "transient ";
		}
		writer.println( "	private "+addTransient+realJavaType+" "+javaProperty+";" );
		writer.println();
		// setter method
		if ( override ) {
			writer.println( "	@Override" );	
		}
		writer.println( "	public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
		writer.println( "		this."+javaProperty+" = value;" );
		writer.println( "	}" );
		writer.println();
		// getter method
		if ( override ) {
			writer.println( "	@Override" );	
		}
		writer.println( "	public "+realJavaType+" get"+javaSuffix+"() {" );
		writer.println( "		return this."+javaProperty+";" );
		writer.println( "	}" );
		writer.println();
	}
	
}
