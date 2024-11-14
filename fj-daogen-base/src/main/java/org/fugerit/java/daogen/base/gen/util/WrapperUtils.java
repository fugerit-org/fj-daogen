package org.fugerit.java.daogen.base.gen.util;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.javagen.GeneratorNameHelper;
import org.fugerit.java.core.lang.helpers.BooleanUtils;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.daogen.base.config.*;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

import java.io.IOException;
import java.util.function.Function;

@Slf4j
public class WrapperUtils {

    private WrapperUtils() {}

    public static void init(DaogenCatalogConfig daogenConfig, DaogenCatalogEntity entity, DaogenBasicGenerator gen ) throws ConfigException {
        String daoWrapperNgMode = daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE, DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_DISABLED );
        log.info( "{} -> {}", DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE, daoWrapperNgMode );
        if ( DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_DISABLED.equalsIgnoreCase( daoWrapperNgMode ) ) {
            gen.setClassBaseWrapper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_WRAPPER_BASE, gen.getImportList() ) );
            gen.setExtendsClass( gen.getClassBaseWrapper()+"<"+gen.getEntityModelName()+">" );
        } else if ( DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_ENABLED.equalsIgnoreCase( daoWrapperNgMode ) ) {
            String wrapperNgClass = DaogenClassConfigHelper.findClassConfigProp( daogenConfig, DaogenClassConfigHelper.DAO_WRAPPER_NG_BASE, DaogenClassConfigHelper.DAO_BASE_CLASS );
            if ( StringUtils.isNotEmpty( wrapperNgClass ) ) {
                gen.setClassBaseWrapper( DaogenClassConfigHelper.addImport( daogenConfig , DaogenClassConfigHelper.DAO_WRAPPER_NG_BASE, gen.getImportList() ) );
                gen.setExtendsClass( gen.getClassBaseWrapper()+"<"+gen.getEntityModelName()+">" );
            }
        } else {
            throw new ConfigException( "Invalid "+DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE+" parameter : "+daoWrapperNgMode );
        }
        gen.getImportList().add( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+gen.getEntityModelName() );
        gen.setImplementsInterface( gen.getEntityModelName() );
        for ( DaogenCatalogRelation relation : gen.getCurrentEntity().getRelations() ) {
            DaogenCatalogEntity entityTo = gen.getDaogenConfig().getListMap( relation.getTo() );
            gen.getImportList().add( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+DaogenCatalogConstants.modelName( entityTo ) );
        }
    }

    public static void generateRelations(DaogenBasicGenerator gen ) {
        if ( !gen.getCurrentEntity().getRelations().isEmpty() ) {
            gen.getWriter().println( DaogenBasicGenerator.TAB+"/*" );
            gen.getWriter().println( DaogenBasicGenerator.TAB+" * fields generated for relations " );
            gen.getWriter().println( DaogenBasicGenerator.TAB+" */" );
            gen.getWriter().println();
            for ( DaogenCatalogRelation relation : gen.getCurrentEntity().getRelations() ) {
                DaogenCatalogEntity entityTo = gen.getDaogenConfig().getListMap( relation.getTo() );
                String baseType = DaogenCatalogConstants.modelName( entityTo );
                String className = GeneratorNameHelper.toClassName( relation.getName() );
                if ( DaogenCatalogRelation.MODE_MANY.equalsIgnoreCase( relation.getMode() ) ) {
                    baseType = "java.util.List<"+baseType+">";
                }
                // metodo set
                gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.AT_OVERRIDE );
                gen.getWriter().println( DaogenBasicGenerator.TAB+"public void set"+className+"( "+baseType+" value ) {" );
                gen.getWriter().println( DaogenBasicGenerator.TAB_2+"this.unwrapModel().set"+className+"( value );" );
                gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
                gen.getWriter().println();
                // metodo get
                gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.AT_OVERRIDE );
                gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.PUBLIC_SPACE_LIT+baseType+" get"+className+"() {" );
                gen.getWriter().println( DaogenBasicGenerator.TAB_2+"return this.unwrapModel().get"+className+"();" );
                gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
                gen.getWriter().println();
            }
        }
    }

    public static void generateBody( DaogenBasicGenerator gen, Function<DaogenCatalogField, String> getterDecorator ) throws IOException {
        String daoWrapperNgMode = gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE, DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_DISABLED );
        gen.generateSerial( DaogenCatalogConstants.GEN_PROP_DAO_WRAPPER_NG_MODE_DISABLED.equalsIgnoreCase( daoWrapperNgMode ) );
        gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.PUBLIC_SPACE_LIT+gen.getEntityWrapperName()+"( "+gen.getEntityModelName()+" wrapped ) {" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"super( wrapped );" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
        gen.getWriter().println();
        gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.PUBLIC_SPACE_LIT+gen.getEntityModelName()+" unwrap( "+gen.getEntityWrapperName()+" wrapper ) {" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+""+gen.getEntityModelName()+" res = wrapper;" );
        if ( gen.isJdkVersionAtLeast( DaogenCatalogConstants.GEN_PROP_JDK_TARGET_VERSION_17 ) ) {
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"while ( res instanceof "+gen.getEntityWrapperName()+" wrappedinstance ) { " );
            gen.getWriter().println( DaogenBasicGenerator.TAB_3+"res = wrappedinstance.unwrapModel();" );
        } else {
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"while ( res instanceof "+gen.getEntityWrapperName()+" ) { " );
            gen.getWriter().println( DaogenBasicGenerator.TAB_3+"res = (("+gen.getEntityWrapperName()+")res).unwrapModel();" );
        }
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"}" );
        gen.getWriter().println( DaogenBasicGenerator.TAB_2+"return res;" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
        gen.getWriter().println();

        boolean relationLast = "true".equalsIgnoreCase( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_RELATIONS_LAST ) );
        if ( !relationLast ) {
            generateRelations( gen );
        }

        gen.getWriter().println( DaogenBasicGenerator.TAB+"/*" );
        gen.getWriter().println( DaogenBasicGenerator.TAB+" * fields generated for entity attributes " );
        gen.getWriter().println( DaogenBasicGenerator.TAB+" */" );
        for ( DaogenCatalogField field : gen.getCurrentEntity().getAllFields() ) {
            // property
            String javaSuffix = GeneratorNameHelper.toClassName( field.getId() );
            String realJavaType = gen.getDaogenConfig().getTypeMapper().mapForModel( field );
            // metodo set
            gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.AT_OVERRIDE );
            gen.getWriter().println( DaogenBasicGenerator.TAB+"public void set"+javaSuffix+"( "+realJavaType+" value ) {" );
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"this.unwrapModel().set"+javaSuffix+"( value );" );
            gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
            gen.getWriter().println();
            // metodo get
            String decorateGet = getterDecorator.apply( field );
            if ( StringUtils.isNotEmpty( decorateGet ) ) {
                gen.getWriter().println( DaogenBasicGenerator.TAB+decorateGet );
            }
            gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.AT_OVERRIDE );
            gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.PUBLIC_SPACE_LIT+realJavaType+" get"+javaSuffix+"() {" );
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"return this.unwrapModel().get"+javaSuffix+"();" );
            gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
            gen.getWriter().println();
        }

        if ( relationLast ) {
            generateRelations( gen );
        }

        if ( BooleanUtils.isTrue( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_CHECK_EMPTY_INTERFACE ) ) ) {
            gen.getWriter().println( DaogenBasicGenerator.TAB+DaogenBasicGenerator.AT_OVERRIDE );
            gen.getWriter().println( DaogenBasicGenerator.TAB+"public boolean isEmpty() {" );
            gen.getWriter().println( DaogenBasicGenerator.TAB_2+"return this.unwrapModel().isEmpty();" );
            gen.getWriter().println( DaogenBasicGenerator.TAB+"}" );
            gen.getWriter().println();
        }
    }

}
