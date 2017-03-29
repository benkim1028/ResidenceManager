package backend;

import java.sql.*;

/**
 * Created by GL on 2017-03-25.
 */
public class Resident {

    private static Connection con = Connector.getConnection();

    public static ResultSet testMethod(String select, String from, String where) throws SQLException {
        String query = "SELECT " + select + " FROM " + from;
        if (!where.isEmpty()) {
            query = query + " WHERE " + where;
        }
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
}
