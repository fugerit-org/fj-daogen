package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseRestLoadHelperGenerator extends DaogenBasicGenerator {

	protected static final String NEW_CONTEXT_LIT = "try (CloseableDAOContext context = this.newDefaultContext() ) {";
	
	protected static final String CATCH_LIT = "} catch(Exception e) {" ;
	
	protected static final String ERROR_LOAD_LIT = "logger.error(\"ERRORE - REST- Load";
	
	protected static final String PRODUCE_JSON_LIT = "@Produces(MediaType.APPLICATION_JSON)";
	
	protected static final String RESPONSE_RES_NULL_LIT = "Response res = null;";
	
	protected static final String FACTORY_LIT = " factory = (";
	
	protected static final String ATT_NAME_LIT = ".ATT_NAME );";
	
	protected static final String LIST_LIT = "<List<";
	
	protected static final String CONTEXT_GET_ATTRIBUTE_LIT = ") context.getAttribute(";
	
	protected static final String FACADE_FACTORY_GET_LIT = " facade = factory.get";
	
	private String propertyPackage;
	
	private String key;
	
	private BaseRestLoadHelperGeneratorConfig config;
	
	protected BaseRestLoadHelperGenerator(String key, BaseRestLoadHelperGeneratorConfig config, String propertyPackage ) {
		super();
		this.key = key;
		this.config = config;
		this.propertyPackage = propertyPackage;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	protected String helperClass = null;
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		String fullObjectBName = fullObjectName( daogenConfig.getGeneralProp( this.propertyPackage ), DaogenCatalogConstants.restLoadName( entity ) );
		log.info( "propertyPackage:{}, fullObjectBName:{}", this.propertyPackage, fullObjectBName  );
		super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ), 
				DaogenHelperGenerator.toHelperClassName( fullObjectBName ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().addAll( this.config.getImportList() );
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
		this.setExtendsClass( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_REST_SERVICE )+LT_LIT+this.getEntityModelName()+GT_LIT );
	}
	
	private void printPrimaryKeyLoader( GeneratorKeyHelper primaryKeyHelper, boolean deep ) {
		String deepUrl = "";
		String deepMethod = "";
		String deepWorker = "";
		if ( deep ) {
			deepUrl = "/deep";
			deepMethod = "deep";
			deepWorker = "Deep";
		}
		this.printPrimaryKeyLoader(primaryKeyHelper, deepUrl, deepMethod, deepWorker);
	}
	
	protected abstract void printPrimaryKeyLoader( GeneratorKeyHelper primaryKeyHelper, String deepUrl, String deepMethod, String deepWorker );
	
	protected abstract void printLoadAll( String factoryClassName );
	
	protected abstract void printLoadCurrent( String urlName, String propertyName, String javaName, DaogenCatalogField field );
	
	private void generateDaogenBodyHandleLoadDeep( String factoryClassName, GeneratorKeyHelper primaryKeyHelper ) {
		this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getClassServiceResult()+LT_LIT+this.getEntityModelName()+"> "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"DeepWorker( DAOContext context, "+primaryKeyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
		this.getWriter().println( TAB_2+""+factoryClassName+FACTORY_LIT+factoryClassName+CONTEXT_GET_ATTRIBUTE_LIT+factoryClassName+ATT_NAME_LIT );
		this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+FACADE_FACTORY_GET_LIT+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( TAB_2+""+this.getEntityModelName()+" model = facade.loadById( context , "+primaryKeyHelper.getForwardParams()+" );" );
		this.getWriter().println( TAB_2+""+this.getClassServiceResult()+LT_LIT+this.getEntityModelName()+">  result = "+this.getClassServiceResult()+".newDefaultResult( model );" );
		if ( !this.getCurrentEntity().getRelations().isEmpty() ) {
			this.getWriter().println( TAB_2+"if ( result.getContent() != null ) {" );
			for ( DaogenCatalogRelation rel : this.getCurrentEntity().getRelations() ) {
				DaogenCatalogEntity entityTo = this.getDaogenConfig().getListMap( rel.getTo() );
				GeneratorKeyHelper relKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , entityTo, entityTo.getPrimaryKey() ).setForLoadInterface();
				if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( rel.getMode() ) && relKeyHelper.getKeyFields().size() == 1 ) {
					String javaName = GeneratorNameHelper.toClassName( rel.getKey() );
					String relMethod = DaogenCatalogConstants.restLoadName( entityTo )+".loadBy"+javaName+"( context, result.getContent().get"+GeneratorNameHelper.toClassName( this.getCurrentEntity().getPrimaryKey() )+"() ).getContent()";
					this.getWriter().println( TAB_3+"result.getContent().set"+GeneratorNameHelper.toClassName( rel.getName() )+"("+relMethod+");" );
				} else {
					GeneratorKeyHelper relKeyHelper1 = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), rel.getKey() ).setForLoadInterface();
					List<String> keyList = new ArrayList<String>();
					for ( String currentFieldKey : relKeyHelper1.getKeyFields() ) {
						keyList.add( "result.getContent().get"+GeneratorNameHelper.toClassName( currentFieldKey )+"()" );
					}
					String keyFields = ConcatHelper.concat( "," , keyList.toArray( new String[0] ) );
					String relMethod = DaogenCatalogConstants.restLoadName( entityTo )+"."+FacadeDefGenerator.METHOD_LOAD_BY_PK+"Worker( context, "+keyFields+" ).getContent()";
					this.getWriter().println( TAB_3+"result.getContent().set"+GeneratorNameHelper.toClassName( rel.getName() )+"("+relMethod+");" );
				}
			}
			this.getWriter().println( TAB_2+"}" );	
		}
		this.getWriter().println( TAB_2+RETURN_RESULT_LIT );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println( );
		this.printPrimaryKeyLoader(primaryKeyHelper, true);
		this.getWriter().println( );	
	}
	
	private void generateDaogenBodyHandleField() {
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String javaType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			if ( !field.getId().equalsIgnoreCase( this.getCurrentEntity().getPrimaryKey() ) && ( javaType.equalsIgnoreCase( "java.lang.String" ) || javaType.equals( "java.math.BigDecimal" ) ) ) {
				String javaName = GeneratorNameHelper.toClassName( field.getId() );
				String urlName = field.getId().toLowerCase();
				String propertyName = GeneratorNameHelper.toPropertyName( urlName );
				DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), propertyName, "current" );
				this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getClassServiceResult()+LIST_LIT+this.getEntityModelName()+">> loadBy"+javaName+"( DAOContext context, "+javaType+" current ) throws "+this.getClassDaoException()+" {" );
				this.getWriter().println( TAB_2+""+this.helperClass+" model = new "+this.helperClass+"();" );
				this.getWriter().println( TAB_2+"model.set"+javaName+"( current );" );
				this.getWriter().println( TAB_2+""+this.getClassServiceResult()+LIST_LIT+this.getEntityModelName()+">>  result = loadByModelWorker( context , model );" );
				this.getWriter().println( TAB_2+RETURN_RESULT_LIT );
				this.getWriter().println( TAB+"}" );
				this.getWriter().println( );
				this.printLoadCurrent(urlName, propertyName, javaName, field);
				this.getWriter().println( );
			}
		}
	}
	
	@Override
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		String factoryClassName = GeneratorNameHelper.classFromPackage( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) );
		// load by id
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() ).setForLoadInterface();
			this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getClassServiceResult()+LT_LIT+this.getEntityModelName()+"> "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"Worker( DAOContext context, "+primaryKeyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( TAB_2+""+factoryClassName+FACTORY_LIT+factoryClassName+CONTEXT_GET_ATTRIBUTE_LIT+factoryClassName+ATT_NAME_LIT );
			this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+FACADE_FACTORY_GET_LIT+this.getEntityFacadeDefName()+"();" );
			this.getWriter().println( TAB_2+""+this.getEntityModelName()+" model = facade.loadById( context , "+primaryKeyHelper.getForwardParams()+" );" );
			this.getWriter().println( TAB_2+""+this.getClassServiceResult()+LT_LIT+this.getEntityModelName()+">  result = "+this.getClassServiceResult()+".newDefaultResult( model );" );
			this.getWriter().println( TAB_2+RETURN_RESULT_LIT );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println( );
			// rest base code
			this.printPrimaryKeyLoader(primaryKeyHelper, false);
			this.getWriter().println( );
			// deep load
			this.generateDaogenBodyHandleLoadDeep(factoryClassName, primaryKeyHelper);		
		}
		// load all
		this.printLoadAll(factoryClassName);
		this.getWriter().println( );
		// load model worker
		DaogenCustomCode.addCommentRest( "rest.worker" ,DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName(), this.getEntityModelName(), "model" );
		this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getClassServiceResult()+LIST_LIT+this.getEntityModelName()+">> loadByModelWorker( DAOContext context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+" {" );
		this.getWriter().println( TAB_2+""+this.getEntityFinderName()+" finder = "+this.getEntityFinderName()+".newInstance( model );" );
		this.getWriter().println( TAB_2+""+factoryClassName+FACTORY_LIT+factoryClassName+CONTEXT_GET_ATTRIBUTE_LIT+factoryClassName+ATT_NAME_LIT );
		this.getWriter().println( TAB_2+""+this.getEntityFacadeDefName()+FACADE_FACTORY_GET_LIT+this.getEntityFacadeDefName()+"();" );
		this.getWriter().println( TAB_2+""+this.getClassBaseResult()+LT_LIT+this.getEntityModelName()+"> resultFacade = facade.loadAllByFinder( context , finder );" );
		this.getWriter().println( TAB_2+""+this.getClassServiceResult()+LIST_LIT+this.getEntityModelName()+">>  result = "+this.getClassServiceResult()+".newDefaultResult( resultFacade.getList() );" );
		this.getWriter().println( TAB_2+RETURN_RESULT_LIT );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println( );
		this.generateDaogenBodyHandleField();
	}

}
