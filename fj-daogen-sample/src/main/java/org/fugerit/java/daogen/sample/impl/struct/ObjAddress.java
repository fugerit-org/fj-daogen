package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelAddress;
import org.fugerit.java.daogen.sample.impl.helper.HelperAddress;
import org.fugerit.java.daogen.sample.impl.helper.WrapperAddress;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjAddress, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjAddress extends WrapperAddress implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 411737603450L;

	public ObjAddress( ModelAddress wrapped ) {
		super( wrapped );
	}

	public ObjAddress() {
		this( new HelperAddress() );
	}

	public final static String SQL_TYPE_NAME = "OBJ_ADDRESS";

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put( SQL_TYPE_NAME, ObjAddress.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjAddress wrap( ModelAddress model ) {
		ObjAddress res = null;
		if ( model instanceof ObjAddress ) {
			res = (ObjAddress) model;
		} else { 
			res = new ObjAddress( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setIdUser( stream.readBigDecimal() );
		this.setDateInsert( stream.readTimestamp() );
		this.setDateUpdate( stream.readTimestamp() );
		this.setInfo( stream.readString() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getId() );
		stream.writeBigDecimal( this.getIdUser() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateInsert() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateUpdate() ) );
		stream.writeString( this.getInfo() );
	}

}
