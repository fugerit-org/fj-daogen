package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.daogen.base.config.DaogenCatalogConfig;
import org.fugerit.java.daogen.base.config.DaogenCatalogConstants;
import org.fugerit.java.daogen.base.config.DaogenCatalogEntity;
import org.fugerit.java.daogen.base.config.DaogenCatalogRelation;
import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

public class HelperUtils {

    private HelperUtils() {}

    public static void checkImportModel(DaogenCatalogConfig daogenConfig, DaogenBasicGenerator gen ) {
        if ( !daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL ).equals( daogenConfig.getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_HELPER ) ) ) {
            gen.getImportList().add( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+gen.getEntityModelName() );
            for ( DaogenCatalogRelation relation : gen.getCurrentEntity().getRelations() ) {
                DaogenCatalogEntity entityTo = gen.getDaogenConfig().getListMap( relation.getTo() );
                gen.getImportList().add( gen.getDaogenConfig().getGeneralProp( DaogenCatalogConstants.GEN_PROP_PACKAGE_MODEL )+"."+DaogenCatalogConstants.modelName( entityTo ) );
            }
        }
    }

}
