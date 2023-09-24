package org.fugerit.java.daogen.base.gen.helper;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenHelperGenerator;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseRestLoadRealGenerator extends DaogenBasicGenerator {

	public BaseRestLoadRealGenerator(String key, List<String> importList, String propertyParage,
			String patternBaseAnnotation, String patternPathAnnotation) {
		super();
		this.key = key;
		this.importList = importList;
		this.propertyPackage = propertyParage;
		log.debug( "patternBaseAnnotation:{}, patternPathAnnotation:{}", patternBaseAnnotation, patternPathAnnotation );
		this.patternBaseAnnotation = patternBaseAnnotation;
		this.patternPathAnnotation = patternPathAnnotation;
	}

	private String key;
	
	private List<String> importList;
	
	private String propertyPackage;
	
	private String patternBaseAnnotation;
	
	private String patternPathAnnotation;
	
	@Override
	public String getKey() {
		return key;
	}


	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		String fullObjectBName = fullObjectName( daogenConfig.getGeneralProp( this.propertyPackage ), DaogenCatalogConstants.restLoadName( entity ) );
		log.debug( "propertyPackage:{}, fullObjectBName:{}", this.propertyPackage, fullObjectBName  );
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectBName, 
				STYLE_CLASS, daogenConfig, entity );
		for ( String currentImport : this.importList ) {
			this.getImportList().add( currentImport );
		}
		this.setExtendsClass( DaogenHelperGenerator.toHelperClassName( this.getJavaName() ) );
		try {
			this.checkSkipRealClass();
		} catch (IOException e) {
			throw new ConfigException( e );
		}
	}

	@Override
	protected void beforeClass() {
		super.beforeClass();
		String urlBase = this.getCurrentEntity().getName().replace( "_" , "" ).toLowerCase();
		SafeFunction.applyIfNotNull( this.patternBaseAnnotation, () -> this.getWriter().println( patternBaseAnnotation ) );
		log.debug( "patternPathAnnotation : {}, urlBase : {}", patternPathAnnotation, urlBase );
		String pathAnnotation = MessageFormat.format( this.patternPathAnnotation , urlBase);
		log.debug( "pathAnnotation : {}", pathAnnotation );
		this.getWriter().println( pathAnnotation );
	}

	@Override
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		this.println( REAL_CLASS_COMMENT );
	}

}
