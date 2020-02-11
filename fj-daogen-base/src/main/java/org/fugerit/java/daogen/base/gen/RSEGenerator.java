package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenClassConfigHelper;

public class RSEGenerator extends DaogenBasicGenerator {

	public static final String KEY = "RSEGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	public void init( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) throws ConfigException {
		super.init( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_SRC_MAIN_JAVA ), 
				fullObjectName( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_RSE ), DaogenCatalogConstants.rseName( entity ) ), 
				STYLE_CLASS, daogenConfig, entity );
		this.setClassRSEHelper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_RSEHELPER_BASE, this.getImportList() ) );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+this.getEntityModelName() );
		this.getImportList().add( this.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+this.getEntityHelperName() );
		this.getImportList().add( "java.sql.ResultSet" );
		this.getImportList().add( "java.sql.SQLException" );
		this.setExtendsClass( this.getClassRSEHelper()+"<"+this.getEntityModelName()+">" );
	}

	@Override
	public void generateDaogenBody() throws Exception {
		this.addSerialVerUID();
		this.getWriter().println( "	public static final "+this.getEntityRSEName()+" DEFAULT = new "+this.getEntityRSEName()+"();" );
		this.getWriter().println();
		this.getWriter().println( "	@Override" );
		this.getWriter().println( "	public "+this.getEntityModelName()+" extractNext( ResultSet rs ) throws SQLException { " );
		this.getWriter().println( "		"+this.getEntityHelperName()+" current = new "+this.getEntityHelperName()+"();" );
		
		String blobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Blob" );
		String clobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Clob" );
		
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			String columnName = field.getId();
			String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
			String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
			String extratMethod = null;
			String indent = "";
			boolean unsafe = BooleanUtils.isTrue( field.getUnsafe() );
			if ( unsafe ) {
				this.getWriter().println( "		// unsafe field (error will be only printed)" );
				this.getWriter().println( "		try { " );
				indent = "	";
			}
			boolean tryCatch = false;
			if ( columnType.equalsIgnoreCase( "java.lang.String" ) ) {
				extratMethod = "rs.getString( \""+columnName+"\" ) ";
			} else 	if ( columnType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
				extratMethod = "rs.getBigDecimal( \""+columnName+"\" ) ";
			} else 	if ( columnType.equalsIgnoreCase( "java.lang.Integer" ) ) {
				extratMethod = "rs.getInt( \""+columnName+"\" ) ";				
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Date" ) ) {
				extratMethod = "rs.getDate( \""+columnName+"\" ) ";				
			} else 	if ( columnType.equalsIgnoreCase( "java.sql.Timestamp" ) ) {
				extratMethod = "rs.getDate( \""+columnName+"\" ) ";				
			} else 	if ( columnType.equalsIgnoreCase( "java.util.Date" ) ) {				
				extratMethod = "rs.getTimestamp( \""+columnName+"\" ) ";	
			} else 	if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
				extratMethod = blobHandlerType+".newHandlerPreload( rs.getBlob( \""+columnName+"\" ) ) ";	
				tryCatch = true;
			} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
				extratMethod = clobHandlerType+".newHandlerPreload( rs.getClob( \""+columnName+"\" ) ) ";	
				tryCatch = true;				
			} else {
				throw new ConfigException( "Type : "+columnType+" not handled yet!" );
			}
			if ( tryCatch ) {
				this.getWriter().println( indent+"		try { " );
				this.getWriter().println( indent+"			current.set"+javaSuffix+"( "+extratMethod+" );" );
				this.getWriter().println( indent+"		} catch (Exception e) {" );
				this.getWriter().println( indent+"			throw new SQLException( \"Errore estrazione campo : "+columnName+"\", e );" );
				this.getWriter().println( indent+"		}" );
			} else {
				this.getWriter().println( indent+"		current.set"+javaSuffix+"( "+extratMethod+" );" );
			}
			if ( unsafe ) {
				this.getWriter().println( "		} catch (Exception e1) {" );
				this.getWriter().println( "			logger.warn( \"Exception handling field '{}' -> {}\", \""+columnName+"\", e1 );" );
				this.getWriter().println( "		}" );
			}
		}
		this.getWriter().println( "		return current;" );
		this.getWriter().println( "	} " );
	}

	
	
}
