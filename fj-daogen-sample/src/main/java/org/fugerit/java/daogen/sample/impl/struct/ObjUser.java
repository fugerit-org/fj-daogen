package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelUser;
import org.fugerit.java.daogen.sample.impl.helper.HelperUser;
import org.fugerit.java.daogen.sample.impl.helper.WrapperUser;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjUser, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjUser extends WrapperUser implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 819085336101L;

	public ObjUser( ModelUser wrapped ) {
		super( wrapped );
	}

	public ObjUser() {
		this( new HelperUser() );
	}

	public final static String SQL_TYPE_NAME = "OBJ_USER";

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put( SQL_TYPE_NAME, ObjUser.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjUser wrap( ModelUser model ) {
		ObjUser res = null;
		if ( model instanceof ObjUser ) {
			res = (ObjUser) model;
		} else { 
			res = new ObjUser( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setUsername( stream.readString() );
		this.setPassword( stream.readString() );
		this.setLastLogin( stream.readTimestamp() );
		this.setDateInsert( stream.readTimestamp() );
		this.setDateUpdate( stream.readTimestamp() );
		this.setState( stream.readBigDecimal() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getId() );
		stream.writeString( this.getUsername() );
		stream.writeString( this.getPassword() );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getLastLogin() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateInsert() ) );
		stream.writeTimestamp( org.fugerit.java.core.db.daogen.SQLTypeConverter.utilDateToSqlTimestamp( this.getDateUpdate() ) );
		stream.writeBigDecimal( this.getState() );
	}

}
