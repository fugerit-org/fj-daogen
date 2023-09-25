package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;
import org.fugerit.java.daogen.base.gen.GeneratorKeyHelper;

public class SpringBootLoadHelperGenerator extends BaseRestLoadHelperGenerator {

	public static final String KEY = SpringBootLoadHelperGenerator.class.getSimpleName();
	
	private static final String RESPONSE_ENTITY_LIT = "ResponseEntity<Object> res = null;";
	
	private static final String RES_BUILD_LIT = "res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();";
	
	public SpringBootLoadHelperGenerator() {
		super(KEY, DaogenCatalogConstants.GEN_PROP_PACKAGE_SPRING_REST_LOAD);
	}

	@Override
	protected void populateImportListBase() {
		String jeeTarget = this.getJeeTargetMode();
		this.getImportList().add( "java.util.List" );
		this.getImportList().add( jeeTarget+".servlet.http.HttpServletResponse" );
		this.getImportList().add( "org.springframework.http.ResponseEntity" );
		this.getImportList().add( "org.springframework.web.bind.annotation.PathVariable" );
		this.getImportList().add( "org.springframework.web.bind.annotation.GetMapping" );
		this.getImportList().add( "org.springframework.http.MediaType" );
	}
	
	protected void printPrimaryKeyLoader( GeneratorKeyHelper primaryKeyHelper, String deepUrl, String deepMethod, String deepWorker ) {
		this.getWriter().println( TAB+"@GetMapping(value = \""+deepUrl+primaryKeyHelper.getUrlParams()+"\", produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getByID"+deepMethod+"("+primaryKeyHelper.getPathVarables()+") throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_ENTITY_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+FacadeDefGenerator.METHOD_LOAD_BY_PK+deepWorker+"Worker( context, "+primaryKeyHelper.getRestParams()+" );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromObject( result ) );" );
		this.getWriter().println( TAB_2+CATCH_LIT);
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
		this.getWriter().println( TAB_3+RES_BUILD_LIT );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}
	
	protected void printLoadAll( String factoryClassName ) {
		this.getWriter().println( TAB+"@GetMapping(value = \"/all\", produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getAll() throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_ENTITY_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		this.getWriter().println( TAB_2+""+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( TAB_3+""+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> resultFacade = facade.loadAll( context );" );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result) );" );
		this.getWriter().println( TAB_2+CATCH_LIT);
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getAll - \"+e, e );" );
		this.getWriter().println( TAB_3+RES_BUILD_LIT );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}

	protected void printLoadCurrent( String urlName, String propertyName, String javaName, DaogenCatalogField field ) {
		this.getWriter().println( TAB+"@GetMapping(value = \"/"+urlName+"/{"+urlName+"}\", produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getAll"+javaName+"(@PathVariable( \""+urlName+"\" ) String "+propertyName+") throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_ENTITY_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		if ( field.getJavaType().equals( "java.lang.String" ) ) {
			this.getWriter().println( TAB_3+"String value = "+propertyName+";" );
		} else {
			this.getWriter().println( TAB_3+"java.math.BigDecimal value = new java.math.BigDecimal("+propertyName+");" );
		}
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadBy"+javaName+"( context, value );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result ) );" );
		this.getWriter().println( TAB_2+CATCH_LIT);
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getAll"+javaName+" - \"+e, e );" );
		this.getWriter().println( TAB_3+RES_BUILD_LIT );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}

}
