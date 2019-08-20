package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;

public class RestLoadGenerator extends DaogenBasicGenerator {

	public static final String KEY = "RestLoadGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_REST_LOAD ), DaogenCatalogConstants.restLoadName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( "java.util.List" );
		this.getImportList().add( "java.math.BigDecimal" );
		this.getImportList().add( "javax.ejb.Stateless" );
		this.getImportList().add( "javax.ws.rs.GET" );
		this.getImportList().add( "javax.ws.rs.Path" );
		this.getImportList().add( "javax.ws.rs.PathParam" );
		this.getImportList().add( "javax.ws.rs.Produces" );
		this.getImportList().add( "javax.ws.rs.core.MediaType" );
		this.getImportList().add( "javax.ws.rs.core.Response" );
		this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
		this.setClassCloseableDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CLOSEABLECONTEXT_BASE, this.getImportList() ) );
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
		this.setClassServiceResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_SERVICERESULT_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFinderName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFacadeDefName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) );
		this.setExtendsClass( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_REST_SERVICE ) );
	}

	@Override
	protected void beforeClass() {
		String urlBase = this.getCurrentEntity().getName().replaceAll( "_" , "" ).toLowerCase();
		this.getWriter().println( "@Stateless" );
		this.getWriter().println( "@Path(\"/"+urlBase+"/load\")" );
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		String factoryClassName = GeneratorNameHelper.classFromPackage( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) );
		// load by id
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() ).setForLoadInterface();
			//DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), "id", "id" );
			this.getWriter().println( "	public static "+this.getClassServiceResult()+"<"+this.getEntityModelName()+"> "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"Worker( DAOContext context, "+primaryKeyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		"+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
			this.getWriter().println( "		"+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
			this.getWriter().println( "		"+this.getEntityModelName()+" model = facade.loadById( context , "+primaryKeyHelper.getForwardParams()+" );" );
			this.getWriter().println( "		"+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+this.getClassServiceResult()+".newDefaultResult( model );" );
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println( );
			this.getWriter().println( "	@GET" );
			this.getWriter().println( "	@Path(\""+primaryKeyHelper.getUrlParams()+"\")" );
			this.getWriter().println( "	@Produces(MediaType.APPLICATION_JSON)" );
			this.getWriter().println( "	public Response getByID("+primaryKeyHelper.getPathParams()+") throws Exception {" );
			this.getWriter().println( "		Response res = null;" );
			this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
			this.getWriter().println( "			"+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = loadByIdWorker( context, "+primaryKeyHelper.getRestParams()+" );" );
			this.getWriter().println( "			res = Response.ok( result ).build();" );
			this.getWriter().println( "		} catch(Exception e) {" );
			this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
			this.getWriter().println( "		}" );
			this.getWriter().println( "		return res;" );
			this.getWriter().println( "	}" );
			this.getWriter().println( );
		}
		// load all
		this.getWriter().println( "	@GET" );
		this.getWriter().println( "	@Path(\"/all\")" );
		this.getWriter().println( "	@Produces(MediaType.APPLICATION_JSON)" );
		this.getWriter().println( "	public Response getAll() throws Exception {" );
		this.getWriter().println( "		Response res = null;" );
		this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
		this.getWriter().println( "		"+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( "		"+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( "			"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> resultFacade = facade.loadAll( context );" );
		this.getWriter().println( "			"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( "			res = Response.ok( result ).build();" );
		this.getWriter().println( "		} catch(Exception e) {" );
		this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll - \"+e, e );" );
		this.getWriter().println( "		}" );
		this.getWriter().println( "		return res;" );
		this.getWriter().println( "	}" );
		this.getWriter().println( );
		// load model worker
		DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), this.getEntityModelName(), "model" );
		this.getWriter().println( "	public static "+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">> loadByModelWorker( DAOContext context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+" {" );
		this.getWriter().println( "		"+this.getEntityFinderName()+" finder = "+this.getEntityFinderName()+".newInstance( model );" );
		this.getWriter().println( "		"+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( "		"+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( "		"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> resultFacade = facade.loadAllByFinder( context , finder );" );
		this.getWriter().println( "		"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( "		return result;" );
		this.getWriter().println( "	}" );
		this.getWriter().println( );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String javaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			if ( !field.getId().equals( "id") && ( javaType.equalsIgnoreCase( "java.lang.String" ) || javaType.equals( "java.math.BigDecimal" ) ) ) {
				String javaName = GeneratorNameHelper.toClassName( field.getId() );
				String urlName = field.getId().toLowerCase();
				String propertyName = GeneratorNameHelper.toPropertyName( urlName );
				DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), propertyName, "current" );
				this.getWriter().println( "	public static "+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">> loadBy"+javaName+"( DAOContext context, "+javaType+" current ) throws "+this.getClassDaoException()+" {" );
				this.getWriter().println( "		"+this.getEntityHelperName()+" model = new "+this.getEntityHelperName()+"();" );
				this.getWriter().println( "		model.set"+javaName+"( current );" );
				this.getWriter().println( "		"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadByModelWorker( context , model );" );
				this.getWriter().println( "		return result;" );
				this.getWriter().println( "	}" );
				this.getWriter().println( );
				this.getWriter().println( "	@GET" );
				this.getWriter().println( "	@Path(\"/"+urlName+"/{"+urlName+"}\")" );
				this.getWriter().println( "	@Produces(MediaType.APPLICATION_JSON)" );
				this.getWriter().println( "	public Response getAll"+javaName+"(@PathParam( \""+urlName+"\" ) String "+propertyName+") throws Exception {" );
				this.getWriter().println( "		Response res = null;" );
				this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
				if ( field.getJavaType().equals( "java.lang.String" ) ) {
					this.getWriter().println( "			String value = "+propertyName+";" );
				} else {
					this.getWriter().println( "			BigDecimal value = new BigDecimal("+propertyName+");" );
				}
				this.getWriter().println( "			"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadBy"+javaName+"( context, value );" );
				this.getWriter().println( "			res = Response.ok( result ).build();" );
				this.getWriter().println( "		} catch(Exception e) {" );
				this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll"+javaName+" - \"+e, e );" );
				this.getWriter().println( "		}" );
				this.getWriter().println( "		return res;" );
				this.getWriter().println( "	}" );
				this.getWriter().println( );
			}
		}	
	}

}
