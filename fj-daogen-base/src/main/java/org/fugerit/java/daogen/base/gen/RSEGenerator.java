package org.fugerit.java.daogen.base.gen;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.daogen.base.config.*;
import org.fugerit.java.daogen.base.gen.util.FacadeGeneratorUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class RSEGenerator extends DaogenBasicGenerator {

	public static final String KEY = "RSEGenerator";
	
	@Override
	public String getKey() {
		return KEY;
	}
	
	@Override
	public boolean isGenerate( DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity ) {
		return FacadeGeneratorUtils.isFacadeGenerate( entity );
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
	public void generateDaogenBody() throws IOException {
		this.addSerialVerUID();
		this.getWriter().println( TAB+"public static final "+this.getEntityRSEName()+" DEFAULT = new "+this.getEntityRSEName()+"();" );
		this.getWriter().println();
		this.getWriter().println( TAB+"@Override" );
		this.getWriter().println( TAB+"public "+this.getEntityModelName()+" extractNext( ResultSet rs ) throws SQLException { " );
		this.getWriter().println( TAB_2+""+this.getEntityHelperName()+" current = new "+this.getEntityHelperName()+"();" );
		String blobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Blob" );
		String clobHandlerType = this.getDaogenConfig().getTypeMapper().getTypeMapConfig().getProperty( "model_java.sql.Clob" );
		for ( DaogenCatalogField field : this.getCurrentEntity() ) {
			this.handleField(field, blobHandlerType, clobHandlerType);
		}
		this.getWriter().println( TAB_2+"return current;" );
		this.getWriter().println( TAB+"} " );
	}
	
	private void handleField( DaogenCatalogField field, String blobHandlerType, String clobHandlerType ) {
		String columnName = field.getId();
		String columnType = this.getDaogenConfig().getTypeMapper().mapForModel( field );
		String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
		String extratMethod = null;
		String indent = "";
		boolean unsafe = BooleanUtils.isTrue( field.getUnsafe() );
		if ( unsafe ) {
			this.getWriter().println( TAB_2+"// unsafe field (error will be only printed)" );
			this.getWriter().println( TAB_2+"try { " );
			indent = TAB;
		}
		boolean tryCatch = false;
		if ( columnType.equalsIgnoreCase( String.class.getName() ) ) {
			extratMethod = "rs.getString( \""+columnName+END_LINE_1_LIT;
		} else 	if ( columnType.equalsIgnoreCase(BigDecimal.class.getName() ) ) {
			extratMethod = "rs.getBigDecimal( \""+columnName+END_LINE_1_LIT;
		} else 	if ( columnType.equalsIgnoreCase( Integer.class.getName() ) ) {
			extratMethod = "rs.getInt( \""+columnName+END_LINE_1_LIT;				
		} else 	if ( columnType.equalsIgnoreCase(java.sql.Date.class.getName()) ) {
			extratMethod = "rs.getDate( \""+columnName+END_LINE_1_LIT;				
		} else 	if ( columnType.equalsIgnoreCase(Date.class.getName()) ) {
			extratMethod = "rs.getTimestamp( \""+columnName+END_LINE_1_LIT;
		} else 	if ( columnType.equalsIgnoreCase( Timestamp.class.getName() ) ) {
			extratMethod = "rs.getTimestamp( \""+columnName+END_LINE_1_LIT;
		} else 	if ( columnType.equalsIgnoreCase( LocalDate.class.getName() ) ) {
			extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDate( rs.getDate( \""+columnName+END_LINE_2_LIT;
		} else 	if ( columnType.equalsIgnoreCase( LocalTime.class.getName() ) ) {
			extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalTime( rs.getTime( \""+columnName+END_LINE_2_LIT;
		} else 	if ( columnType.equalsIgnoreCase( LocalDateTime.class.getName() ) ) {
			extratMethod = "org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( rs.getTimestamp( \""+columnName+END_LINE_2_LIT;
		} else 	if ( columnType.equalsIgnoreCase( blobHandlerType ) ) {
			extratMethod = blobHandlerType+".newHandlerPreload( rs.getBlob( \""+columnName+END_LINE_2_LIT;	
			tryCatch = true;
		} else 	if ( columnType.equalsIgnoreCase( clobHandlerType ) ) {
			extratMethod = clobHandlerType+".newHandlerPreload( rs.getClob( \""+columnName+END_LINE_2_LIT;	
			tryCatch = true;				
		} else {
			throw new ConfigRuntimeException( "Type : "+columnType+" not handled yet!" );
		}
		this.handleEnd(tryCatch, indent, javaSuffix, columnName, extratMethod, unsafe);
	}
	
	private void handleEnd( boolean tryCatch, String indent, String javaSuffix, String columnName, String extratMethod, boolean unsafe ) {
		if ( tryCatch ) {
			this.getWriter().println( indent+TAB_2+"try { " );
			this.getWriter().println( indent+TAB_3+"current.set"+javaSuffix+"( "+extratMethod+" );" );
			this.getWriter().println( indent+TAB_2+"} catch (Exception e) {" );
			this.getWriter().println( indent+TAB_3+"throw new SQLException( \"Errore estrazione campo : "+columnName+"\", e );" );
			this.getWriter().println( indent+TAB_2+"}" );
		} else {
			this.getWriter().println( indent+TAB_2+"current.set"+javaSuffix+"( "+extratMethod+" );" );
		}
		if ( unsafe ) {
			this.getWriter().println( TAB_2+"} catch (Exception e1) {" );
			this.getWriter().println( TAB_3+"logger.warn( \"Exception handling field '{}' -> {}\", \""+columnName+"\", e1 );" );
			this.getWriter().println( TAB_2+"}" );
		}
	}
	
}
