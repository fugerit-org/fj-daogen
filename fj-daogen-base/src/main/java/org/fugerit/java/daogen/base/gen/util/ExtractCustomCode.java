package org.fugerit.java.daogen.base.gen.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.function.SimpleValue;
import org.fugerit.java.core.io.FileIO;

public class ExtractCustomCode {

	private ExtractCustomCode() {}
	
	public static String extractCustom( File file, String startTag, String endTag ) {
		return SafeFunction.get( () -> extractCustom( FileIO.readString( file ) , startTag, endTag ) );
	}
	
	public static String extractCustom( CharSequence text, String startTag, String endTag ) {
		return SafeFunction.get( () -> {
			try ( BufferedReader reader = new BufferedReader( new StringReader( text.toString() ) );
					StringWriter buffer = new StringWriter(); 
					PrintWriter writer = new PrintWriter( buffer, true ) ) {
				SimpleValue<Boolean> customCode = new SimpleValue<>( false );
				reader.lines().forEach( line -> {
					if ( line.contains( startTag ) ) {
						customCode.setValue( true );
					} else if ( line.contains( endTag ) ) {
						customCode.setValue( false );
					} else if ( customCode.getValue().booleanValue() ) {
						writer.println( line );
					}
				} );
				return buffer.toString();
			}
		} );
	}
	
	public static String addCustomContent( CharSequence text, String startTag, String endTag, String customContent ) {
		return SafeFunction.get( () -> {
			try ( BufferedReader reader = new BufferedReader( new StringReader( text.toString() ) );
					StringWriter buffer = new StringWriter(); 
					PrintWriter writer = new PrintWriter( buffer, true ) ) {
				SimpleValue<Boolean> customCode = new SimpleValue<>( false );
				reader.lines().forEach( line -> {
					if ( line.contains( startTag ) ) {
						customCode.setValue( true );
					} else if ( line.contains( endTag ) ) {
						writer.println( customContent );	// append custom content
						customCode.setValue( false );
					} 
					// all lines myst be written anyway
					writer.println( line );
				} );
				return buffer.toString();
			}
		} );
	}
	
}
