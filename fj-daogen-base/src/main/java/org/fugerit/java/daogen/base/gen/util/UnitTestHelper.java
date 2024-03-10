package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.daogen.base.config.*;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

import java.io.PrintWriter;
import java.util.ArrayList;

public class UnitTestHelper {

    private UnitTestHelper() {}

    public static void genBodyBasicTest( DaogenBasicGenerator gen, int junitLevel ) {
        // create / print test
        String assertionClass = "Assertions";
        String appendPublic = "";
        if ( junitLevel < 5 ) {
            assertionClass = "Assert";
            appendPublic = "public";
            GenUtils.addLogger( gen, DaogenCatalogConstants.junit4ModelName( gen.getCurrentEntity() ) );
        } else {
            GenUtils.addLogger( gen, DaogenCatalogConstants.junit5ModelName( gen.getCurrentEntity() ) );
        }
        // read all fields
        UnitTestHelper.createSampleEntityPrintAllMethod( gen );
        // creates a new instance
        UnitTestHelper.createSampleEntityInstanceMethod( gen, assertionClass );
        gen.getWriter().println( DaogenBasicGenerator.TAB+"@Test" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+appendPublic+" void testJUnit"+junitLevel+"Model"+GeneratorNameHelper.toClassName( gen.getCurrentEntity().getName() )+"() { " );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+gen.getEntityModelName()+" current = this.newInstance();" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"this.printAll( current );" );
        String daoHelperNgMode = gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE, DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE_DISABLED );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"logger.info( \"current toString() : {}\", current );" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"logger.info( \"current isEmpty() : {}\", current.isEmpty() );" );
        if ( DaogenCatalogConstants.GEN_PROP_DAO_HELPER_NG_MODE_DISABLED.equalsIgnoreCase( daoHelperNgMode ) ) {
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"org.fugerit.java.core.function.SafeFunction.apply( () -> org.fugerit.java.core.io.ObjectIO.fullSerializationTest( current ) );" );
        }
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+assertionClass+".assertNotNull( current );" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
        gen.getWriter().println();
    }

    private static void createSampleEntityPrintAllMethod( DaogenBasicGenerator gen ) {
        gen.getWriter().println( DaogenBasicGenerator.TAB+"public void printAll( "+gen.getEntityModelName()+" current ) { " );
        for ( DaogenCatalogField field : gen.getCurrentEntity() ) {
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+" logger.info( \""+field.getId()+"-> {}\", current.get"+GeneratorNameHelper.toClassName( field.getId() )+"() );" );
        }
        for ( DaogenCatalogRelation relation : gen.getCurrentEntity().getRelations() ) {
            String className = GeneratorNameHelper.toClassName( relation.getName() );
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+" logger.info( \"relation : "+relation.getId()+"-> {}\", current.get"+className+"() );" );
        }
        gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
        gen.getWriter().println();
    }

    private static void createSampleEntityInstanceMethod( DaogenBasicGenerator gen, String assertionClass ) {
        // creates a new instance
        gen.getWriter().println( DaogenBasicGenerator.TAB+"public "+gen.getEntityModelName()+" newInstance() { " );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+""+gen.getEntityWrapperName()+" current = new "+gen.getEntityWrapperName()+"( new "+gen.getEntityHelperName()+"() );" );
        for ( DaogenCatalogField field : gen.getCurrentEntity() ) {
            handleFieldNewInstance( gen.getDaogenConfig(), field, gen.getWriter() );
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+assertionClass+".assertFalse( current.isEmpty() );" );
        }
        for ( DaogenCatalogRelation relation : gen.getCurrentEntity().getRelations() ) {
            DaogenCatalogEntity entityTo = gen.getDaogenConfig().getListMap( relation.getTo() );
            String className = GeneratorNameHelper.toClassName( relation.getName() );
            String baseType = gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER )+"."+DaogenCatalogConstants.helperName( entityTo );
            if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
                baseType = ArrayList.class.getName()+"<"+gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+DaogenCatalogConstants.modelName( entityTo )+">";
            }
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"current.set"+className+"( new "+baseType+"() );" );
        }
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"logger.info( \"unwrap :  {}\", current.unwrap( current ) );" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"return current;" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
    }

    private static void handleFieldNewInstance(DaogenCatalogConfig config, DaogenCatalogField field, PrintWriter writer ) {
        String columnType = config.getTypeMapper().mapForModel( field );
        StringBuilder line = new StringBuilder();
        line.append( DaogenBasicGenerator.TAB_2 );
        line.append( "current.set" );
        line.append(  GeneratorNameHelper.toClassName( field.getId() ) );
        line.append( "(" );
        if ( columnType.equalsIgnoreCase( "java.lang.String" ) ) {
            line.append( "\"1\"" );
        } else 	if ( columnType.equalsIgnoreCase( "java.math.BigDecimal" ) ) {
            line.append( "new java.math.BigDecimal( \"1\" )" );
        } else 	if ( columnType.equalsIgnoreCase( "java.lang.Integer" ) ) {
            line.append( "1" );
        } else 	if ( columnType.equalsIgnoreCase( "java.sql.Date" ) ) {
            line.append( "new java.sql.Date( System.currentTimeMillis )" );
        } else 	if ( columnType.equalsIgnoreCase( "java.sql.Timestamp" ) ) {
            line.append( "new java.sql.Timestamp( System.currentTimeMillis )" );
        } else 	if ( columnType.equalsIgnoreCase( "java.util.Date" ) ) {
            line.append( "new java.util.Date()" );
        } else {
            line.append( "null" );
        }
        line.append( ");" );
        writer.println( line.toString() );
    }

}
