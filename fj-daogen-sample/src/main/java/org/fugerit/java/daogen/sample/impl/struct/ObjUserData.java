package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelUserData;
import org.fugerit.java.daogen.sample.impl.helper.HelperUserData;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUserData;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjUserData, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjUserData extends WrapperUserData implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 868724996032L;

	public ObjUserData( ModelUserData wrapped ) {
		super( wrapped );
	}

	public ObjUserData() {
		this( new HelperUserData() );
	}

	public static final String SQL_TYPE_NAME = "OBJ_USER_DATA";

	public static final ObjUserData MAPPER = new ObjUserData();

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<>();
		map.put( SQL_TYPE_NAME, ObjUserData.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjUserData wrap( ModelUserData model ) {
		ObjUserData res = null;
		if ( model instanceof ObjUserData ) {
			res = (ObjUserData) model;
		} else { 
			res = new ObjUserData( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setUsername( stream.readString() );
		this.setPassword( stream.readString() );
		this.setLastLogin( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( stream.readDate() ) );
		this.setDateInsert( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( stream.readDate() ) );
		this.setDateUpdate( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToLocalDateTime( stream.readDate() ) );
		this.setState( stream.readBigDecimal() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getId() );
		stream.writeString( this.getUsername() );
		stream.writeString( this.getPassword() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.localDateTimeToSqlTimestamp( this.getLastLogin() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.localDateTimeToSqlTimestamp( this.getDateInsert() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.localDateTimeToSqlTimestamp( this.getDateUpdate() ) );
		stream.writeBigDecimal( this.getState() );
	}

}
