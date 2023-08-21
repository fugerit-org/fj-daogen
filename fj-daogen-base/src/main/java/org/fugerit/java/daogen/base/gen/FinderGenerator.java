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
		this.getWriter().println( "\tprivate "+this.getEntityModelName()+" model;" );
		this.getWriter().println();
		this.getWriter().println( "\tpublic void setModel( "+this.getEntityModelName()+" model ) {"  );
		this.getWriter().println( "\t\tthis.model = model;"  );
		this.getWriter().println( "\t}"  );
		this.getWriter().println();
		this.getWriter().println( "\tpublic "+this.getEntityModelName()+" getModel() {"  );
		this.getWriter().println( "\t\treturn this.model;"  );
		this.getWriter().println( "\t}"  );
		this.getWriter().println();
		if ( this.getCurrentEntity().containsDefaultId() ) {
			DaogenCatalogField idField = this.getCurrentEntity().get( DaogenCatalogEntity.DEFAULT_ID_FIELD );
			String type = this.getDaogenConfig().getTypeMapper().mapForModel( idField );
			this.getWriter().println( "\t/**" );
			this.getWriter().println( "\t *Factory method to create a new finder " );
			this.getWriter().println( "\t *" );	
			this.getWriter().println( "\t * @param id		id to wrap in the finder" );	
			this.getWriter().println( "\t *" );	
			this.getWriter().println( "\t * @return	the finder" );	
			this.getWriter().println( "\t */" );
			this.getWriter().println( "\tpublic static "+this.getEntityFinderName()+" newInstance( "+type+" id ) { " );
			this.getWriter().println( "\t\t"+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
			this.getWriter().println( "\t\tfinder.setId( id );" );
			this.getWriter().println( "\t\treturn finder;" );
			this.getWriter().println( "\t}" );
			this.getWriter().println();		
		}
		this.getWriter().println( "\t/**" );
		this.getWriter().println( "\t * Factory method to create a new finder " );
		this.getWriter().println( "\t *" );	
		this.getWriter().println( "\t * @param model		the model to wrap in the finder" );
		this.getWriter().println( "\t *" );	
		this.getWriter().println( "\t * @return	the finder" );	
		this.getWriter().println( "\t */" );
		this.getWriter().println( "\tpublic static "+this.getEntityFinderName()+" newInstance( "+this.getEntityModelName()+" model ) { " );
		this.getWriter().println( "\t\t"+this.getEntityFinderName()+" finder = new "+this.getEntityFinderName()+"();" );
		if ( this.getCurrentEntity().containsDefaultId() ) {
			this.getWriter().println( "\t\tfinder.setId( model.getId() );" );	
		} else {
			this.getWriter().println( "\t\t// default id not available for this entity" );
		}
		this.getWriter().println( "\t\tfinder.setModel( model );" );
		this.getWriter().println( "\t\treturn finder;" );
		this.getWriter().println( "\t}" );
		this.getWriter().println();			
	}

}
