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

	public GraalVMReflectConfigGenerator() {
		super();
	}

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
			org.fugerit.java.nhg.reflect.config.EntryMethod initMethod = new EntryMethod();
			initMethod.setName( "<init>" );
			List<EntryMethod> methods = new ArrayList<>();
			methods.add( initMethod );
			methods.addAll( entity.stream().map( field -> {
				EntryMethod m = new EntryMethod();
				String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
				m.setName( "get"+javaSuffix );
				return m;
			} ).collect( Collectors.toList() ) );
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
