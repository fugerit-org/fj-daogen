package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenCustomCode;

public class FacadeDefGenerator extends DaogenBasicGenerator {

	public static final String KEY = "FacadeDefGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.facadeDefName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
		this.getImportList().add( "java.math.BigDecimal" );
		this.setClassDaogenContext( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_CONTEXT_BASE, this.getImportList() ) );
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		this.setClassBaseResult( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RESULT_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
	}

	private void generateKeySelect( GeneratorKeyHelper keyHelper, String keyName ) {
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Load method by "+keyName+" for entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	DAO Context" );	
		this.getWriter().println( keyHelper.getJavadocParams() );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return the item found or <code>null</code>." );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in case of errors" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getEntityModelName()+" loadById( "+this.getClassDaogenContext()+" context, "+keyHelper.getKeyParams()+" ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
	}

	@Override
	public void generateBody() throws Exception {
		this.getWriter().println( "	/*");
		this.getWriter().println( "	 * NOTE: It is advised to use a finder for incapsulating search params, except searches for primary key.");
		this.getWriter().println( "	 */");
		this.getWriter().println();
		if ( StringUtils.isNotEmpty( this.getCurrentEntity().getPrimaryKey() ) ) {
			GeneratorKeyHelper primaryKeyHelper = new GeneratorKeyHelper( this.getDaogenConfig() , this.getCurrentEntity(), this.getCurrentEntity().getPrimaryKey() );
			generateKeySelect( primaryKeyHelper, "primary key" );
		}
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
		this.getWriter().println( "	 * @return Il risultato della ricerca" );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in caso di errori" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAllByFinder( "+this.getClassDaogenContext()+" context, "+this.getEntityFinderName()+" finder ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		DaogenCustomCode.addCommentFacadeDef( "facade.def.create" , DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName() );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> create( "+this.getClassDaogenContext()+" context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		DaogenCustomCode.addCommentFacadeDef( "facade.def.updateById" , DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName() );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> updateById( "+this.getClassDaogenContext()+" context, "+this.getEntityModelName()+" model ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		DaogenCustomCode.addCommentFacadeDef( "facade.def.deleteById" , DaogenCustomCode.INDENT_1, this.getWriter(), this.getEntityModelName() );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> deleteById( "+this.getClassDaogenContext()+" context, BigDecimal id ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();		
	}

}
