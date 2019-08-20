package org.fugerit.java.daogen.base.gen;

import java.io.File;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.SimpleJavaGenerator;
import org.fugerit.java.core.util.collection.KeyObject;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;

public abstract class DaogenBasicGenerator extends SimpleJavaGenerator implements KeyObject<String> {

	private DaogenCatalogConfig daogenConfig;
	
	private DaogenCatalogEntity currentEntity;

	public static String fullObjectName( String packageName, String simpleClassName ) {
		return packageName+"."+simpleClassName;
	}

	public abstract void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException;
	
	public void init( String sourceFolder, String fullObjectBName, String javaStyle, DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( new File( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER ), sourceFolder ), fullObjectBName, javaStyle, daogenConfig.getGeneralProps() );
		this.daogenConfig = daogenConfig;
		this.currentEntity = entity;
	}

	protected DaogenCatalogConfig getDaogenConfig() {
		return daogenConfig;
	}

	protected void setDaogenConfig(DaogenCatalogConfig daogenConfig) {
		this.daogenConfig = daogenConfig;
	}

	protected DaogenCatalogEntity getCurrentEntity() {
		return currentEntity;
	}

	protected void setCurrentEntity(DaogenCatalogEntity currentEntity) {
		this.currentEntity = currentEntity;
	}

	@Override
	public abstract void generateBody() throws Exception;
	
	protected String getBaseName() {
		return DaogenCatalogConstants.modelName( this.getCurrentEntity() );
	}
	
	protected String getEntityModelName() {
		return DaogenCatalogConstants.modelName( this.getCurrentEntity() );
	}
	
	protected String getEntityRSEName() {
		return DaogenCatalogConstants.rseName( this.getCurrentEntity() );
	}
	
	protected String getEntityHelperName() {
		return DaogenCatalogConstants.helperName( this.getCurrentEntity() );
	}
	
	protected String getEntityWrapperName() {
		return DaogenCatalogConstants.wrapperName( this.getCurrentEntity() );
	}
	
	protected String getEntityFacadeDefName() {
		return DaogenCatalogConstants.facadeDefName( this.getCurrentEntity() );
	}
	
	protected String getEntityFacadeDataImplName() {
		return DaogenCatalogConstants.facadeImplDataName( this.getCurrentEntity() );
	}
	
	protected String getEntityFinderName() {
		return DaogenCatalogConstants.finderlName( this.getCurrentEntity() );
	}
	
	protected String getEntityStructName() {
		return DaogenCatalogConstants.structName( this.getDaogenConfig(), this.getCurrentEntity() );
	}
	
	protected String getSQLStructName() {
		return DaogenCatalogConstants.structPrefix( this.getDaogenConfig() )+this.getCurrentEntity().getName();
	}
	
	protected String getEntityBaseResult() {
		return this.getClassBaseResult()+"<"+this.getEntityModelName()+">";
	}
	
	private String classDaogenContext;
	
	private String classCloseableDaogenContext;
	
	private String classDaoException;
	
	private String classBaseFinder;
	
	private String classBaseResult;
	
	private String classBaseHelper;
	
	private String classBaseWrapper;
	
	private String classDaoHelper;
	
	private String classSelectHelper;
	
	private String classInsertHelper;
	
	private String classUpdateHelper;
	
	private String classDeleteHelper;
	
	private String classRSEHelper;
	
	private String classDataFacade;
	
	private String classStructMapper;
	
	private String classServiceResult;

	protected String getClassDaogenContext() {
		return classDaogenContext;
	}

	protected void setClassDaogenContext(String classDaogenContext) {
		this.classDaogenContext = classDaogenContext;
	}

	protected String getClassDaoException() {
		return classDaoException;
	}

	protected void setClassDaoException(String classDaoException) {
		this.classDaoException = classDaoException;
	}

	protected String getClassBaseFinder() {
		return classBaseFinder;
	}

	protected void setClassBaseFinder(String classBaseFinder) {
		this.classBaseFinder = classBaseFinder;
	}

	protected String getClassBaseResult() {
		return classBaseResult;
	}

	protected void setClassBaseResult(String classBaseResult) {
		this.classBaseResult = classBaseResult;
	}

	protected String getClassBaseHelper() {
		return classBaseHelper;
	}

	protected void setClassBaseHelper(String classBaseHelper) {
		this.classBaseHelper = classBaseHelper;
	}

	protected String getClassBaseWrapper() {
		return classBaseWrapper;
	}

	protected void setClassBaseWrapper(String classBaseWrapper) {
		this.classBaseWrapper = classBaseWrapper;
	}

	protected String getClassDaoHelper() {
		return classDaoHelper;
	}

	protected void setClassDaoHelper(String classDaoHelper) {
		this.classDaoHelper = classDaoHelper;
	}

	protected String getClassSelectHelper() {
		return classSelectHelper;
	}

	protected void setClassSelectHelper(String classSelectHelper) {
		this.classSelectHelper = classSelectHelper;
	}

	protected String getClassInsertHelper() {
		return classInsertHelper;
	}

	protected void setClassInsertHelper(String classInsertHelper) {
		this.classInsertHelper = classInsertHelper;
	}

	protected String getClassUpdateHelper() {
		return classUpdateHelper;
	}

	protected void setClassUpdateHelper(String classUpdateHelper) {
		this.classUpdateHelper = classUpdateHelper;
	}

	protected String getClassDeleteHelper() {
		return classDeleteHelper;
	}

	protected void setClassDeleteHelper(String classDeleteHelper) {
		this.classDeleteHelper = classDeleteHelper;
	}

	protected String getClassRSEHelper() {
		return classRSEHelper;
	}

	protected void setClassRSEHelper(String classRSEHelper) {
		this.classRSEHelper = classRSEHelper;
	}

	protected String getClassDataFacade() {
		return classDataFacade;
	}

	protected void setClassDataFacade(String classDataFacade) {
		this.classDataFacade = classDataFacade;
	}

	protected String getClassStructMapper() {
		return classStructMapper;
	}

	protected void setClassStructMapper(String classStructMapper) {
		this.classStructMapper = classStructMapper;
	}

	protected String getClassCloseableDaogenContext() {
		return classCloseableDaogenContext;
	}

	protected void setClassCloseableDaogenContext(String classCloseableDaogenContext) {
		this.classCloseableDaogenContext = classCloseableDaogenContext;
	}

	protected String getClassServiceResult() {
		return classServiceResult;
	}

	protected void setClassServiceResult(String classServiceResult) {
		this.classServiceResult = classServiceResult;
	}

}
