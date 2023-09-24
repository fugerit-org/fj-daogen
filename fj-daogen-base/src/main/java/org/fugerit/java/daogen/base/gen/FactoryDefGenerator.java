package org.fugerit.java.daogen.base.gen;

import java.io.IOException;
import java.util.Iterator;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

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
			this.setClassDaoException( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_EXCEPTION_BASE, this.getImportList() ) );
		}
	}

	@Override
	public void generateDaogenBody() throws IOException {
		if ( this.isModeReal() ) {
			this.generateRealClass();
		} else {
			this.getWriter().println( TAB+"public static final String ATT_NAME = \""+this.getJavaName()+"\";" );
			this.getWriter().println();
			Iterator<String> itEntity = this.getDaogenConfig().getIdSet().iterator();
			while ( itEntity.hasNext() ) {
				String currentId = itEntity.next();
				DaogenCatalogEntity current = this.getDaogenConfig().getListMap( currentId );
				if ( FacadeGeneratorUtils.isFacadeGenerate( current ) ) {
					String facadeName = DaogenCatalogConstants.facadeDefName( current );
					String packageFacade = this.getDaogenConfig().getGeneralProp(DaogenCatalogConstants.GEN_PROP_PACKAGE_FACADE_DEF );
					this.getWriter().println( TAB+"/**" );
					this.getWriter().println( TAB+" * Facade encapsulating persistance for entity : "+current.getName() );
					this.getWriter().println( TAB+" *" );
					this.getWriter().println( TAB+" * @return"+TAB+"the facade" );
					this.getWriter().println( TAB+" * @throws "+this.getClassDaoException()+TAB+"in case of problems" );
					this.getWriter().println( TAB+" */" );
					this.getWriter().println( TAB+""+packageFacade+"."+facadeName+" get"+facadeName+"() throws "+this.getClassDaoException()+";" );
					this.getWriter().println();	
				}
			}
		}
	}

}
