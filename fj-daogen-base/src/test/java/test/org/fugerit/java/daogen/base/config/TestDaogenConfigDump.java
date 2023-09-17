package test.org.fugerit.java.daogen.base.config;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.db.connect.ConnectionFactory;
import org.fugerit.java.core.db.connect.SingleConnectionFactory;
import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogField;
import org.fugerit.java.daogen.base.config.DaogenConfigDump;
import org.junit.Assert;
import org.junit.Test;

import test.org.fugerit.java.MemDBTestBase;

public class TestDaogenConfigDump extends MemDBTestBase {

	@Test
	public void testDumpSchema() throws Exception {
		boolean ok = false;
		try ( StringWriter writer = new StringWriter() ) {
			ConnectionFactory cf = new SingleConnectionFactory( this.getConnection() );
			Properties params = new Properties();
			params.setProperty( DaogenConfigDump.PARAM_SCHEMA , "FUGERIT" );
			List<String> tnl = new ArrayList<String>();
			tnl.add( "*" );
			DaogenConfigDump.dumpConfig( cf , params, writer, tnl );
			logger.info( "OUTPUT > \n"+writer.toString() );
			DaogenCatalogConfig config = new DaogenCatalogConfig();
			DaogenCatalogConfig.load( new ByteArrayInputStream( writer.toString().getBytes() ) , config );
			Collection<String> entityIdList = config.getIdSet();
			logger.info( "See config : "+config );
			for ( String currentEntityId : entityIdList ) {
				DaogenCatalogEntity entity = config.getListMap( currentEntityId );
				logger.info( "entity : {} ({})", entity.toString(), entity.describe() );
				for ( DaogenCatalogField field : entity ) {
					logger.info( "field : {} ({})", field.toString(), field.describe() );	
				}
				logger.info( "equals : {}, hash code {}", entity.equals( entity ), entity.hashCode() ); // coverage testing
			}
			ok = true;
		}
		Assert.assertTrue( ok );
	}
	
}
