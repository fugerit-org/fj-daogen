*Page last updated on* **2020-03-05**

[Previous](index.html) | [Next](qs_config.html)

The easiest way to create the stub of Mars configuration is to dump it from the database.

Imagine this is your DDL : 

```
CREATE TABLE daogen_quickstart.person (
	id BIGINT NOT NULL,
	surname VARCHAR(256) NOT NULL,
	name VARCHAR(256) NOT NULL,
	birth_date DATE NOT NULL,
	note VARCHAR(256) NOT NULL,
	id_mother BIGINT,
	id_father BIGINT
);
```

Create a simple main class : 

```
public class DaogenDump {

	private final static Logger logger = LoggerFactory.getLogger( DaogenDump.class );
	
	public static void main( String[] args ) {
		try {
			logger.info( "daogen dump start!" );
			try ( StringWriter writer = new StringWriter() ) {
				ConnectionFactory cf = new SingleConnectionFactory( QuickstartDBHelper.newConnection() );
				Properties params = new Properties();
				params.setProperty( DaogenConfigDump.PARAM_SCHEMA , "DAOGEN_QUICKSTART" );
				List<String> tnl = new ArrayList<String>();
				tnl.add( "*" );
				Properties mapTables = new Properties();
				DaogenConfigDump.dumpConfig( cf , params, writer, tnl, mapTables );
				logger.info( "OUTPUT > \n"+writer.toString() );
			}
			logger.info( "daogen dump end!" );
		} catch (Exception e)  {
			logger.error( "Error during dump : "+e , e );
		}
	}
	
}
```

At the end the output will be something like : 

```
<daogen-config>
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