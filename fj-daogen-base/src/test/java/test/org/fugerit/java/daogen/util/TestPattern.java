package test.org.fugerit.java.daogen.util;

import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPattern {

	private boolean worker( String patternPathAnnotation, String urlBase, String expected ) {
		log.info( "patternPathAnnotation : {}, urlBase : {}", patternPathAnnotation, urlBase );
		String pathAnnotation = MessageFormat.format( patternPathAnnotation , urlBase);
		log.info( "pathAnnotation : {}", pathAnnotation );
		return expected == null || expected.equals( pathAnnotation );
	}
	
	@Test
	public void testSpringBootPathAnnotation() {
		boolean ok = this.worker( "@RequestMapping(\"/{0}/load\")", "document", "@RequestMapping(\"/document/load\")" );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testJaxRsPathAnnotation() {
		boolean ok = this.worker( "@Path(\"/{0}/load\")", "document", "@Path(\"/document/load\")" );
		Assert.assertTrue( ok );
	}
	
}
