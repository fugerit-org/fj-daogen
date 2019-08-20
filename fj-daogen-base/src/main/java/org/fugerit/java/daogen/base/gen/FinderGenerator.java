package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class FinderGenerator extends DaogenBasicGenerator {

	public static final String KEY = "FinderGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.finderlName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
		this.setJavaStyle( STYLE_CLASS );
		this.setClassBaseFinder( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_BASEFINDER_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.setExtendsClass( this.getClassBaseFinder() );
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		this.getWriter().println( "	private "+this.getEntityModelName()+" model;" );
		this.getWriter().println();
		this.getWriter().println( "	public void setModel( "+this.getEntityModelName()+" model ) {"  );
		this.getWriter().println( "		this.model = model;"  );
		this.getWriter().println( "	}"  );
		this.getWriter().println();
		this.getWriter().println( "	public "+this.getEntityModelName()+" getModel() {"  );
		this.getWriter().println( "		return this.model;"  );
		this.getWriter().println( "	}"  );
		this.getWriter().println();
		if ( this.getCurrentEntity().containsDefaultId() ) {
			this.getWriter().println( "	/**" );
			this.getWriter().println( "	 *Factory method to create a new finder " );
			this.getWriter().println( "	 *" );	
			this.getWriter().println( "	 * @param id		id to wrap in the finder" );	
			this.getWriter().println( "	 *" );	
			this.getWriter().println( "	 * @return	the finder" );	
			this.getWriter().println( "	 */" );
			this.getWriter().println( "	public static "+this.getEntityFinderName()+" newInstance( java.math.BigDecimal id ) { " );
			this.getWriter().println( "		"+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
			this.getWriter().println( "		finder.setId( id );" );
			this.getWriter().println( "		return finder;" );
			this.getWriter().println( "	}" );
			this.getWriter().println();		
		}
		this.getWriter().println( "	/**" );
		this.getWriter().println( "	 * Factory method to create a new finder " );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @param model		the model to wrap in the finder" );
		this.getWriter().println( "	 *" );	
		this.getWriter().println( "	 * @return	the finder" );	
		this.getWriter().println( "	 */" );
		this.getWriter().println( "	public static "+this.getEntityFinderName()+" newInstance( "+this.getEntityModelName()+" model ) { " );
		this.getWriter().println( "		"+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
		if ( this.getCurrentEntity().containsDefaultId() ) {
			this.getWriter().println( "		finder.setId( model.getId() );" );	
		} else {
			this.getWriter().println( "		// default id not available for this entity" );
		}
		this.getWriter().println( "		finder.setModel( model );" );
		this.getWriter().println( "		return finder;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();			
	}

}
