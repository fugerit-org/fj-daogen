package org.fugerit.java.daogen.base.gen;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

public class FacadeDefGenerator extends DaogenBasicHelperGenerator {

	public static final String KEY = FacadeDefGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public static final String PRIMARY_KEY = "primary key";
	public static final String METHOD_LOAD_BY_PK = "loadById";
	public static final String METHOD_DELETE_BY_PK = "deleteById";
	public static final String METHOD_UPDATE_BY_PK = "updateById";

	private static final String FOR_ENTITY_LIT = " for entity : ";
	
	private static final String PARAM_CONTEXT_LIT = " * @param context";
	
	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		// init
		if ( this.isModeHelper() ) {
			super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ),
					DaogenHelperGenerator.toHelperClassName( fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.facadeDefName( entity ) ) ), 
					STYLE_INTERFACE, daogenConfig, entity );
		} else {
			super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
					fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.facadeDefName( entity ) ), 
					STYLE_INTERFACE, daogenConfig, entity );	
		}
		// config
		if ( this.isModeReal() ) {
			this.configRealClass();
		} else {
			this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
			this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
			this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
			this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
			this.getImportList().add( Stream.class.getName() );
			if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
				this.getImportList().add( Optional.class.getName() );
			}
		}
	}

	private void methodByKey( GeneratorKeyHelper keyHelper, String methodName, String returnType, String returnJavadoc, String comment ) {
		this.getWriter().println( TAB+"/**" );
		this.getWriter().println( TAB+" * "+comment );
		this.getWriter().println( TAB+" *" );	
		this.getWriter().println( TAB+PARAM_CONTEXT_LIT+TAB+"DAO Context" );	
		this.getWriter().println( keyHelper.getJavadocParams() );	
		this.getWriter().println( TAB+" *" );
		this.getWriter().println( TAB+" * @return "+returnJavadoc );	
		this.getWriter().println( TAB+JD_THROWS+this.getClassDaoException()+TAB_3+IN_CASE_OF_ERRORS_LIT );	
		this.getWriter().println( TAB+" */" );
		this.getWriter().println( TAB+""+returnType+" "+methodName+"( "+this.getClassDaogenContext()+CONTEXT_LIT+keyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
	}
	
	private void loadAllCommentHelper( boolean stream ) {
		String addOn = "";
		if ( stream ) {
			addOn = " (as a stream)";
		}
		this.getWriter().println( TAB+"/**" );
		this.getWriter().println( TAB+" * Method to load all the items for entity : "+this.getEntityModelName()+addOn );
		this.getWriter().println( TAB+" *" );	
		this.getWriter().println( TAB+PARAM_CONTEXT_LIT+TAB+"DAOContext" );	
		this.getWriter().println( TAB+" * @param finder"+TAB+"the finder encapsulating search params" );	
		this.getWriter().println( TAB+" *" );	
		if ( stream ) {
			this.getWriter().println( TAB+" * @return stream on the result" );	
		} else {
			this.getWriter().println( TAB+" * @return search result" );	
		}
		this.getWriter().println( TAB+JD_THROWS+this.getClassDaoException()+TAB_3+IN_CASE_OF_ERRORS_LIT );	
		this.getWriter().println( TAB+" */" );
	}
	
	@Override
	public void generateDaogenBody() throws IOException {
		if ( this.isModeReal() ) {
			this.generateRealClass();
		} else {
			this.getWriter().println( TAB+"/*");
			this.getWriter().println( TAB+" * NOTE: It is advised to use a finder for encapsulating search params, except searches for primary key.");
			this.getWriter().println( TAB+" */");
			this.getWriter().println();
			this.getWriter().println( TAB+"/**" );
			this.getWriter().println( TAB+" * Method to load all the items for entity : "+this.getEntityModelName() );
			this.getWriter().println( TAB+" *" );	
			this.getWriter().println( TAB+PARAM_CONTEXT_LIT+TAB+"DAOContext" );	
			this.getWriter().println( TAB+" *" );	
			this.getWriter().println( TAB+" * @return search result" );	
			this.getWriter().println( TAB+JD_THROWS+this.getClassDaoException()+TAB_3+IN_CASE_OF_ERRORS_LIT );	
			this.getWriter().println( TAB+" */" );
			this.getWriter().println( TAB+""+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAll( "+this.getClassDaogenContext()+" context ) throws "+this.getClassDaoException()+";" );
			this.getWriter().println();
			this.loadAllCommentHelper( false );
			this.getWriter().println( TAB+""+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAllByFinder( "+this.getClassDaogenContext()+CONTEXT_LIT+this.getEntityFinderName()+" finder ) throws "+this.getClassDaoException()+";" );
			this.getWriter().println();
			this.loadAllCommentHelper( true );
			this.getWriter().println( TAB+""+Stream.class.getSimpleName()+"<"+this.getEntityModelName()+"> loadAllByFinderStream( "+this.getClassDaogenContext()+CONTEXT_LIT+this.getEntityFinderName()+" finder ) throws "+this.getClassDaoException()+";" );
			this.getWriter().println();
			if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
				GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() );
				// load by id
				methodByKey( primaryKeyHelper.setForLoadInterface(), METHOD_LOAD_BY_PK, this.getEntityModelName(), "The found object or <code>null</code>", "Load method by "+PRIMARY_KEY+FOR_ENTITY_LIT+this.getEntityModelName() );
				// load by id optional
				methodByKey( primaryKeyHelper.setForLoadInterface(), METHOD_LOAD_BY_PK+Optional.class.getSimpleName(), Optional.class.getSimpleName()+"<"+this.getEntityModelName()+">", "The found object or <code>null</code>", "Load method by "+PRIMARY_KEY+FOR_ENTITY_LIT+this.getEntityModelName() );
				if ( FacadeGeneratorUtils.isFacadeModeInsert( this.getCurrentEntity() ) ) {
					DaogenCustomCode.addCommentFacadeDef( "facade.def.create" , DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName() );	
					this.getWriter().println( TAB+""+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> create( "+this.getClassDaogenContext()+CONTEXT_LIT+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+";" );
					this.getWriter().println();
				}
				if ( FacadeGeneratorUtils.isFacadeModeDelete( this.getCurrentEntity() ) ) {
					methodByKey( primaryKeyHelper.setForLoadInterface(), METHOD_DELETE_BY_PK, this.getEntityBaseResult(), "Delete result (resultCode=0, delete ok)", "Delete method by "+PRIMARY_KEY+FOR_ENTITY_LIT+this.getEntityModelName() );	
				}
				if ( FacadeGeneratorUtils.isFacadeModeUpdate( this.getCurrentEntity() ) ) {
					methodByKey( primaryKeyHelper.setForUpdateInterface(), METHOD_UPDATE_BY_PK, this.getEntityBaseResult(), "Update result (resultCode=0, update ok)", "Delete method by "+PRIMARY_KEY+FOR_ENTITY_LIT+this.getEntityModelName() );	
				}
			}
		}
	}

}
