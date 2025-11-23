package org.fugerit.java.daogen.sample;

import org.fugerit.java.core.db.daogen.DAOContext;
import org.fugerit.java.core.db.daogen.StatementHelperLibrary;

import java.sql.PreparedStatement;
import java.util.function.BiFunction;

public class SampleStatementHelper {

    private SampleStatementHelper() {}

    public static BiFunction<PreparedStatement, DAOContext, PreparedStatement> SAMPLE_STATEMENET_HELPER =
            StatementHelperLibrary.hewHelperSafeSilent(
                    StatementHelperLibrary.newHelperPipeline(
                    StatementHelperLibrary.newHelperWithQueryTimeout( 30 ),
                    StatementHelperLibrary.newHelperWithFetchSize( null ) )
            );

}
