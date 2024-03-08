package org.fugerit.java.daogen.base.gen;

import java.io.IOException;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.reflect.MethodHelper;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;
import org.fugerit.java.daogen.base.gen.util.PropertyUtils;
import org.fugerit.java.daogen.base.gen.util.TypeUtils;

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

	private void generateDaogenBodyStart() {
		this.getWriter().println( TAB+"public "+this.getEntityStructName()+"( "+this.getEntityModelName()+" wrapped ) {" );
		this.getWriter().println( TAB_2+"super( wrapped );" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		this.getWriter().println( TAB+"public "+this.getEntityStructName()+"() {" );
		this.getWriter().println( TAB_2+"this( new "+this.getEntityHelperName()+"() );" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		
		this.getWriter().println( TAB+"public static final String SQL_TYPE_NAME = \""+this.getSQLStructName().toUpperCase()+"\";" );
		this.getWriter().println();
		
		this.getWriter().println( TAB+"public static final "+this.getEntityStructName()+" MAPPER = new "+this.getEntityStructName()+"();" );
		this.getWriter().println();
		
		// obj mapper impl
		this.getWriter().println( TAB+AT_OVERRIDE );
		this.getWriter().println( TAB+"public Map<String, Class<?>> newTypeMapper() throws SQLException {" );
		this.getWriter().println( TAB_2+"Map<String, Class<?>> map = new HashMap<>();" );
		this.getWriter().println( TAB_2+"map.put( SQL_TYPE_NAME, "+this.getEntityStructName()+".class );" );
		this.getWriter().println( TAB_2+"return map;" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();

		// SQLData impl
		this.getWriter().println( TAB+AT_OVERRIDE );
		this.getWriter().println( TAB+"public String getSQLTypeName() throws SQLException {" );
		this.getWriter().println( TAB_2+"return SQL_TYPE_NAME;" );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
		
		
		this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getEntityStructName()+" wrap( "+this.getEntityModelName()+" model ) {" );
		this.getWriter().println( TAB_2+""+this.getEntityStructName()+" res = null;" );
		this.getWriter().println( TAB_2+"if ( model instanceof "+this.getEntityStructName()+" ) {" );
		this.getWriter().println( TAB_3+"res = ("+this.getEntityStructName()+") model;" );
		this.getWriter().println( TAB_2+"} else { " );
		this.getWriter().println( TAB_3+"res = new "+this.getEntityStructName()+"( model );" );
		this.getWriter().println( TAB_2+"}" );
		this.getWriter().println( TAB_2+RETURN_RES_LIT );
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();
	}

	private void generateDaogenBodyLobField( String blobHandlerType, String clobHandlerType, StringBuilder line  ) {
		boolean isFirst = true;
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			if ( columnType.equalsIgnoreCase( blobHandlerType ) || 
					columnType.equalsIgnoreCase( clobHandlerType ) ) {
				String propertyName = GeneratorNameHelper.toPropertyName( field.getId() );
				if ( isFirst ) {
					isFirst = false;
				} else {
					line.append( " && " );
				}
				line.append( "(this."+MethodHelper.getGetterNameForProperty( propertyName )+"() == null)" );
			}
		}
	}
	
	private void generateDaogenBodyLobSetup( String blobHandlerType, String clobHandlerType  ) {
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
				String propertyName = GeneratorNameHelper.toPropertyName( field.getId() );
				String propertyNameH = propertyName+"Blob";
				this.getWriter().println( TAB_2+""+MethodHelper.getSetterNameForProperty( propertyNameH )+"( org.fugerit.java.core.db.daogen.LobHelper.createBlob( conn, "+MethodHelper.getGetterNameForProperty( propertyName )+"() ) );" );
			} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
				String propertyName = GeneratorNameHelper.toPropertyName( field.getId() );
				String propertyNameH = propertyName+"Clob";
				this.getWriter().println( TAB_2+""+MethodHelper.getSetterNameForProperty( propertyNameH )+"( org.fugerit.java.core.db.daogen.LobHelper.createClob( conn, "+MethodHelper.getGetterNameForProperty( propertyName )+"() ) );" );
			}
		}
	}
	
	private void generateDaogenBodyLob( boolean containsBlob, boolean containsClob, String blobHandlerType, String clobHandlerType ) {
		if ( containsBlob || containsClob ) {
			// inner property for blob handling
			this.getWriter().println( TAB+"private boolean areLobsSet = false;" );
			this.getWriter().println();	
			// check lobs method
			this.getWriter().println( TAB+"protected boolean checkLobs() {" );
			this.getWriter().println( TAB_2+"// lobs must be set, or lobs properties must be null for writeSQL() to work" );
			this.getWriter().println( TAB_2+"boolean check = this.areLobsSet;" );
			this.getWriter().println( TAB_2+"if ( !check ) {" );
			StringBuilder line = new StringBuilder();
			line.append( TAB_3+"check = " );
			this.generateDaogenBodyLobField(blobHandlerType, clobHandlerType, line);
			line.append( ";" );
			this.getWriter().println( line );
			this.getWriter().println( TAB_2+"}" );
			this.getWriter().println( TAB_2+"return check;" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();	
			// setup lobs method
			this.getWriter().println( TAB+"public void setupLobs( java.sql.Connection conn ) throws SQLException {" );
			this.generateDaogenBodyLobSetup(blobHandlerType, clobHandlerType);
			this.getWriter().println( TAB_2+"this.areLobsSet = true;" );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();	
			// wrapper helpers
			this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getEntityStructName()+" wrap( "+this.getEntityModelName()+" model, java.sql.Connection conn ) throws SQLException {" );
			this.getWriter().println( TAB_2+""+this.getEntityStructName()+" res = wrap( model );" );
			this.getWriter().println( TAB_3+"res.setupLobs( conn );" );
			this.getWriter().println( TAB_2+RETURN_RES_LIT );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
			this.getWriter().println( TAB+PUBLIC_STATIC_SPACE_LIT+this.getEntityStructName()+"[] wrap( "+this.getEntityModelName()+"[] list, java.sql.Connection conn ) throws SQLException {" );
			this.getWriter().println( TAB_2+""+this.getEntityStructName()+"[] res = null;" );
			this.getWriter().println( TAB_2+"if ( list != null ) {" );
			this.getWriter().println( TAB_3+"res = new "+this.getEntityStructName()+"[ list.length ];" );
			this.getWriter().println( TAB_3+"for ( int k=0; k<list.length; k++ ) {" );
			this.getWriter().println( TAB_3+TAB+"res[k] = wrap( list[k], conn );" );
			this.getWriter().println( TAB_3+"}" );
			this.getWriter().println( TAB_2+"}" );
			this.getWriter().println( TAB_2+RETURN_RES_LIT );
			this.getWriter().println( TAB+"}" );
			this.getWriter().println();
		}
	}
	
	private void handleField1( DaogenCatalogField field, String blobHandlerType, String clobHandlerType ) {
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
		} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
			extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.clobToCharHandler( (java.sql.Clob) stream.readObject() )";			
		} else if ( field.isUserType() ) {
			extratMethod = " ( ("+ field.getStructType() +") stream.readObject() ) ";
		} else {
			throw new ConfigRuntimeException( "Type : "+columnType+" not handled yet!" );
		}
		this.getWriter().println( TAB_2+"this.set"+javaSuffix+"( "+extratMethod+" );" );
	}
	
	private void handleField2( DaogenCatalogField field, String blobHandlerType, String clobHandlerType ) {
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
			extratMethod = "stream.writeBlob( FIELD-TOKEN )";
			javaSuffix+= "Blob";
		} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
			extratMethod = "stream.writeClob( FIELD-TOKEN )";
			javaSuffix+= "Clob";
		} else if ( field.isUserType() ) {
			extratMethod = "stream.writeObject( ("+ field.getStructType() +") FIELD-TOKEN )";
		} else {
			throw new ConfigRuntimeException( "Type : "+columnType+" not handled yet!" );
		}
		this.getWriter().println( TAB_2+""+extratMethod.replace( "FIELD-TOKEN" ,  "this.get"+javaSuffix+"()" )+";" );
	}
	
	@Override
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		
		String blobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Blob" );
		String clobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Clob" );
		
		this.generateDaogenBodyStart();

		boolean containsBlob = false;
		boolean containsClob = false;
		
		// blob / clob handling
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
				containsBlob = true;
				PropertyUtils.newProperty( this.getWriter(), field.getId()+"_BLOB", TypeUtils.TYPE_BLOB, false, true );
			} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
				containsClob = true;
				PropertyUtils.newProperty( this.getWriter(), field.getId()+"_CLOB", TypeUtils.TYPE_CLOB, false, true );
			}
		}
		
		this.generateDaogenBodyLob(containsBlob, containsClob, blobHandlerType, clobHandlerType);
		
		// readSQL()
		this.getWriter().println( TAB+AT_OVERRIDE );
		this.getWriter().println( TAB+"public void readSQL(SQLInput stream, String typeName) throws SQLException {" );
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			this.handleField1(field, blobHandlerType, clobHandlerType);
		}
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();

		// readSQL()
		this.getWriter().println( TAB+AT_OVERRIDE );
		this.getWriter().println( TAB+"public void writeSQL(SQLOutput stream) throws SQLException {" );
		if ( containsBlob || containsClob ) {
			this.getWriter().println( TAB_2+"if ( !this.checkLobs() ) {" );
			this.getWriter().println( TAB_3+"org.fugerit.java.core.db.daogen.BasicHelper.throwUnsupported( \"To use writeSQL() you must invoke setupLobs() for  \"+this.getSQLTypeName() );" );
			this.getWriter().println( TAB_2+"}" );
			this.getWriter().println( TAB_2+"this.areLobsSet = false;\t// clob and blob will be used only once" );
		}
		for ( DaogenCatalogField field : this.getCurrentEntity() )  {
			this.handleField2(field, blobHandlerType, clobHandlerType);
		}
		this.getWriter().println( TAB+"}" );
		this.getWriter().println();	
	}

	
	
}
