package org.fugerit.java.daogen.base.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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
	
	private static void generageFile( JavaGenerator gen ) throws IOException {
		log.debug( "Generating : {} START", gen );
		gen.generate();
		gen.write();
		log.debug( "Generating : {} END", gen );
	}
	
	private static void handleCurrentGenerator( DaogenBasicGenerator generator, DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity, FactoryType dataType ) throws ConfigException, IOException {
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
	
	private static void handleGenerators( DaogenCatalogConfig daogenConfig, DaogenGeneratorCatalog generatorCatalog, DaogenCatalogEntity entity ) throws ConfigException, IOException {
		if ( generatorCatalog.getEntityGenerators( daogenConfig ) != null ) {
			// iterating over generators
			for ( FactoryType dataType : generatorCatalog.getEntityGenerators( daogenConfig ) ) {
				log.info( "generator : {} -> {}", dataType.getInfo(), dataType.getType() );
				if ( daogenConfig.getGeneralProps().containsKey( dataType.getInfo() ) ) {
					handleCurrentGenerator((DaogenBasicGenerator)(ClassHelper.newInstance( dataType.getType())), daogenConfig, entity, dataType);
				}
			}
		}
	}
	
	private static void generate( DaogenCatalogConfig daogenConfig, DaogenGeneratorCatalog generatorCatalog ) throws ConfigException, IOException {
		List<String> entityIdList = new ArrayList<String>( daogenConfig.getIdSet() );
		Collections.sort( entityIdList );
		// iterating over entity to generate
		for ( String entityId : entityIdList ) {
			DaogenCatalogEntity entity = daogenConfig.getListMap( entityId );
			log.info( "Describe : {} -> {}", entity.getId(), entity.describe() );
			handleGenerators(daogenConfig, generatorCatalog, entity);
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
	
	public static void generate( InputStream fis, Properties overrideProperties ) throws ConfigException {
		try {
			DaogenCatalogConfig daogenConfig = DaogenCatalogConfig.loadConfig( fis, overrideProperties );
			for ( DaogenGeneratorCatalog generatorCatalog : daogenConfig.getGeneratorCatalogs() ) {
				generate(daogenConfig, generatorCatalog);
			}
		} catch (IOException e) {
			throw ConfigException.convertEx( "Error during DAO generation", e );
		}
	}
	
	public static void generate( InputStream fis ) throws ConfigException {
		generate(fis, null);
	}
	
}
