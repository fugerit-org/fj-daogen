package org.fugerit.java.daogen.base.gen.util;

import org.fugerit.java.daogen.base.gen.DaogenBasicGenerator;

import java.util.Arrays;
import java.util.Collection;

public class GenUtils {

    private GenUtils() {}

    public static void addAll(Collection<String> importList, String... toAdd ) {
        importList.addAll(Arrays.asList( toAdd ));
    }

    public static void addLogger(DaogenBasicGenerator gen, String classSimpleName ) {
        gen.getWriter().println( DaogenBasicGenerator.TAB+"private static final Logger logger = LoggerFactory.getLogger( "+ classSimpleName+".class );" );

    }

}
