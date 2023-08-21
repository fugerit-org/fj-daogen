package org.fugerit.java.daogen.base.gen.helper;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;
import org.fugerit.java.daogen.base.gen.GeneratorKeyHelper;

public class SpringBootLoadHelperGenerator extends BaseRestLoadHelperGenerator {

	public static final String KEY = SpringBootLoadHelperGenerator.class.getSimpleName();
	
	private static final BaseRestLoadHelperGeneratorConfig CONFIG = new BaseRestLoadHelperGeneratorConfig();
	static {
		List<String> importList = new ArrayList<String>();
		importList.add( "java.util.List" );
		importList.add( "javax.servlet.http.HttpServletResponse" );
		importList.add( "org.springframework.http.ResponseEntity" );
		importList.add( "org.springframework.web.bind.annotation.PathVariable" );
		importList.add( "org.springframework.web.bind.annotation.RequestMapping" );
		importList.add( "org.springframework.web.bind.annotation.RequestMethod" );
		importList.add( "org.springframework.web.bind.annotation.RequestMethod" );
		importList.add( "org.springframework.http.MediaType" );
		CONFIG.setImportList( importList );
	}
	
	public SpringBootLoadHelperGenerator() {
		super(KEY, CONFIG);
	}

	protected void printPrimaryKeyLoader( GeneratorKeyHelper primaryKeyHelper, String deepUrl, String deepMethod, String deepWorker ) {
		this.getWriter().println( TAB+"@RequestMapping(value = \""+deepUrl+primaryKeyHelper.getUrlParams()+"\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getByID"+deepMethod+"("+primaryKeyHelper.getPathVarables()+") throws Exception {" );
		this.getWriter().println( TAB_2+"ResponseEntity<Object> res = null;" );
		this.getWriter().println( TAB_2+"try (CloseableDAOContext context = this.newDefaultContext() ) {" );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+FacadeDefGenerator.METHOD_LOAD_BY_PK+deepWorker+"Worker( context, "+primaryKeyHelper.getRestParams()+" );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromObject( result ) );" );
		this.getWriter().println( TAB_2+"} catch(Exception e) {" );
		this.getWriter().println( TAB_3+"logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+"return res;" );
	}
	
	protected void printLoadAll( String factoryClassName ) {
		this.getWriter().println( TAB+"@RequestMapping(value = \"/all\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getAll() throws Exception {" );
		this.getWriter().println( TAB_2+"ResponseEntity<Object> res = null;" );
		this.getWriter().println( TAB_2+"try (CloseableDAOContext context = this.newDefaultContext() ) {" );
		this.getWriter().println( TAB_2+""+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( TAB_3+""+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> resultFacade = facade.loadAll( context );" );
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result) );" );
		this.getWriter().println( TAB_2+"} catch(Exception e) {" );
		this.getWriter().println( TAB_3+"logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll - \"+e, e );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+"return res;" );
		this.getWriter().println( TAB+"}" );
	}

	protected void printLoadCurrent( String urlName, String propertyName, String javaName, DaogenCatalogField field ) {
		this.getWriter().println( TAB+"@RequestMapping(value = \"/"+urlName+"/{"+urlName+"}\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( TAB+"public ResponseEntity<Object> getAll"+javaName+"(@PathVariable( \""+urlName+"\" ) String "+propertyName+") throws Exception {" );
		this.getWriter().println( TAB_2+"ResponseEntity<Object> res = null;" );
		this.getWriter().println( TAB_2+"try (CloseableDAOContext context = this.newDefaultContext() ) {" );
		if ( field.getJavaType().equals( "java.lang.String" ) ) {
			this.getWriter().println( TAB_3+"String value = "+propertyName+";" );
		} else {
			this.getWriter().println( TAB_3+"java.math.BigDecimal value = new java.math.BigDecimal("+propertyName+");" );
		}
		this.getWriter().println( TAB_3+""+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadBy"+javaName+"( context, value );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result ) );" );
		this.getWriter().println( TAB_2+"} catch(Exception e) {" );
		this.getWriter().println( TAB_3+"logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll"+javaName+" - \"+e, e );" );
		this.getWriter().println( TAB_3+"res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+"return res;" );
		this.getWriter().println( TAB+"}" );
	}

}
