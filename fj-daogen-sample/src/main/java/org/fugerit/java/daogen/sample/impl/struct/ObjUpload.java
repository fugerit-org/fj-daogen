package org.fugerit.java.daogen.sample.impl.struct;

import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelUpload;
import org.fugerit.java.daogen.sample.impl.helper.HelperUpload;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUpload;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.SQLException;

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

	public final static String SQL_TYPE_NAME = "OBJ_UPLOAD";

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
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

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setDateInsert( stream.readTimestamp() );
		this.setDateUpdate( stream.readTimestamp() );
		this.setContent( org.fugerit.java.core.db.daogen.SQLTypeConverter.blobToByteHandler( (java.sql.Blob) stream.readObject() ) );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		throwUnsupported( "Method writeSQL() not implemented for "+this.getSQLTypeName() );
		stream.writeBigDecimal( this.getId() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateInsert() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateUpdate() ) );
		// blob must be writtern separately : this.getContent();
	}

}
