package org.fugerit.java.daogen.base.gen;

import java.util.Iterator;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;

public class FactoryDefGenerator extends DaogenBasicHelperGenerator {

	public static final String KEY = FactoryDefGenerator.class.getSimpleName();
	
	@Override
	public String getKey() {
		return KEY;
	}

	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		// init
		if ( this.isModeHelper() ) {
			super.init( DaogenHelperGenerator.toHelperSourceFolder( daogenConfig ),
					DaogenHelperGenerator.toHelperClassName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ) ), 
					STYLE_INTERFACE, daogenConfig, null );
		} else {
			super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
					daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_FACTORY_DEF ), 
					STYLE_INTERFACE, daogenConfig, null );			
		}
		// config
		if ( this.isModeReal() ) {
			this.configRealClass();
		} else {
			String packageFacade = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF );
			this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
			Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				String facadeName = DaogenCatalogConstants.facadeDefName( current );
				this.getImportList().add( packageFacade+"."+facadeName );	
			}
		}
	}

	@Override
	public void generateDaogenBody() throws Exception {
		if ( this.isModeReal() ) {
			this.generateRealClass();
		} else {
			this.getWriter().println( "	public static final String ATT_NAME = \""+this.getJavaName()+"\";" );
			this.getWriter().println();
			Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				String facadeName = DaogenCatalogConstants.facadeDefName( current );
				this.getWriter().println( "	/**" );
				this.getWriter().println( "	 * Facade incapsulating persistance for entity : "+current.getName() );
				this.getWriter().println( "	 *" );
				this.getWriter().println( "	 * @return	the facade" );
				this.getWriter().println( "	 * @throws "+this.getClassDaoException()+"	in case of problems" );
				this.getWriter().println( "	 */" );
				this.getWriter().println( "	"+facadeName+" get"+facadeName+"() throws "+this.getClassDaoException()+";" );
				this.getWriter().println();
			}
		}
	}

}
