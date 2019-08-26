package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.db.dao.DAOException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class StructGenerator extends DaogenBasicGenerator {

	public static final String KEY = "StructGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_STRUCT ), DaogenCatalogConstants.structName( daogenConfig, entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.setClassStructMapper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_STRUCTMAPPER_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityWrapperName() );
		this.getImportList().add( "java.util.Map" );
		this.getImportList().add( "java.util.HashMap" );
		this.getImportList().add( "java.sql.SQLData" );
		this.getImportList().add( "java.sql.SQLInput" );
		this.getImportList().add( "java.sql.SQLOutput" );
		this.getImportList().add( "java.sql.SQLException" );
		this.setImplementsInterface( "SQLData, "+this.getClassStructMapper() );
		this.setExtendsClass( this.getEntityWrapperName() );
	}

	@Override
	public void generateBody() throws Exception {
		this.addSerialVerUID();
		this.getWriter().println( "	public "+this.getEntityStructName()+"( "+this.getEntityModelName()+" wrapped ) {" );
		this.getWriter().println( "		super( wrapped );" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
		this.getWriter().println( "	public "+this.getEntityStructName()+"() {" );
		this.getWriter().println( "		this( new "+this.getEntityHelperName()+"() );" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
		
		this.getWriter().println( "	public final static String SQL_TYPE_NAME = \""+this.getSQLStructName().toUpperCase()+"\";" );
		this.getWriter().println();
		
		// obj mapper impl
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public Map<String, Class<?>> newTypeMapper() throws SQLException {" );
		this.getWriter().println( "		Map<String, Class<?>> map = new HashMap<String, Class<?>>();" );
		this.getWriter().println( "		map.put( SQL_TYPE_NAME, "+this.getEntityStructName()+".class );" );
		this.getWriter().println( "		return map;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();

		// SQLData impl
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public String getSQLTypeName() throws SQLException {" );
		this.getWriter().println( "		return SQL_TYPE_NAME;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();
		
		
		this.getWriter().println( "	public static "+this.getEntityStructName()+" wrap( "+this.getEntityModelName()+" model ) {" );
		this.getWriter().println( "		"+this.getEntityStructName()+" res = null;" );
		this.getWriter().println( "		if ( model instanceof "+this.getEntityStructName()+" ) {" );
		this.getWriter().println( "			res = ("+this.getEntityStructName()+") model;" );
		this.getWriter().println( "		} else { " );
		this.getWriter().println( "			res = new "+this.getEntityStructName()+"( model );" );
		this.getWriter().println( "		}" );
		this.getWriter().println( "		return res;" );
		this.getWriter().println( "	}" );
		this.getWriter().println();

		boolean containsBlob = false;
		
		String blobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Blob" );
		String clobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Clob" );
		
		// readSQL()
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public void readSQL(SQLInput stream, String typeName) throws SQLException {" );
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String extratMethod = null;
			if ( columnType.equalsIgnoreCase( "java.lang.String" ) ) {
				extratMethod = "stream.readString()";
			} else 	if ( columnType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
				extratMethod = "stream.readBigDecimal()";
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Date" ) ) {
				extratMethod = "stream.readDate()";
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Timestamp" ) || columnType.equalsIgnoreCase( "java.util.Date" ) ) {
				extratMethod = "stream.readTimestamp()";
			} else 	if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
				extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.blobToByteHandler( (java.sql.Blob) stream.readObject() )";
				containsBlob = true;
			} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
				extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.clobToCharHandler( (java.sql.Clob) stream.readObject() )";
				containsBlob = true;				
			} else {
				throw new ConfigException( "Type : "+columnType+" not handled yet!" );
			}
			this.getWriter().println( "		this.set"+javaSuffix+"( "+extratMethod+" );" );
		}
		this.getWriter().println( "	}" );
		this.getWriter().println();		

		// readSQL()
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public void writeSQL(SQLOutput stream) throws SQLException {" );
		if ( containsBlob ) {
			this.getWriter().println( "		throwUnsupported( \"Method writeSQL() not implemented for \"+this.getSQLTypeName() );" );
		}
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String extratMethod = null;
			if ( columnType.equalsIgnoreCase( "java.lang.String" ) ) {
				extratMethod = "stream.writeString( FIELD-TOKEN )";
			} else 	if ( columnType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
				extratMethod = "stream.writeBigDecimal( FIELD-TOKEN )";
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Date" ) ) {
				extratMethod = "stream.writeDate( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlDate( FIELD-TOKEN ) )";
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Timestamp" ) || columnType.equalsIgnoreCase( "java.util.Date" ) ) {
				extratMethod = "stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( FIELD-TOKEN ) )";
			} else 	if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
				extratMethod = "// blob must be writtern separately : FIELD-TOKEN";
			} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
				extratMethod = "// clob must be writtern separately : FIELD-TOKEN";				
			} else {
				throw new DAOException( "Type : "+columnType+" not handled yet!" );
			}
			this.getWriter().println( "		"+extratMethod.replaceAll( "FIELD-TOKEN" ,  "this.get"+javaSuffix+"()" )+";" );
		}
		this.getWriter().println( "	}" );
		this.getWriter().println();	
	}

	
	
}
