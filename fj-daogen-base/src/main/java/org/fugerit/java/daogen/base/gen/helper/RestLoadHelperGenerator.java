package org.fugerit.java.daogen.base.gen.helper;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;
import org.fugerit.java.daogen.base.gen.GeneratorKeyHelper;

public class RestLoadHelperGenerator extends BaseRestLoadHelperGenerator {

	public static final String KEY = RestLoadHelperGenerator.class.getSimpleName();
	
	private static final BaseRestLoadHelperGeneratorConfig CONFIG = new BaseRestLoadHelperGeneratorConfig();
	static {
		List<String> importList = new ArrayList<String>();
		importList.add( "java.util.List" );
		importList.add( "javax.ws.rs.GET" );
		importList.add( "javax.ws.rs.Path" );
		importList.add( "javax.ws.rs.PathParam" );
		importList.add( "javax.ws.rs.Produces" );
		importList.add( "javax.ws.rs.core.MediaType" );
		importList.add( "javax.ws.rs.core.Response" );
		CONFIG.setImportList( importList );
	}
	
	public RestLoadHelperGenerator() {
		super(KEY, CONFIG, DaogenCatalogConstants.GEN_PROP_PACKAGE_REST_LOAD);
	}
	
	protected void printPrimaryKeyLoader( GeneratorKeyHelper primaryKeyHelper, String deepUrl, String deepMethod, String deepWorker ) {
		this.getWriter().println( TAB+"@GET" );
		this.getWriter().println( TAB+"@Path(\""+deepUrl+primaryKeyHelper.getUrlParams()+"\")" );
		this.getWriter().println( TAB+PRODUCE_JSON_LIT );
		this.getWriter().println( TAB+"public Response getByID"+deepMethod+"("+primaryKeyHelper.getPathParams()+") throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_RES_NULL_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+LT_LIT+this.getEntityModelName()+">  result = "+FacadeDefGenerator.METHOD_LOAD_BY_PK+deepWorker+"Worker( context, "+primaryKeyHelper.getRestParams()+" );" );
		this.getWriter().println( TAB_3+"res = this.createResponseFromObject( result );" );
		this.getWriter().println( TAB_2+CATCH_LIT );
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}
	
	protected void printLoadAll( String factoryClassName ) {
		this.getWriter().println( TAB+"@GET" );
		this.getWriter().println( TAB+"@Path(\"/all\")" );
		this.getWriter().println( TAB+PRODUCE_JSON_LIT );
		this.getWriter().println( TAB+"public Response getAll() throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_RES_NULL_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		this.getWriter().println( TAB_2+""+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( TAB_3+""+this.getClassBaseResult()+LT_LIT+this.getEntityModelName()+"> resultFacade = facade.loadAll( context );" );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( TAB_3+"res = this.createResponseFromList( result );" );
		this.getWriter().println( TAB_2+CATCH_LIT );
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getAll - \"+e, e );" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}

	protected void printLoadCurrent( String urlName, String propertyName, String javaName, DaogenCatalogField field ) {
		this.getWriter().println( TAB+"@GET" );
		this.getWriter().println( TAB+"@Path(\"/"+urlName+"/{"+urlName+"}\")" );
		this.getWriter().println( TAB+PRODUCE_JSON_LIT );
		this.getWriter().println( TAB+"public Response getAll"+javaName+"(@PathParam( \""+urlName+"\" ) String "+propertyName+") throws Exception {" );
		this.getWriter().println( TAB_2+RESPONSE_RES_NULL_LIT );
		this.getWriter().println( TAB_2+NEW_CONTEXT_LIT );
		if ( field.getJavaType().equals( "java.lang.String" ) ) {
			this.getWriter().println( TAB_3+"String value = "+propertyName+";" );
		} else {
			this.getWriter().println( TAB_3+"java.math.BigDecimal value = new java.math.BigDecimal("+propertyName+");" );
		}
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadBy"+javaName+"( context, value );" );
		this.getWriter().println( TAB_3+"res = this.createResponseFromList( result );" );
		this.getWriter().println( TAB_2+CATCH_LIT );
		this.getWriter().println( TAB_3+ERROR_LOAD_LIT+this.getCurrentEntity().toClassName()+" - getAll"+javaName+" - \"+e, e );" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
	}

}
