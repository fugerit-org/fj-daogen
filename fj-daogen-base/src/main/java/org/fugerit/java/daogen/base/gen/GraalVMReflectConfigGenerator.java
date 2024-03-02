package org.fugerit.java.daogen.base.gen;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.*;

import org.fugerit.java.nhg.GenerateReflectConfig;
import org.fugerit.java.nhg.reflect.config.Entry;
import org.fugerit.java.nhg.reflect.config.EntryMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GraalVMReflectConfigGenerator extends DaogenBasicGenerator {

	public static final String KEY = GraalVMReflectConfigGenerator.class.getSimpleName();

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void init(DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity) throws ConfigException {
		String sourceFolder = daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_RESOURCES );
		String jsonFile = daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_GRAALVM_REFLECT_CONFIG );
		super.setDaogenConfig( daogenConfig );
		super.init( sourceFolder, jsonFile, STYLE_CLASS, daogenConfig, entity );
		File path = new File( new File( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER ), sourceFolder ), jsonFile );
		log.info( "path : {}", path );
		this.setJavaFile(path);
	}

	@Override
	public void generateDaogenBody() throws IOException {
		// donothing()
	}
	private void handleEntity(DaogenCatalogEntity entity, List<Entry> reflectConfig  ) {
		String[] entryNames = { fullObjectName( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL ), DaogenCatalogConstants.modelName( entity ) ),
				fullObjectName( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ), DaogenCatalogConstants.helperName( entity ) ),
				fullObjectName( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ), DaogenCatalogConstants.wrapperName( entity ) ) };
		for ( String name : entryNames ) {
			Entry entry = new Entry();
			log.info( "name : {}", name );
			entry.setName( name );
			reflectConfig.add( entry );
			List<EntryMethod> methods = new ArrayList<>();
			// init methods
			methods.add( new EntryMethod( "<init>" ) );
			// main properties getter
			methods.addAll( entity.stream().map( field -> {
				EntryMethod m = new EntryMethod();
				m.setName( "get"+GeneratorNameHelper.toClassName( field.getId() ) );
				return m;
			} ).collect( Collectors.toList() ) );
			// relations properties getter
			methods.addAll( entity.getRelations().stream().map( relation -> {
				EntryMethod m = new EntryMethod();
				m.setName( "get"+GeneratorNameHelper.toClassName( relation.getName() ) );
				return m;
			} ).collect( Collectors.toList() ) );
			// empty method
			methods.add( new EntryMethod( "isEmpty" ) );
			entry.setMethods( methods );
		}

	}

	@Override
	public void generate() throws IOException {
		List<String> entityIdList = new ArrayList<>( this.getDaogenConfig().getIdSet() );
		Collections.sort( entityIdList );
		List<Entry> reflectConfig = new ArrayList<>();
		// iterate over entity to generate
		for ( String entityId : entityIdList ) {
			DaogenCatalogEntity entity = this.getDaogenConfig().getListMap( entityId );
			log.info( "native config for : {} -> {}", entity.getId(), entity.describe() );
			this.handleEntity( entity, reflectConfig );
		}
		GenerateReflectConfig generateReflectConfig = new GenerateReflectConfig();
		generateReflectConfig.generate( this.getWriter(), reflectConfig );
	}

}
