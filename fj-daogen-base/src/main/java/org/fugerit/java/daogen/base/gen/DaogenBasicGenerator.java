package org.fugerit.java.daogen.base.gen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.io.FileIO;
import org.fugerit.java.core.javagen.SimpleJavaGenerator;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.core.util.collection.KeyObject;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class DaogenBasicGenerator extends SimpleJavaGenerator implements KeyObject<String> {

	public static final String BLANK = " ";
	
	public static final String TAB = "\t";
	
	public static final String TAB_2 = TAB+TAB;
	
	public static final String TAB_3 = TAB_2+TAB;
	
	protected static final String JD_THROWS = " * @throws ";
	
	protected static final String CLOSE_AND_THROWS = " ) throws ";
	
	protected static final String AT_OVERRIDE = "@Override";
	
	protected static final String REAL_CLASS_COMMENT = TAB+"// [HELPER/IMPL MODEL] this class is a stub and can be modified as you see fit (it will not been overwritten)";

	protected static final String CONTEXT_LIT = " context, ";
	
	protected static final String PUBLIC_LIT = "public ";
	
	protected static final String GT_LIT = ">";
	
	protected static final String LT_LIT = "<";
	
	protected static final String GENERIC_LIT = "<>();";
	
	protected static final String DAO_HELPER_LIT = " daoHelper = new ";
	
	protected static final String MODEL_SET_LIT = "model.set";
	
	protected static final String MODEL_GET_LIT = ", model.get";
	
	protected static final String CONTEXT_GEN_LIT = "<>( context );";
	
	protected static final String COMMA_END_LIT = "() );";
	
	protected static final String RETURN_RESULT_LIT = "return result;";
	
	protected static final String DAO_HELPER_UPDATE_LIT = "int res = daoHelper.update( query );";
	
	protected static final String EVALUATE_RESULT_LIT = "this.evaluteSqlUpdateResult(res, model, result);";
	
	protected static final String END_LINE_1_LIT = "\" ) ";
	
	protected static final String END_LINE_2_LIT = "\" ) ) ";
	
	protected static final String RETURN_RES_LIT = "return res;";
	
	protected static final String PUBLIC_SPACE_LIT = "public ";
	
	protected static final String PUBLIC_STATIC_SPACE_LIT = "public static ";
	
	protected static final String IN_CASE_OF_ERRORS_LIT = "in case of errors";
	
	@Override
	protected void customPartWorker( String startTag, String endTag, String indent ) throws IOException {
		if ( !this.isNoCustomComment() ) {
			customPartWorkerDaogen( this.getJavaFile(), this.getWriter(), startTag, endTag, indent );
		}
	}
	
	public static void customPartWorkerDaogen( File file, PrintWriter writer, String startTag, String endTag, String indent ) throws IOException {
		customPartWorkerDaogen(file, writer, startTag, endTag, indent, "" );
	}
	
	public static void customPartWorkerDaogen( File file, PrintWriter writer, String startTag, String endTag, String indent, String addIfEmpty ) throws IOException {
		writer.println( indent+startTag );
		boolean customCode = false;
		boolean isEmpty = true;
		if ( file.exists() ) {
			try ( BufferedReader reader = new BufferedReader( new FileReader( file ) ) ) {
				String line = reader.readLine();
				while ( line != null ) {
					if ( line.contains( startTag ) && !line.trim().startsWith( "*" ) ) {
						customCode = true;
					} else if ( line.contains( endTag ) && !line.trim().startsWith( "*" ) ) {
						customCode = false;
					} else if ( customCode ) {
						writer.println( line );
						isEmpty = false;
					}
					line = reader.readLine();
				}
			}
		}
		if ( isEmpty ) {
			writer.print( addIfEmpty );
		}
		writer.println( indent+endTag );
		writer.println();
	}

	@Override
	public void write() throws IOException {
		if ( this.skipWrite ) {
			log.info( "Skip writing this generator : {}", this );
		} else {
			super.write();
		}
	}
	
	protected boolean checkSkipRealClass() throws IOException {
		if ( this.getJavaFile().exists() ) {
			String content = FileIO.readString( this.getJavaFile() );
			if ( content.contains( REAL_CLASS_COMMENT ) ) {
				this.setSkipWrite( true );
			}
		}
		return this.isSkipWrite();
	}

	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		log.trace( "daogenConfig:{}, entity:{}", daogenConfig, entity );
		return true;
	}
	
	protected DaogenBasicGenerator() {
		this.decorators = new ArrayList<>();
	}
	
	private List<DaogenBasicDecorator> decorators;
	
	
	public List<DaogenBasicDecorator> getDecorators() {
		return decorators;
	}

	private DaogenCatalogConfig daogenConfig;
	
	private DaogenCatalogEntity currentEntity;
	
	protected Integer getJdkTargetVersion() {
		return Integer.valueOf( StringUtils.valueWithDefault( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_JDK_TARGET_VERSION ) , DaogenCatalogConstants.GEN_PROP_JDK_TARGET_VERSION_DEFAULT ) );
	}
	
	protected boolean isJdkVersionEquals( Integer jdkVerson ) {
		return this.getJdkTargetVersion().intValue() == jdkVerson.intValue();
	}
	
	protected boolean isJdkVersionAtLeast( Integer jdkVerson ) {
		return this.getJdkTargetVersion().intValue() >= jdkVerson.intValue();
	}
	
	protected String getJeeTargetMode() {
		return StringUtils.valueWithDefault( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_JEE_TARGET_MODE ) , DaogenCatalogConstants.GEN_PROP_JEE_TARGET_MODE_DEFAULT );
	}
	
	private boolean skipWrite = false;
	
	public boolean isSkipWrite() {
		return skipWrite;
	}

	public void setSkipWrite(boolean skipWrite) {
		this.skipWrite = skipWrite;
	}

	public static String fullObjectName( String packageName, String simpleClassName ) {
		if ( packageName == null ) {
			throw new ConfigRuntimeException( "fullObjectName() packageName is null" );
		}
		if ( simpleClassName == null ) {
			throw new ConfigRuntimeException( "fullObjectName() simpleClassName is null" );
		}
		return packageName+"."+simpleClassName;
	}

	public abstract void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException;
	
	
	public void init( String sourceFolder, String fullObjectBName, String javaStyle, DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		if ( DaogenCatalogConstants.GEN_PROP_SRC_HELPERS_MAIN.equalsIgnoreCase( sourceFolder ) ) {
			sourceFolder = daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA );
		} else if ( DaogenCatalogConstants.GEN_PROP_SRC_HELPERS_GEN.equalsIgnoreCase( sourceFolder ) ) {
			sourceFolder = "target/generated-sources/daogen/";
		}
		if ( sourceFolder == null || sourceFolder.equalsIgnoreCase( "null" ) ) {
			throw new ConfigException( "null source folder for : "+fullObjectBName );
		}
		super.init( new File( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_BASE_SRC_FOLDER ), sourceFolder ), fullObjectBName, javaStyle, daogenConfig.getGeneralProps(), daogenConfig.getLineSeparator() );
		this.daogenConfig = daogenConfig;
		this.currentEntity = entity;
		for ( DaogenBasicDecorator decorator : this.getDecorators() ) {
			try {
				decorator.addImports();
			} catch (Exception e) {
				throw new ConfigRuntimeException( e );
			}
		}
	}

	public DaogenCatalogConfig getDaogenConfig() {
		return daogenConfig;
	}

	protected void setDaogenConfig(DaogenCatalogConfig daogenConfig) {
		this.daogenConfig = daogenConfig;
	}

	public DaogenCatalogEntity getCurrentEntity() {
		return currentEntity;
	}

	protected void setCurrentEntity(DaogenCatalogEntity currentEntity) {
		this.currentEntity = currentEntity;
	}

	@Override
	public void generateBody() throws IOException {
		for ( DaogenBasicDecorator decorator : this.getDecorators() ) {
			try {
				decorator.addBeforeClassBody();
			} catch (Exception e) {
				throw new ConfigRuntimeException( e );
			}
		}
		this.generateDaogenBody();
		for ( DaogenBasicDecorator decorator : this.getDecorators() ) {
			try {
				decorator.addAfterClassBody();
			} catch (Exception e) {
				throw new ConfigRuntimeException( e );
			}
		}
	}
	
	public abstract void generateDaogenBody() throws IOException;
	
	public String getBaseName() {
		return DaogenCatalogConstants.modelName( this.getCurrentEntity() );
	}
	
	public String getEntityModelName() {
		return DaogenCatalogConstants.modelName( this.getCurrentEntity() );
	}
	
	public String getEntityRSEName() {
		return DaogenCatalogConstants.rseName( this.getCurrentEntity() );
	}
	
	public String getEntityHelperName() {
		return DaogenCatalogConstants.helperName( this.getCurrentEntity() );
	}
	
	public String getEntityWrapperName() {
		return DaogenCatalogConstants.wrapperName( this.getCurrentEntity() );
	}
	
	public String getEntityFacadeDefName() {
		return DaogenCatalogConstants.facadeDefName( this.getCurrentEntity() );
	}
	
	public String getEntityFacadeDataImplName() {
		return DaogenCatalogConstants.facadeImplDataName( this.getCurrentEntity() );
	}
	
	public String getEntityFinderName() {
		return DaogenCatalogConstants.finderlName( this.getCurrentEntity() );
	}
	
	public String getEntityStructName() {
		return DaogenCatalogConstants.structName( this.getDaogenConfig(), this.getCurrentEntity() );
	}
	
	public String getSQLStructName() {
		String sqlStructName = this.getCurrentEntity().getStructSqlType();
		if ( DaogenCatalogEntity.ATT_STRUCT_SQL_TYPE_USENAME.equalsIgnoreCase( sqlStructName ) ) {
			sqlStructName = this.getCurrentEntity().getName();
		} else if ( StringUtils.isEmpty( sqlStructName ) ) {
			sqlStructName = DaogenCatalogConstants.structPrefix( this.getDaogenConfig() )+this.getCurrentEntity().getName();
		}
		return sqlStructName;
	}
	
	public String getEntityBaseResult() {
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

	public String getClassDaogenContext() {
		return classDaogenContext;
	}

	protected void setClassDaogenContext(String classDaogenContext) {
		this.classDaogenContext = classDaogenContext;
	}

	public String getClassDaoException() {
		return classDaoException;
	}

	protected void setClassDaoException(String classDaoException) {
		this.classDaoException = classDaoException;
	}

	public String getClassBaseFinder() {
		return classBaseFinder;
	}

	protected void setClassBaseFinder(String classBaseFinder) {
		this.classBaseFinder = classBaseFinder;
	}

	public String getClassBaseResult() {
		return classBaseResult;
	}

	protected void setClassBaseResult(String classBaseResult) {
		this.classBaseResult = classBaseResult;
	}

	public String getClassBaseHelper() {
		return classBaseHelper;
	}

	protected void setClassBaseHelper(String classBaseHelper) {
		this.classBaseHelper = classBaseHelper;
	}

	public String getClassBaseWrapper() {
		return classBaseWrapper;
	}

	protected void setClassBaseWrapper(String classBaseWrapper) {
		this.classBaseWrapper = classBaseWrapper;
	}

	public String getClassDaoHelper() {
		return classDaoHelper;
	}

	protected void setClassDaoHelper(String classDaoHelper) {
		this.classDaoHelper = classDaoHelper;
	}

	public String getClassSelectHelper() {
		return classSelectHelper;
	}

	protected void setClassSelectHelper(String classSelectHelper) {
		this.classSelectHelper = classSelectHelper;
	}

	public String getClassInsertHelper() {
		return classInsertHelper;
	}

	protected void setClassInsertHelper(String classInsertHelper) {
		this.classInsertHelper = classInsertHelper;
	}

	public String getClassUpdateHelper() {
		return classUpdateHelper;
	}

	protected void setClassUpdateHelper(String classUpdateHelper) {
		this.classUpdateHelper = classUpdateHelper;
	}

	public String getClassDeleteHelper() {
		return classDeleteHelper;
	}

	protected void setClassDeleteHelper(String classDeleteHelper) {
		this.classDeleteHelper = classDeleteHelper;
	}

	public String getClassRSEHelper() {
		return classRSEHelper;
	}

	protected void setClassRSEHelper(String classRSEHelper) {
		this.classRSEHelper = classRSEHelper;
	}

	public String getClassDataFacade() {
		return classDataFacade;
	}

	protected void setClassDataFacade(String classDataFacade) {
		this.classDataFacade = classDataFacade;
	}

	public String getClassStructMapper() {
		return classStructMapper;
	}

	protected void setClassStructMapper(String classStructMapper) {
		this.classStructMapper = classStructMapper;
	}

	public String getClassCloseableDaogenContext() {
		return classCloseableDaogenContext;
	}

	protected void setClassCloseableDaogenContext(String classCloseableDaogenContext) {
		this.classCloseableDaogenContext = classCloseableDaogenContext;
	}

	public String getClassServiceResult() {
		return classServiceResult;
	}

	protected void setClassServiceResult(String classServiceResult) {
		this.classServiceResult = classServiceResult;
	}

	@Override
	protected void beforeClass() {
		for ( DaogenBasicDecorator decorator : this.getDecorators() ) {
			try {
				decorator.addBeforeClass();	
			} catch (Exception e) {
				throw new ConfigRuntimeException( e );
			}
		}
	}
	
	protected void writeSerialHelpers() {
		this.getWriter().println( TAB+"private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {" );
		this.getWriter().println( TAB_2+"// this class is conditionally serializable, depending on contained object" );
		this.getWriter().println( TAB_2+"// special situation can be handled using this method in future" );
		this.getWriter().println( TAB_2+"out.defaultWriteObject();" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		this.getWriter().println( TAB+"private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {" );
		this.getWriter().println( TAB_2+"// this class is conditionally serializable, depending on contained object" );
		this.getWriter().println( TAB_2+"// special situation can be handled using this method in future" );
		this.getWriter().println( TAB_2+"in.defaultReadObject();" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
	}

}
