package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUpload;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjUpload, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjUpload extends WrapperUpload implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 514160737140L;

	public ObjUpload( ModelUpload wrapped ) {
		super( wrapped );
	}

	public ObjUpload() {
		this( new HelperUpload() );
	}

	public static final String SQL_TYPE_NAME = "OBJ_UPLOAD";

	public static final ObjUpload MAPPER = new ObjUpload();

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<>();
		map.put( SQL_TYPE_NAME, ObjUpload.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjUpload wrap( ModelUpload model ) {
		ObjUpload res = null;
		if ( model instanceof ObjUpload ) {
			res = (ObjUpload) model;
		} else { 
			res = new ObjUpload( model );
		}
		return res;
	}

	private transient java.sql.Blob contentBlob;

	public void setContentBlob( java.sql.Blob value ) {
		this.contentBlob = value;
	}

	public java.sql.Blob getContentBlob() {
		return this.contentBlob;
	}

	private boolean areLobsSet = false;

	protected boolean checkLobs() {
		// lobs must be set, or lobs properties must be null for writeSQL() to work
		boolean check = this.areLobsSet;
		if ( !check ) {
			check = (this.getContent() == null);
		}
		return check;
	}

	public void setupLobs( java.sql.Connection conn ) throws SQLException {
		setContentBlob( org.fugerit.java.core.db.daogen.LobHelper.createBlob( conn, getContent() ) );
		this.areLobsSet = true;
	}

	public static ObjUpload wrap( ModelUpload model, java.sql.Connection conn ) throws SQLException {
		ObjUpload res = wrap( model );
			res.setupLobs( conn );
		return res;
	}

	public static ObjUpload[] wrap( ModelUpload[] list, java.sql.Connection conn ) throws SQLException {
		ObjUpload[] res = null;
		if ( list != null ) {
			res = new ObjUpload[ list.length ];
			for ( int k=0; k<list.length; k++ ) {
				res[k] = wrap( list[k], conn );
			}
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setDateInsert( stream.readTimestamp() );
		this.setDateUpdate( stream.readTimestamp() );
		this.setContent( org.fugerit.java.core.db.daogen.SQLTypeConverter.blobToByteHandler( (java.sql.Blob) stream.readObject() ) );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		if ( !this.checkLobs() ) {
			org.fugerit.java.core.db.daogen.BasicHelper.throwUnsupported( "To use writeSQL() you must invoke setupLobs() for  "+this.getSQLTypeName() );
		}
		this.areLobsSet = false;	// clob and blob will be used only once
		stream.writeBigDecimal( this.getId() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateInsert() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateUpdate() ) );
		stream.writeBlob( this.getContentBlob() );
	}

}
