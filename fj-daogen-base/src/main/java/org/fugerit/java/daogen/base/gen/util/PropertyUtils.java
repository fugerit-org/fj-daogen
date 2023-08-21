package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.core.io.helper.CustomPrintWriter;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

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
		writer.println( DaogenBasicGenerator.TAB+"private "+addTransient+realJavaType+" "+javaProperty+";" );
		writer.println();
		// setter method
		if ( override ) {
			writer.println( DaogenBasicGenerator.TAB+"@Override" );	
		}
		writer.println( DaogenBasicGenerator.TAB+"public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
		writer.println( DaogenBasicGenerator.TAB_2+"this."+javaProperty+" = value;" );
		writer.println( DaogenBasicGenerator.TAB+"}" );
		writer.println();
		// getter method
		if ( override ) {
			writer.println( DaogenBasicGenerator.TAB+"@Override" );	
		}
		writer.println( DaogenBasicGenerator.TAB+"public "+realJavaType+" get"+javaSuffix+"() {" );
		writer.println( DaogenBasicGenerator.TAB_2+"return this."+javaProperty+";" );
		writer.println( DaogenBasicGenerator.TAB+"}" );
		writer.println();
	}
	
}
