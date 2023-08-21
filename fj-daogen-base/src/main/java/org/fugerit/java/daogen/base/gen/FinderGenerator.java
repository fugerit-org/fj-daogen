package org.fugerit.java.daogen.base.gen;

import java.math.BigDecimal;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

public class FinderGenerator extends DaogenBasicGenerator {

	public static final String KEY = "FinderGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF ), DaogenCatalogConstants.finderlName( entity ) ), 
				STYLE_INTERFACE, daogenConfig, entity );
		this.setJavaStyle( STYLE_CLASS );
		DaogenCatalogField idField = entity.get( DaogenCatalogEntity.DEFAULT_ID_FIELD );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		if ( idField == null || this.getDaogenConfig().getTypeMapper().mapForModel( idField ).equalsIgnoreCase( BigDecimal.class.getName() ) ) {
			this.setClassBaseFinder( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_BASEFINDER_BASE, this.getImportList() ) );	
			this.setExtendsClass( this.getClassBaseFinder() );
		} else {
			String type = this.getDaogenConfig().getTypeMapper().mapForModel( idField );
			this.setClassBaseFinder( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_GENERICFINDER_BASE, this.getImportList() ) );
			this.setExtendsClass( this.getClassBaseFinder()+"<"+type+">" );
		}
	}

	@Override
	public void generateDaogenBody() throws Exception {
		this.addSerialVerUID();
		this.getWriter().println( TAB+"private "+this.getEntityModelName()+" model;" );
		this.getWriter().println();
		this.getWriter().println( TAB+"public void setModel( "+this.getEntityModelName()+" model ) {"  );
		this.getWriter().println( TAB_2+"this.model = model;"  );
		this.getWriter().println( TAB+"}"  );
		this.getWriter().println();
		this.getWriter().println( TAB+"public "+this.getEntityModelName()+" getModel() {"  );
		this.getWriter().println( TAB_2+"return this.model;"  );
		this.getWriter().println( TAB+"}"  );
		this.getWriter().println();
		if ( this.getCurrentEntity().containsDefaultId() ) {
			DaogenCatalogField idField = this.getCurrentEntity().get( DaogenCatalogEntity.DEFAULT_ID_FIELD );
			String type = this.getDaogenConfig().getTypeMapper().mapForModel( idField );
			this.getWriter().println( TAB+"/**" );
			this.getWriter().println( TAB+" *Factory method to create a new finder " );
			this.getWriter().println( TAB+" *" );	
			this.getWriter().println( TAB+" * @param id		id to wrap in the finder" );	
			this.getWriter().println( TAB+" *" );	
			this.getWriter().println( TAB+" * @return	the finder" );	
			this.getWriter().println( TAB+" */" );
			this.getWriter().println( TAB+"public static "+this.getEntityFinderName()+" newInstance( "+type+" id ) { " );
			this.getWriter().println( TAB_2+""+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
			this.getWriter().println( TAB_2+"finder.setId( id );" );
			this.getWriter().println( TAB_2+"return finder;" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();		
		}
		this.getWriter().println( TAB+"/**" );
		this.getWriter().println( TAB+" * Factory method to create a new finder " );
		this.getWriter().println( TAB+" *" );	
		this.getWriter().println( TAB+" * @param model		the model to wrap in the finder" );
		this.getWriter().println( TAB+" *" );	
		this.getWriter().println( TAB+" * @return	the finder" );	
		this.getWriter().println( TAB+" */" );
		this.getWriter().println( TAB+"public static "+this.getEntityFinderName()+" newInstance( "+this.getEntityModelName()+" model ) { " );
		this.getWriter().println( TAB_2+""+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
		if ( this.getCurrentEntity().containsDefaultId() ) {
			this.getWriter().println( TAB_2+"finder.setId( model.getId() );" );	
		} else {
			this.getWriter().println( TAB_2+"// default id not available for this entity" );
		}
		this.getWriter().println( TAB_2+"finder.setModel( model );" );
		this.getWriter().println( TAB_2+"return finder;" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();			
	}

}
