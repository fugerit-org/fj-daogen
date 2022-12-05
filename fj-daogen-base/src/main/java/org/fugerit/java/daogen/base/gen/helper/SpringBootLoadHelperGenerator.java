package org.fugerit.java.daogen.base.gen.helper;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.ConcatHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenCatalogRelation;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.fugerit.java.daogen.base.gen.FacadeDefGenerator;
import org.fugerit.java.daogen.base.gen.GeneratorKeyHelper;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

public class SpringBootLoadHelperGenerator extends DaogenBasicGenerator {

	public static final String KEY = SpringBootLoadHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	private String helperClass = null;
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ), 
				DaogenHelperGenerator.toHelperClassName( fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_SPRING_REST_LOAD ), DaogenCatalogConstants.restLoadName( entity ) ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( "java.util.List" );
		this.getImportList().add( "javax.servlet.http.HttpServletResponse" );
		this.getImportList().add( "org.springframework.http.ResponseEntity" );
		this.getImportList().add( "org.springframework.web.bind.annotation.PathVariable" );
		this.getImportList().add( "org.springframework.web.bind.annotation.RequestMapping" );
		this.getImportList().add( "org.springframework.web.bind.annotation.RequestMethod" );
		this.getImportList().add( "org.springframework.web.bind.annotation.RequestMethod" );
		this.getImportList().add( "org.springframework.http.MediaType" );
		this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
		this.setClassCloseableDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CLOSEABLECONTEXT_BASE, this.getImportList() ) );
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
		this.setClassServiceResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_SERVICERESULT_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.helperClass = this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName();
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFinderName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFacadeDefName() );
		String facadeDef = this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF );
		if ( facadeDef != null ) {
			this.getImportList().add( facadeDef );	
		}
		this.setExtendsClass( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_REST_SERVICE )+"<"+this.getEntityModelName()+">" );
	}

	@Override
	public void generateDaogenBody() throws Exception {
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
			this.getWriter().println( "	@RequestMapping(value = \""+primaryKeyHelper.getUrlParams()+"\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
			this.getWriter().println( "	public ResponseEntity<Object> getByID("+primaryKeyHelper.getPathVarables()+") throws Exception {" );
			this.getWriter().println( "		ResponseEntity<Object> res = null;" );
			this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
			this.getWriter().println( "			"+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"Worker( context, "+primaryKeyHelper.getRestParams()+" );" );
			this.getWriter().println( "			res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromObject( result ) );" );
			this.getWriter().println( "		} catch(Exception e) {" );
			this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
			this.getWriter().println( "			res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
			this.getWriter().println( "		}" );
			this.getWriter().println( "		return res;" );
			this.getWriter().println( "	}" );
			this.getWriter().println( );
			// deep load
			this.getWriter().println( "	public static "+this.getClassServiceResult()+"<"+this.getEntityModelName()+"> "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"DeepWorker( DAOContext context, "+primaryKeyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		"+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
			this.getWriter().println( "		"+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
			this.getWriter().println( "		"+this.getEntityModelName()+" model = facade.loadById( context , "+primaryKeyHelper.getForwardParams()+" );" );
			this.getWriter().println( "		"+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+this.getClassServiceResult()+".newDefaultResult( model );" );
			this.getWriter().println( "		if ( result.getContent() != null ) {" );
			for ( DaogenCatalogRelation rel : this.getCurrentEntity().getRelations() ) {
				DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( rel.getTo() );
				GeneratorKeyHelper relKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , entityTo, entityTo.getPrimaryKey() ).setForLoadInterface();
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( rel.getMode() ) && relKeyHelper.getKeyFields().size() == 1 ) {
					String javaName = GeneratorNameHelper.toClassName( rel.getKey() );
					String relMethod = DaogenCatalogConstants.restLoadName( entityTo )+".loadBy"+javaName+"( context, result.getContent().get"+GeneratorNameHelper.toClassName( this.getCurrentEntity().getPrimaryKey() )+"() ).getContent()";
					this.getWriter().println( "			result.getContent().set"+GeneratorNameHelper.toClassName( rel.getName() )+"("+relMethod+");" );
				} else {
					GeneratorKeyHelper relKeyHelper1 = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), rel.getKey() ).setForLoadInterface();
					List<String> keyList = new ArrayList<String>();
					for ( String currentFieldKey : relKeyHelper1.getKeyFields() ) {
						keyList.add( "result.getContent().get"+GeneratorNameHelper.toClassName( currentFieldKey )+"()" );
					}
					String keyFields = ConcatHelper.concat( "," , keyList.toArray( new String[0] ) );
					String relMethod = DaogenCatalogConstants.restLoadName( entityTo )+"."+FacadeDefGenerator.METHOD_LOAD_BY_PK+"Worker( context, "+keyFields+" ).getContent()";
					this.getWriter().println( "			result.getContent().set"+GeneratorNameHelper.toClassName( rel.getName() )+"("+relMethod+");" );
				}
			}
			this.getWriter().println( "		}" );
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println( );
			this.getWriter().println( "	@RequestMapping(value = \"/deep"+primaryKeyHelper.getUrlParams()+"\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
			this.getWriter().println( "	public ResponseEntity<Object> getByIDdeep("+primaryKeyHelper.getPathVarables()+") throws Exception {" );
			this.getWriter().println( "		ResponseEntity<Object> res = null;" );
			this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
			this.getWriter().println( "			"+this.getClassServiceResult()+"<"+this.getEntityModelName()+">  result = "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"DeepWorker( context, "+primaryKeyHelper.getRestParams()+" );" );
			this.getWriter().println( "			res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromObject( result ) );" );
			this.getWriter().println( "		} catch(Exception e) {" );
			this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getByID - \"+e, e );" );
			this.getWriter().println( "			res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
			this.getWriter().println( "		}" );
			this.getWriter().println( "		return res;" );
			this.getWriter().println( "	}" );
			this.getWriter().println( );			
		}
		// load all
		this.getWriter().println( "	@RequestMapping(value = \"/all\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
		this.getWriter().println( "	public ResponseEntity<Object> getAll() throws Exception {" );
		this.getWriter().println( "		ResponseEntity<Object> res = null;" );
		this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
		this.getWriter().println( "		"+factoryClassName+" factory = ("+factoryClassName+") context.getAttribute("+factoryClassName+".ATT_NAME );" );
		this.getWriter().println( "		"+this.getEntityFacadeDefName()+" facade = factory.get"+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( "			"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> resultFacade = facade.loadAll( context );" );
		this.getWriter().println( "			"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( "			res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result) );" );
		this.getWriter().println( "		} catch(Exception e) {" );
		this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll - \"+e, e );" );
		this.getWriter().println( "			res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
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
			if ( !field.getId().equalsIgnoreCase( this.getCurrentEntity().getPrimaryKey() ) && ( javaType.equalsIgnoreCase( "java.lang.String" ) || javaType.equals( "java.math.BigDecimal" ) ) ) {
				String javaName = GeneratorNameHelper.toClassName( field.getId() );
				String urlName = field.getId().toLowerCase();
				String propertyName = GeneratorNameHelper.toPropertyName( urlName );
				DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), propertyName, "current" );
				this.getWriter().println( "	public static "+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">> loadBy"+javaName+"( DAOContext context, "+javaType+" current ) throws "+this.getClassDaoException()+" {" );
				this.getWriter().println( "		"+this.helperClass+" model = new "+this.helperClass+"();" );
				this.getWriter().println( "		model.set"+javaName+"( current );" );
				this.getWriter().println( "		"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadByModelWorker( context , model );" );
				this.getWriter().println( "		return result;" );
				this.getWriter().println( "	}" );
				this.getWriter().println( );
				this.getWriter().println( "	@RequestMapping(value = \"/"+urlName+"/{"+urlName+"}\", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )" );
				this.getWriter().println( "	public ResponseEntity<Object> getAll"+javaName+"(@PathVariable( \""+urlName+"\" ) String "+propertyName+") throws Exception {" );
				this.getWriter().println( "		ResponseEntity<Object> res = null;" );
				this.getWriter().println( "		try (CloseableDAOContext context = this.newDefaultContext() ) {" );
				if ( field.getJavaType().equals( "java.lang.String" ) ) {
					this.getWriter().println( "			String value = "+propertyName+";" );
				} else {
					this.getWriter().println( "			java.math.BigDecimal value = new java.math.BigDecimal("+propertyName+");" );
				}
				this.getWriter().println( "			"+this.getClassServiceResult()+"<List<"+this.getEntityModelName()+">>  result = loadBy"+javaName+"( context, value );" );
				this.getWriter().println( "			res = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body( this.createResultFromList( result ) );" );
				this.getWriter().println( "		} catch(Exception e) {" );
				this.getWriter().println( "			logger.error(\"ERRORE - REST- "+"Load"+this.getCurrentEntity().toClassName()+" - getAll"+javaName+" - \"+e, e );" );
				this.getWriter().println( "			res = ResponseEntity.status( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ).build();" );
				this.getWriter().println( "		}" );
				this.getWriter().println( "		return res;" );
				this.getWriter().println( "	}" );
				this.getWriter().println( );
			}
		}	
	}

}
