package test.org.fugerit.java.daogen.base.autodoc;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;

import org.fugerit.java.doc.lib.autodoc.AutodocDocConfig;
import org.fugerit.java.doc.lib.autodoc.parser.model.AutodocModel;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestGenerateXsdAutodoc {

	private static final String TARGET = "../docs/config/daogen-config-xsd-ref.html";
	
	@Test
	public void testParseXsdModel() {
		try ( FileOutputStream fos = new FileOutputStream( new File( TARGET ) ) )  {
			AutodocModel autodocModel = MarsDaogenXsdAutodocFacade.parseLast();
			AutodocDocConfig docConfig = AutodocDocConfig.newConfig();
			docConfig.processAutodocHtmlDefault(autodocModel, fos);
		} catch (Exception e) {
			String message = "Error : "+e;
			log.error( message, e );
			fail( message );
		}
	}
	
}
