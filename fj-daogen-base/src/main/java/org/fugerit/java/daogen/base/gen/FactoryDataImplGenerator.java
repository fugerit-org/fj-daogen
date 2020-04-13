package org.fugerit.java.daogen.base.gen;

import java.util.Iterator;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;

public class FactoryDataImplGenerator extends DaogenBasicHelperGenerator {

	public static final String KEY = FactoryDataImplGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		// init
		if ( this.isModeHelper() ) {
			super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ),
					DaogenHelperGenerator.toHelperClassName(daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DATA_IMPL ) ), 
					STYLE_CLASS, daogenConfig, null );
		} else {
			super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
					daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DATA_IMPL ), 
					STYLE_CLASS, daogenConfig, null );		
		}
		// config
		String baseName = this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF );
		if ( this.isModeReal() ) {
			this.configRealClass();
		} else {
			String packageFacade = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF );
			
			this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
			Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				this.getImportList().add( packageFacade+"."+DaogenCatalogConstants.facadeDefName( current ) );
			}
			if ( this.isModeHelper() ) {
				baseName = DaogenHelperGenerator.toHelperClassName( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) );
			}
		}	
		this.setImplementsInterface( baseName+", java.io.Serializable" );
	}


	@Override
	public void generateDaogenBody() throws Exception {
		if ( this.isModeReal() ) {
			this.generateRealClass();
		} else {
			this.addSerialVerUID();
			this.getWriter().println();
			this.getWriter().println( "	public "+this.getJavaName()+"() {");
			Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
			String packageFacadeImpl = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DATA_IMPL );
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				String facadeName = DaogenCatalogConstants.facadeDefName( current );
				String facadeNameImpl = packageFacadeImpl+"."+DaogenCatalogConstants.facadeImplDataName( current );
				String propertyName = GeneratorNameHelper.toPropertyName( facadeName );
				this.getWriter().println( "		this."+propertyName+" = new "+facadeNameImpl+"();" ) ;
			}
			this.getWriter().println( "	}");
			this.getWriter().println();
			itEntity = this.getDaogenConfig().getIdSet().iterator();
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				String facadeName = DaogenCatalogConstants.facadeDefName( current );
				String propertyName = GeneratorNameHelper.toPropertyName( facadeName );
				this.getWriter().println( "	private "+facadeName+" "+propertyName+";" );
				this.getWriter().println();
				this.getWriter().println( "	@Override" );
				this.getWriter().println( "	public "+facadeName+" get"+facadeName+"() throws "+this.getClassDaoException()+" {" );
				this.getWriter().println( "		return this."+propertyName+";" );
				this.getWriter().println( "	}" );
				this.getWriter().println();
				this.getWriter().println( "	protected void set"+facadeName+"( "+facadeName+" facade ) throws "+this.getClassDaoException()+" {" );
				this.getWriter().println( "		this."+propertyName+" = facade;" );
				this.getWriter().println( "	}" );
				this.getWriter().println();			
			}
		}
	}

}
