package org.fugerit.java.daogen.base.gen.helper;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.fugerit.java.daogen.base.gen.GeneratorKeyHelper;

public class FacadeDefHelperGenerator extends DaogenBasicGenerator {

	public static final String KEY = FacadeDefHelperGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public static final String PRIMARY_KEY = "primary key";
	public static final String METHOD_LOAD_BY_PK = "loadById";
	public static final String METHOD_DELETE_BY_PK = "deleteById";
	public static final String METHOD_UPDATE_BY_PK = "updateById";
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ),
				DaogenHelperGenerator.toHelperClassName( fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.facadeDefName( entity ) ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
		this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
	}

	private void methodByKey( GeneratorKeyHelper keyHelper, String keyName, String methodName, String returnType, String returnJavadoc, String comment ) {
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * "+comment );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	DAO Context" );	
		this.getWriter().println( keyHelper.getJavadocParams() );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return "+returnJavadoc );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in case of errors" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+returnType+" "+methodName+"( "+this.getClassDaogenContext()+" context, "+keyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
	}
	
	@Override
	public void generateBody() throws Exception {
		this.getWriter().println( "	/*");
		this.getWriter().println( "	 * NOTE: It is advised to use a finder for incapsulating search params, except searches for primary key.");
		this.getWriter().println( "	 */");
		this.getWriter().println();
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Method to load all the items for entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	DAOContext" );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return search result" );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in case of errors" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAll( "+this.getClassDaogenContext()+" context ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Method to load all the items for entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	DAOContext" );	
		this.getWriter().println( "	 * @param finder	the finder incapsulating search params" );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return search result" );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in caso di errori" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAllByFinder( "+this.getClassDaogenContext()+" context, "+this.getEntityFinderName()+" finder ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() );
			methodByKey( primaryKeyHelper.setForLoadInterface(), PRIMARY_KEY, METHOD_LOAD_BY_PK, this.getEntityModelName(), "The found object or <code>null</code>", "Load method by "+PRIMARY_KEY+" for entity : "+this.getEntityModelName() );
			DaogenCustomCode.addCommentFacadeDef( "facade.def.create" , DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName() );
			this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> create( "+this.getClassDaogenContext()+" context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+";" );
			this.getWriter().println();
			methodByKey( primaryKeyHelper.setForLoadInterface(), PRIMARY_KEY, METHOD_DELETE_BY_PK, this.getEntityBaseResult(), "Delete result (resultCode=0, delete ok)", "Delete method by "+PRIMARY_KEY+" for entity : "+this.getEntityModelName() );
			methodByKey( primaryKeyHelper.setForUpdateInterface(), PRIMARY_KEY, METHOD_UPDATE_BY_PK, this.getEntityBaseResult(), "Update result (resultCode=0, update ok)", "Delete method by "+PRIMARY_KEY+" for entity : "+this.getEntityModelName() );
		}
	}

}
