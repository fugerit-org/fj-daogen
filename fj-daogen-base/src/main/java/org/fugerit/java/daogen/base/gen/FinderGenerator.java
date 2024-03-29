package org.fugerit.java.daogen.base.gen;

import java.io.IOException;
import java.math.BigDecimal;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.StringUtils;
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
		String daoFinderNgMode = daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE, DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_DISABLED );
		logger.info( "{} -> {}", DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE, daoFinderNgMode );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		if ( DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_DISABLED.equalsIgnoreCase( daoFinderNgMode ) ) {
			DaogenCatalogField idField = entity.get( DaogenCatalogEntity.DEFAULT_ID_FIELD );
			if ( idField == null || this.getDaogenConfig().getTypeMapper().mapForModel( idField ).equalsIgnoreCase( BigDecimal.class.getName() ) ) {
				this.setClassBaseFinder( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_BASEFINDER_BASE, this.getImportList() ) );
				this.setExtendsClass( this.getClassBaseFinder() );
			} else {
				String type = this.getDaogenConfig().getTypeMapper().mapForModel( idField );
				this.setClassBaseFinder( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_GENERICFINDER_BASE, this.getImportList() ) );
				this.setExtendsClass( this.getClassBaseFinder()+"<"+type+">" );
			}
		} else if ( DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_ENABLED.equalsIgnoreCase( daoFinderNgMode ) ) {
			String finderNgClass = DaogenClassConfigHelper.findClassConfigProp( daogenConfig, DaogenClassConfigHelper.DAO_FINDER_NG_BASE, DaogenClassConfigHelper.DAO_BASE_CLASS );
			if ( StringUtils.isNotEmpty( finderNgClass ) ) {
				this.setClassBaseHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_FINDER_NG_BASE, this.getImportList() ) );
				this.setExtendsClass( this.getClassBaseHelper() );
			}
		} else {
			throw new ConfigException( "Invalid "+DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE+" parameter : "+daoFinderNgMode );
		}
	}

	@Override
	public void generateDaogenBody() throws IOException {
		String daoFinderNgMode = this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE, DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_DISABLED );
		this.generateSerial( DaogenCatalogConstants.GEN_PROP_DAO_FINDER_NG_MODE_DISABLED.equalsIgnoreCase( daoFinderNgMode ) );
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
			this.getWriter().println( TAB+" * @param id"+TAB_2+"id to wrap in the finder" );	
			this.getWriter().println( TAB+" *" );	
			this.getWriter().println( TAB+" * @return"+TAB+"the finder" );	
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
		this.getWriter().println( TAB+" * @param model"+TAB_2+"the model to wrap in the finder" );
		this.getWriter().println( TAB+" *" );	
		this.getWriter().println( TAB+" * @return"+TAB+"the finder" );	
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
