package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
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


	@Override
	public void generateBody() throws Exception {
		this.getWriter().println( "	/*");
		this.getWriter().println( "	 * NOTA: Eccetto la ricerca per id, e' preferibile usare dei finder per incapsulare i parametri di ricerca.");
		this.getWriter().println( "	 */");
		this.getWriter().println();
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Metodo di caricamento per id della entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	il contesto dell'operazione" );	
		this.getWriter().println( "	 * @param id		id dell'oggetto da cercare" );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return l'oggetto trovato o <code>null</code>." );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in caso di errori" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getEntityModelName()+" loadById( "+this.getClassDaogenContext()+" context, BigDecimal id ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Metodo di caricamento per tutte le entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	il contesto dell'operazione" );	
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return Il risultato della ricerca" );	
		this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"			in caso di errori" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	"+this.getClassBaseResult()+"<"+this.getEntityModelName()+"> loadAll( "+this.getClassDaogenContext()+" context ) throws "+this.getClassDaoException()+";" );
		this.getWriter().println();
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Metodo di caricamento per tutte le entity : "+this.getEntityModelName() );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param context	il contesto dell'operazione" );	
		this.getWriter().println( "	 * @param finder	il finder" );	
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
