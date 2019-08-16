package org.fugerit.java.daogen.base.tool;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.JavaGenerator;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.gen.ModelGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoGenFacade {

	private DaoGenFacade() {}
	
	private static Logger logger = LoggerFactory.getLogger( DaoGenFacade.class );
	
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
			for ( String entityId : entityIdList ) {
				DaogenCatalogEntity entity = daogenConfig.getListMap( entityId );
				generageFile( new ModelGenerator( daogenConfig, entity ) );
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConfigException( "Error during DAO generation", e );
		}
	}
	
}
