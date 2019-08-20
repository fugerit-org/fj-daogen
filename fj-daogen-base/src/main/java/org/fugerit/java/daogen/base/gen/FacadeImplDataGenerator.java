package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class FacadeImplDataGenerator extends DaogenBasicGenerator {

	public static final String KEY = "FacadeImplDataGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DATA_IMPL ), DaogenCatalogConstants.facadeImplDataName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_RSE )+"."+this.getEntityRSEName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFinderName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF )+"."+this.getEntityFacadeDefName() );
		this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
		this.setClassDaoHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_DAOHELPER_BASE, this.getImportList() ) );
		this.setClassDataFacade( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_DATAFACADE_BASE, this.getImportList() ) );
		this.setClassSelectHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_SELECTHELPER_BASE, this.getImportList() ) );
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			this.setClassInsertHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_INSERTHELPER_BASE, this.getImportList() ) );
			this.setClassUpdateHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_UPDATEHELPER_BASE, this.getImportList() ) );
			this.setClassDeleteHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_DELETEHELPER_BASE, this.getImportList() ) );
		}
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
		this.setImplementsInterface( this.getEntityFacadeDefName() );
		this.setExtendsClass( this.getClassDataFacade()+"<"+this.getEntityModelName()+">" );
	}

	private static String columnConstantName( String col ) {
		return "COL_"+col.toUpperCase();
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		String autoSetDateInsert = this.getDaogenConfig().getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_DEFAULT_COLUMN_TIME_INSERT );
		String autoSetDateUpdate = this.getDaogenConfig().getGeneralProps().getProperty( DaogenCatalogConstants.GEN_PROP_DEFAULT_COLUMN_TIME_UPDATE );
		this.getWriter().println( "	public "+this.getEntityFacadeDataImplName()+"() {" );
		this.getWriter().println( "		super( \""+this.getCurrentEntity().getId()+"\", "+this.getEntityRSEName()+".DEFAULT );");
		this.getWriter().println( "	}");
		this.getWriter().println();
		String sequenceName = this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_DEFAULT_SEQUENCE );
		if ( StringUtils.isNotEmpty( sequenceName ) ) {
			this.getWriter().println( " 	public final static String SEQUENCE_NAME = \""+sequenceName+"\";" );
			this.getWriter().println();	
			this.getWriter().println( " 	@Override" );
			this.getWriter().println( " 	public String getSequenceName() {" );
			this.getWriter().println( " 		return SEQUENCE_NAME;" );
			this.getWriter().println( " 	}" );
			this.getWriter().println();	
		}
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.getWriter().println( " 	public final static String "+columnConstantName( field.getId() )+ " = \""+field.getId()+"\";" );
		}
		this.getWriter().println();
		this.getWriter().println( "	/* loadAll( context ) is inherited from BasicDataFacade */" );
		this.getWriter().println();		
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public "+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAllByFinder( "+this.getClassDaogenContext()+" context, "+this.getEntityFinderName()+" finder ) throws DAOException {" );
		this.getWriter().println( "		"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> result = new "+this.getClassBaseResult()+"<>();" );
		this.getWriter().println( "		"+this.getClassDaoHelper()+"<"+this.getEntityModelName()+"> daoHelper = new "+this.getClassDaoHelper()+"<>( context );" );
		this.getWriter().println( "		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );" );
		if ( this.getCurrentEntity().containsDefaultId() ) {
			this.getWriter().println( "		query.andEqualParam( COL_ID, finder.getId() );" );	
		}
		this.getWriter().println( "		if ( finder.getModel() != null ) {" );
		this.getWriter().println( "			"+this.getEntityModelName()+" model = finder.getModel();" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.getWriter().println( "			query.andEqualParam( "+columnConstantName( field.getId() )+", model.get"+GeneratorNameHelper.toClassName( field.getId() )+"() );" );
		}
		this.getWriter().println( "		}" );
		this.getWriter().println( "		daoHelper.loadAllHelper( result.getList(), query, this.getRse() ); " );
		this.getWriter().println( "		return result;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();		
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() );
			// create
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> create( "+this.getClassDaogenContext()+" context, "+this.getEntityModelName()+" model ) throws DAOException {" );
			this.getWriter().println( "		"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> result = new "+this.getClassBaseResult()+"<>();" );
			this.getWriter().println( "		"+this.getClassDaoHelper()+"<"+this.getEntityModelName()+"> daoHelper = new "+this.getClassDaoHelper()+"<>( context );" );
			if ( sequenceName != null ) {
				for ( String currentField : primaryKeyHelper.getKeyFields() ) {
					DaogenCatalogField field = this.getCurrentEntity().get( currentField );
					if ( field.getJavaType().equals( "java.math.BigDecimal" ) || field.getJavaType().equals( "java.lang.Long" ) ) {
						String className = GeneratorNameHelper.toClassName( currentField );
						this.getWriter().println( "		if ( model.get"+className+"() == null ) { " );
						this.getWriter().println( "			model.set"+className+"( this.generateId( context ) ); " );
						this.getWriter().println( "		} " );		
					}
				}
			}
			if ( autoSetDateInsert != null ) {
				DaogenCatalogField colData = this.getCurrentEntity().get( autoSetDateInsert );
				if ( colData != null ) {
					this.getWriter().println( "		//  "+DaogenCatalogConstants.GEN_PROP_DEFAULT_COLUMN_TIME_INSERT+" : true - i will set insert time" );	
					this.getWriter().println( "		model.set"+GeneratorNameHelper.toClassName( colData.getId() )+"( new java.sql.Timestamp( System.currentTimeMillis() ) ); " );	
				}
			}
			this.getWriter().println( "		InsertHelper query = daoHelper.newInsertHelper( this.getTableName() );" );
			for ( DaogenCatalogField field : this.getCurrentEntity() ) {
				this.getWriter().println( "		query.addParam( "+columnConstantName( field.getId() )+", model.get"+GeneratorNameHelper.toClassName( field.getId() )+"() );" );
			}
			this.getWriter().println( "		int res = daoHelper.update( query );" );
			this.getWriter().println( "		this.evaluteSqlUpdateResult(res, model, result);" );
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();		
			// load by primary key
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+this.getEntityModelName()+" "+FacadeDefGenerator.METHOD_LOAD_BY_PK+"( "+this.getClassDaogenContext()+" context, "+primaryKeyHelper.setForLoadInterface().getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		"+this.getEntityModelName()+" result = null;" );
			this.getWriter().println( "		"+this.getClassDaoHelper()+"<"+this.getEntityModelName()+"> daoHelper = new "+this.getClassDaoHelper()+"<>( context );" );
			this.getWriter().println( "		SelectHelper query = daoHelper.newSelectHelper( this.getTableName() );" );
			for ( String currentField : primaryKeyHelper.getKeyFields() ) {
				this.getWriter().println( "		query.andEqualParam( COL_"+currentField.toUpperCase()+", "+GeneratorNameHelper.toPropertyName( currentField )+" );" );	
			}
			this.getWriter().println( "		result = daoHelper.loadOneHelper( query, this.getRse() );" );
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();	
			// delete by primary key
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+this.getEntityBaseResult()+" "+FacadeDefGenerator.METHOD_DELETE_BY_PK+"( "+this.getClassDaogenContext()+" context, "+primaryKeyHelper.setForLoadInterface().getKeyParams()+" ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		"+this.getEntityBaseResult()+" result = new "+this.getClassBaseResult()+"<>();" );
			this.getWriter().println( "		"+this.getClassDaoHelper()+"<"+this.getEntityModelName()+"> daoHelper = new "+this.getClassDaoHelper()+"<>( context );" );
			this.getWriter().println( "		DeleteHelper query = daoHelper.newDeleteHelper( this.getTableName() );" );
			for ( String currentField : primaryKeyHelper.getKeyFields() ) {
				this.getWriter().println( "		query.andWhereParam( COL_"+currentField.toUpperCase()+", "+GeneratorNameHelper.toPropertyName( currentField )+" );" );	
			}
			this.getWriter().println( "		int res = daoHelper.update( query );" );
			this.getWriter().println( "		this.evaluteSqlUpdateResult(res, null, result);" );		
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();	
			// update by primary key
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+this.getEntityBaseResult()+" "+FacadeDefGenerator.METHOD_UPDATE_BY_PK+"( "+this.getClassDaogenContext()+" context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		"+this.getEntityBaseResult()+" result = new "+this.getClassBaseResult()+"<>();" );
			this.getWriter().println( "		"+this.getClassDaoHelper()+"<"+this.getEntityModelName()+"> daoHelper = new "+this.getClassDaoHelper()+"<>( context );" );
			if ( autoSetDateUpdate != null ) {
				DaogenCatalogField colData = this.getCurrentEntity().get( autoSetDateUpdate );
				if ( colData != null ) {
					this.getWriter().println( "		//  "+DaogenCatalogConstants.GEN_PROP_DEFAULT_COLUMN_TIME_UPDATE+" : true - i will set update time" );	
					this.getWriter().println( "		model.set"+GeneratorNameHelper.toClassName( colData.getId() )+"( new java.sql.Timestamp( System.currentTimeMillis() ) ); " );	
				}
			}
			this.getWriter().println( "		UpdateHelper query = daoHelper.newUpdateHelper( this.getTableName() );" );
			for ( DaogenCatalogField field : this.getCurrentEntity() ) {
				if ( !primaryKeyHelper.getKeyFields().contains( field.getId() ) ) {
					this.getWriter().println( "		query.addSetParam( "+columnConstantName( field.getId() )+", model.get"+GeneratorNameHelper.toClassName( field.getId() )+"() );" );	
				}
			}
			for ( String currentField : primaryKeyHelper.getKeyFields() ) {
				this.getWriter().println( "		query.andWhereParam( COL_"+currentField.toUpperCase()+", model.get"+GeneratorNameHelper.toClassName( currentField )+"() );" );	
			}
			this.getWriter().println( "		int res = daoHelper.update( query );" );
			this.getWriter().println( "		this.evaluteSqlUpdateResult(res, model, result);" );
			this.getWriter().println( "		return result;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
		}
	}

}
