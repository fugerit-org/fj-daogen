package org.fugerit.java.daogen.base.gen;

import java.util.Iterator;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class FactoryDataImplGenerator extends DaogenBasicGenerator {

	public static final String KEY = "FactoryDataImplGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DATA_IMPL ), 
				STYLE_CLASS, daogenConfig, null );
		String packageFacade = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF );
		String packageFacadeImpl = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DATA_IMPL );
		this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
		while ( itEntity.hasNext() ) {
			String currentId = itEntity.next();
			DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
			this.getImportList().add( packageFacade+"."+DaogenCatalogConstants.facadeDefName( current ) );
			this.getImportList().add( packageFacadeImpl+"."+DaogenCatalogConstants.facadeImplDataName( current ) );
		}
		this.setImplementsInterface( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) );
	}


	@Override
	public void generateBody() throws Exception {
		Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
		while ( itEntity.hasNext() ) {
			String currentId = itEntity.next();
			DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
			String facadeName = DaogenCatalogConstants.facadeDefName( current );
			String facadeNameImpl = DaogenCatalogConstants.facadeImplDataName( current );
			String propertyName = GeneratorNameHelper.toPropertyName( facadeName );
			this.getWriter().println( "	private "+facadeName+" "+propertyName+" = new "+facadeNameImpl+"();" );
			this.getWriter().println();
			this.getWriter().println( "	@Override" );
			this.getWriter().println( "	public "+facadeName+" get"+facadeName+"() throws "+this.getClassDaoException()+" {" );
			this.getWriter().println( "		return this."+propertyName+";" );
			this.getWriter().println( "	}" );
			this.getWriter().println();
		}
	}

}
