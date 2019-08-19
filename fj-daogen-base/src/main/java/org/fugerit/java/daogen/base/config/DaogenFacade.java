package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.FactoryType;
import org.fugerit.java.core.javagen.JavaGenerator;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaogenFacade {

	private DaogenFacade() {}
	
	private static Logger logger = LoggerFactory.getLogger( DaogenFacade.class );
	
	private static void generageFile( JavaGenerator gen ) throws Exception {
		logger.info( "Generating : "+gen );
		gen.generate();
		gen.write();
	}
	
	public static void generate( InputStream fis ) throws ConfigException {
		try {
			DaogenCatalogConfig daogenConfig = DaogenCatalogConfig.loadConfig( fis );
			List<String> entityIdList = new ArrayList<String>( daogenConfig.getIdSet() );
			Collections.sort( entityIdList );
			DaogenGeneratorCatalog generatorCatalog = daogenConfig.getGeneratorCatalog();
			for ( String entityId : entityIdList ) {
				DaogenCatalogEntity entity = daogenConfig.getListMap( entityId );
				for ( FactoryType dataType : generatorCatalog.getEntityGenerators() ) {
					if ( daogenConfig.getGeneralProps().containsKey( dataType.getInfo() ) ) {
						DaogenBasicGenerator generator = (DaogenBasicGenerator)(ClassHelper.newInstance( dataType.getType()));
						generator.init( daogenConfig, entity );	
						generageFile( generator );	
					}
				}
			}
			for ( FactoryType dataType : generatorCatalog.getFactoryGenerators() ) {
				if ( daogenConfig.getGeneralProps().containsKey( dataType.getInfo() ) ) {
					DaogenBasicGenerator generator = (DaogenBasicGenerator)(ClassHelper.newInstance( dataType.getType()));
					generator.init( daogenConfig, null );	
					generageFile( generator );	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConfigException( "Error during DAO generation", e );
		}
	}
	
}
