package org.fugerit.java.daogen.base.gen.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.function.SimpleValue;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.lang.helpers.StringUtils;

import lombok.Getter;
import lombok.Setter;

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
	
	private static String addWithCondition( CharSequence text, String customContent, Predicate<LineCursor> condition ) {
		return SafeFunction.get( () -> {
			try ( BufferedReader reader = new BufferedReader( new StringReader( text.toString() ) );
					StringWriter buffer = new StringWriter(); 
					PrintWriter writer = new PrintWriter( buffer, true ) ) {
				List<String> lines = reader.lines().collect( Collectors.toList() );
				LineCursor cursor = new LineCursor();
				cursor.setLines( lines );
				cursor.setIndex( 0 );
				while ( cursor.getIndex()<lines.size() ) {
					String currentLine = lines.get( cursor.getIndex() );
					if ( condition.test( cursor ) ) {
						writer.println( customContent );
					}
					writer.println( currentLine );
					cursor.setIndex( cursor.getIndex()+1 );
				}
				return buffer.toString();
			}
		} );
	}
	
	public static String addAfterPackageClassEnd( CharSequence text, String customContent ) {
		return addWithCondition(text, customContent, c -> {
			boolean ok = false;
			String previousLine = c.getPreviousLine();
			if ( StringUtils.isNotEmpty( previousLine ) ) {
				ok = previousLine.trim().startsWith( "package" );
			}
			return ok;
		} );
	}
	
	public static String addBeforeClassEnd( CharSequence text, String customContent ) {
		return addWithCondition(text, customContent, c -> c.isLast() && c.getCurrentLine().trim().equals( "}" ) );
	}
	
}

class LineCursor {
	
	@Getter @Setter private List<String> lines;
	
	@Getter @Setter private int index;
	
	public boolean isLast() {
		return this.lines.size()-1 == this.index;
	}
	
	public String getPreviousLine() {
		return this.index==0 ? null : this.lines.get( this.index-1 );
	}
	
	public String getCurrentLine() {
		return this.lines.get( this.index );
	}
}
