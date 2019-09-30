package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestTwoFieldKey;
import org.fugerit.java.daogen.sample.impl.helper.WrapperTestTwoFieldKey;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjTestTwoFieldKey, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjTestTwoFieldKey extends WrapperTestTwoFieldKey implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 171920118757L;

	public ObjTestTwoFieldKey( ModelTestTwoFieldKey wrapped ) {
		super( wrapped );
	}

	public ObjTestTwoFieldKey() {
		this( new HelperTestTwoFieldKey() );
	}

	public final static String SQL_TYPE_NAME = "OBJ_TEST_TWO_FIELD_KEY";

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		map.put( SQL_TYPE_NAME, ObjTestTwoFieldKey.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjTestTwoFieldKey wrap( ModelTestTwoFieldKey model ) {
		ObjTestTwoFieldKey res = null;
		if ( model instanceof ObjTestTwoFieldKey ) {
			res = (ObjTestTwoFieldKey) model;
		} else { 
			res = new ObjTestTwoFieldKey( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setIdOne( stream.readBigDecimal() );
		this.setIdTwo( stream.readBigDecimal() );
		this.setInfo( stream.readString() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getIdOne() );
		stream.writeBigDecimal( this.getIdTwo() );
		stream.writeString( this.getInfo() );
	}

}
