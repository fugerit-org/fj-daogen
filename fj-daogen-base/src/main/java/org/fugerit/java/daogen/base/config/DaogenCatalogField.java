package org.fugerit.java.daogen.base.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
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
	public static final String ATT_VIRTUAL = "virtual";
	public static final String ATT_OPENAPI_EXAMPLE = "openapiExample";
	public static final String ATT_OPENAPI_ENUMERATION = "openapiEnumeration";
	public static final String ATT_OPENAPI_FORMAT = "openapiFormat";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7578925751213309863L;

	@Getter @Setter private String comments;

	@Getter @Setter private String sqlType;

	@Getter @Setter private String sqlTypeName;

	@Getter @Setter private String javaType;

	@Getter @Setter private String size;

	@Getter @Setter private String nullable;

	@Getter @Setter private String unsafe;

	@Getter @Setter private String selectOnly;

	@Getter @Setter private String udt;

	@Getter @Setter private String structType;

	@Getter @Setter private String virtual;

	@Getter @Setter private String openapiExample;

	@Getter @Setter private String openapiEnumeration;

	@Getter @Setter private String openapiFormat;

	public boolean isUserType() {
		return BooleanUtils.isTrue( this.getUdt() );
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
		 list.add( StringUtils.concat( ":" , ATT_VIRTUAL, this.getVirtual() ) );
		 list.add( StringUtils.concat( ":" , ATT_OPENAPI_EXAMPLE, this.getOpenapiExample() ) );
		 list.add( StringUtils.concat( ":" , ATT_OPENAPI_ENUMERATION, this.getOpenapiEnumeration() ) );
		 list.add( StringUtils.concat( ":" , ATT_OPENAPI_FORMAT, this.getOpenapiFormat() ) );
		 return StringUtils.concat(  ",", list );
	}
	
}
