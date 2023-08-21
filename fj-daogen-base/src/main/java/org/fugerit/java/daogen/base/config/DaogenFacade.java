package org.fugerit.java.daogen.base.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.xml.FactoryType;
import org.fugerit.java.core.javagen.JavaGenerator;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.daogen.base.gen.DaogenBasicDecorator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaogenFacade {

	private DaogenFacade() {}
	
	private static void generageFile( JavaGenerator gen ) throws Exception {
		log.info( "Generating : {}", gen );
		gen.generate();
		gen.write();
	}
	
	private static void generate( DaogenCatalogConfig daogenConfig, DaogenGeneratorCatalog generatorCatalog ) throws Exception {
		List<String> entityIdList = new ArrayList<String>( daogenConfig.getIdSet() );
		Collections.sort( entityIdList );
		// iterating over entity to generate
		for ( String entityId : entityIdList ) {
			DaogenCatalogEntity entity = daogenConfig.getListMap( entityId );
			log.info( "Describe : {} -> {}", entity.getId(), entity.describe() );
			if ( generatorCatalog.getEntityGenerators( daogenConfig ) != null ) {
				// iterating over generators
				for ( FactoryType dataType : generatorCatalog.getEntityGenerators( daogenConfig ) ) {
					if ( daogenConfig.getGeneralProps().containsKey( dataType.getInfo() ) ) {
						DaogenBasicGenerator generator = (DaogenBasicGenerator)(ClassHelper.newInstance( dataType.getType()));
						if ( generator.isGenerate( daogenConfig, entity ) ) {
							Collection<FactoryType> decorators = daogenConfig.getDecoratorCatalog().getDataList( dataType.getId() );
							if ( decorators != null ) {
								// iterationg over decorators
								for ( FactoryType decoratorType : decorators ) {
									DaogenBasicDecorator decorator = (DaogenBasicDecorator)ClassHelper.newInstance( decoratorType.getType() );
									decorator.init( generator );
								}
							}
							generator.init( daogenConfig, entity );	
							// actual generations
							generageFile( generator );	
						}
					}
				}
			}
		}
		// iterating over factory generators
		if ( generatorCatalog.getFactoryGenerators( daogenConfig ) != null ) {
			for ( FactoryType dataType : generatorCatalog.getFactoryGenerators( daogenConfig ) ) {
				if ( daogenConfig.getGeneralProps().containsKey( dataType.getInfo() ) ) {
					DaogenBasicGenerator generator = (DaogenBasicGenerator)(ClassHelper.newInstance( dataType.getType()));
					generator.init( daogenConfig, null );	
					generageFile( generator );	
				}
			}
		}
	}
	
	
	public static void generate( InputStream fis ) throws ConfigException {
		try {
			DaogenCatalogConfig daogenConfig = DaogenCatalogConfig.loadConfig( fis );
			for ( DaogenGeneratorCatalog generatorCatalog : daogenConfig.getGeneratorCatalogs() ) {
				generate(daogenConfig, generatorCatalog);
			}
		} catch (Exception e) {
			throw ConfigException.convertEx( "Error during DAO generation", e );
		}
	}
	
}
