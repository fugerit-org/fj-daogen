package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.List;

import org.fugerit.java.core.cfg.xml.BasicIdConfigType;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;

public class DaogenCatalogField extends BasicIdConfigType {

	public static final String ATT_ID = "id";
	public static final String ATT_SQL_TYPE = "sqlType";
	public static final String ATT_SQL_TYPE_NAME = "sqlTypeName";
	public static final String ATT_JAVA_TYPE = "javaType";
	public static final String ATT_SIZE = "size";
	public static final String ATT_NULLABLE = "nullable";
	public static final String ATT_COMMENTS = "comments";
	public static final String ATT_UDT = "udt";
	public static final String ATT_STRUCT_TYPE = "structType";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7578925751213309863L;
	
	private String comments;

	private String sqlType;
	
	private String sqlTypeName;
	
	private String javaType;
	
	private String size;
	
	private String nullable;
	
	private String unsafe;
	
	private String selectOnly;
	
	private String udt;

	private String structType;

	public boolean isUserType() {
		return BooleanUtils.isTrue( this.getUdt() );
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getSqlTypeName() {
		return sqlTypeName;
	}

	public void setSqlTypeName(String sqlTypeName) {
		this.sqlTypeName = sqlTypeName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getUnsafe() {
		return unsafe;
	}

	public void setUnsafe(String unsafe) {
		this.unsafe = unsafe;
	}

	public String getSelectOnly() {
		return selectOnly;
	}

	public void setSelectOnly(String selectOnly) {
		this.selectOnly = selectOnly;
	}

	public String getUdt() {
		return udt;
	}

	public void setUdt(String udt) {
		this.udt = udt;
	}

	public String getStructType() {
		return structType;
	}

	public void setStructType(String structType) {
		this.structType = structType;
	}
	
	public String describe() {
		 List<String> list = new ArrayList<>();
		 list.add( StringUtils.concat( ":" , ATT_SQL_TYPE, this.getSqlType() ) );
		 list.add( StringUtils.concat( ":" , ATT_SQL_TYPE_NAME, this.getSqlTypeName() ) );
		 list.add( StringUtils.concat( ":" , ATT_JAVA_TYPE, this.getJavaType() ) );
		 list.add( StringUtils.concat( ":" , ATT_SIZE, this.getSize() ) );
		 list.add( StringUtils.concat( ":" , ATT_NULLABLE, this.getNullable() ) );
		 list.add( StringUtils.concat( ":" , ATT_COMMENTS, this.getComments() ) );
		 list.add( StringUtils.concat( ":" , ATT_UDT, this.getUdt() ) );
		 list.add( StringUtils.concat( ":" , ATT_STRUCT_TYPE, this.getStructType() ) );
		 return StringUtils.concat(  ",", list );
	}
	
}
