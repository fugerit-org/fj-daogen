package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelLogData;
import org.fugerit.java.daogen.sample.impl.helper.HelperLogData;
import org.fugerit.java.daogen.sample.impl.helper.WrapperLogData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjLogData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjLogData extends WrapperLogData implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 888885937636L;

	public ObjLogData( ModelLogData wrapped ) {
		super( wrapped );
	}

	public ObjLogData() {
		this( new HelperLogData() );
	}

	public final static String SQL_TYPE_NAME = "OBJ_LOG_DATA";

	public final static ObjLogData MAPPER = new ObjLogData();

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put( SQL_TYPE_NAME, ObjLogData.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjLogData wrap( ModelLogData model ) {
		ObjLogData res = null;
		if ( model instanceof ObjLogData ) {
			res = (ObjLogData) model;
		} else { 
			res = new ObjLogData( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setLogTime( stream.readTimestamp() );
		this.setInfo( stream.readString() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getId() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getLogTime() ) );
		stream.writeString( this.getInfo() );
	}

}
