*Page last updated on* **2020-03-05**

[Previous](qs_dump.html) | [Next](qs_notes.html)

Complete the configuration of daogen-config.xml by adding general attributes of root element : 

```
<daogen-config
	base-src-folder="../fj-daogen-quickstart"
	database-type="hsqldb"
	gen-version="1.0.0"
	gen-author="fugerit"
	src-main-java="src/main/java"
	src-test-java="src/test/java"
	src-main-resources="src/main/resources"
	src-test-resources="src/test/resources"
	src-doc-openapi="src/main/doc/openapi_rest"
	src-helpers="SRC-MAIN-JAVA"
	relations-last="true"
	class-config="cl://config/daogen_default_class_config.xml"
	type-mapper="org.fugerit.java.daogen.base.config.DaogenTypeMapper"
	type-map-config="cl://config/daogen_default_type_mapping.xml"
	generator-catalog="cl://config/default-generator-catalog.xml"
	default-column-time-insert="DATE_INSERT"
	default-column-time-update="DATE_UPDATE"
	struct_prefix="OBJ_"
	package-model="org.fugerit.java.daogen.quickstart.def.model"
	package-helper="org.fugerit.java.daogen.quickstart.impl.helper"
	package-struct="org.fugerit.java.daogen.quickstart.impl.struct"
	package-rse="org.fugerit.java.daogen.quickstart.impl.rse"
	package-facade-def="org.fugerit.java.daogen.quickstart.def.facade"
	package-facade-data-impl="org.fugerit.java.daogen.quickstart.impl.facade.data"
	package-rest-load="org.fugerit.java.daogen.quickstart.impl.rest.load"
	base-rest-service="org.fugerit.java.daogen.quickstart.config.ServiceProviderHelper"
	factory-def="org.fugerit.java.daogen.quickstart.def.facade.QuickstartLogicFacade"
	factory-data-impl="org.fugerit.java.daogen.quickstart.impl.facade.data.QuickstartDataLogicFacade"
	default-sequence="quickstart_id"
	openapi_host="http://localhost:8080"
	openapi_path="/fj-daogen-quickstart/jax-rs"
	>
	
  <entity catalog="PUBLIC" comments="People data" foreignKeys="PUBLIC.DAOGEN_QUICKSTART.PERSON,PUBLIC.DAOGEN_QUICKSTART.PERSON" id="PUBLIC.DAOGEN_QUICKSTART.PERSON" name="PERSON" primaryKey="ID" schema="DAOGEN_QUICKSTART">
    <field comments="Person id" id="ID" javaType="java.lang.Long" nullable="no" size="0" sqlType="-5" sqlTypeName="BIGINT"/>
    <field comments="Person surname" id="SURNAME" javaType="java.lang.String" nullable="no" size="256" sqlType="12" sqlTypeName="VARCHAR"/>
    <field comments="Person name" id="NAME" javaType="java.lang.String" nullable="no" size="256" sqlType="12" sqlTypeName="VARCHAR"/>
    <field comments="Person birth date" id="BIRTH_DATE" javaType="java.sql.Date" nullable="no" size="0" sqlType="91" sqlTypeName="DATE"/>
    <field comments="Notes on persone" id="NOTE" javaType="java.lang.String" nullable="no" size="256" sqlType="12" sqlTypeName="VARCHAR"/>
    <field comments="Reference to mother" id="ID_MOTHER" javaType="java.lang.Long" nullable="yes" size="0" sqlType="-5" sqlTypeName="BIGINT"/>
    <field comments="Reference to fater" id="ID_FATHER" javaType="java.lang.Long" nullable="yes" size="0" sqlType="-5" sqlTypeName="BIGINT"/>
  </entity>
	
</daogen-config>
```

And run it with another simple main class : 

```
public class DaogenRun {

	private final static Logger logger = LoggerFactory.getLogger( DaogenRun.class );
	
	public static void main( String[] args ) {
		try {
			try ( InputStream is = ClassHelper.loadFromDefaultClassLoader( "daogen-config.xml" ) ) {
				logger.info( "DAOGEN start!" );
				DaogenFacade.generate( is );
				logger.info( "DAOGEN end!" );
			}
		} catch (Exception e)  {
			logger.error( "Error during dump : "+e , e );
		}
	}
	
}
```

Generated files are located in the specified folders ("src-main-java" etc.)
