package test.org.fugerit.java.daogen.base.autodoc;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.doc.lib.autodoc.facade.XsdParserFacade;
import org.fugerit.java.doc.lib.autodoc.parser.model.AutodocModel;

public class MarsDaogenXsdAutodocFacade {

	public static final String CURRENT_VERSION = "1.0.0";
	
	public static final String TITLE = "Reference daogen-config.xsd documentation for Mars - Fugerit DAO Generation Framework (fj-daogen)";
	
	public static final String XSD_PREFIX = "xsd:";
	
	public static final String AUTODOC_PREFIX = "daogen:";
	
	public static AutodocModel parseLast() throws ConfigException {
		String path =  "./src/main/resources/config/daogen-config-1-0.xsd";
		XsdParserFacade xsdParserFacade = new XsdParserFacade();
		AutodocModel autodocModel = xsdParserFacade.parse( path );
		autodocModel.setVersion( CURRENT_VERSION );
		autodocModel.setTitle( TITLE );
		autodocModel.setXsdPrefix( XSD_PREFIX );
		autodocModel.setAutodocPrefix( AUTODOC_PREFIX );
		return autodocModel;
	}
	
}
