package org.fugerit.java.daogen.sample.impl.struct;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.fugerit.java.core.db.daogen.StructMapper;
import org.fugerit.java.daogen.sample.def.model.ModelTestColumnRename;
import org.fugerit.java.daogen.sample.impl.helper.HelperTestColumnRename;
import org.fugerit.java.daogen.sample.impl.helper.WrapperTestColumnRename;

// custom import start ( code above here will be overwritten )
// custom import end ( code below here will be overwritten )

/**
 * ObjTestColumnRename, version : 1.0.0
 *
 * author: fugerit
 *
 * warning!: auto generated object, insert custom code only between comments :
 * // custom code start ( code above here will be overwritten )
 * // custom code end ( code below here will be overwritten )
 */
public class ObjTestColumnRename extends WrapperTestColumnRename implements SQLData, StructMapper {

	// custom code start ( code above here will be overwritten )
	// custom code end ( code below here will be overwritten )

	private static final long serialVersionUID = 464830272364L;

	public ObjTestColumnRename( ModelTestColumnRename wrapped ) {
		super( wrapped );
	}

	public ObjTestColumnRename() {
		this( new HelperTestColumnRename() );
	}

	public static final String SQL_TYPE_NAME = "OBJ_TEST_COLUMN_RENAME";

	public static final ObjTestColumnRename MAPPER = new ObjTestColumnRename();

	@Override
	public Map<String, Class<?>> newTypeMapper() throws SQLException {
		Map<String, Class<?>> map = new HashMap<>();
		map.put( SQL_TYPE_NAME, ObjTestColumnRename.class );
		return map;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return SQL_TYPE_NAME;
	}

	public static ObjTestColumnRename wrap( ModelTestColumnRename model ) {
		ObjTestColumnRename res = null;
		if ( model instanceof ObjTestColumnRename ) {
			res = (ObjTestColumnRename) model;
		} else { 
			res = new ObjTestColumnRename( model );
		}
		return res;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		this.setId( stream.readBigDecimal() );
		this.setRenamedFieldOne( stream.readString() );
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeBigDecimal( this.getId() );
		stream.writeString( this.getRenamedFieldOne() );
	}

}
