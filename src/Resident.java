import java.sql.*;

/**
 * Created by GL on 2017-03-25.
 */
public class Resident {

    private static Connection con = Connector.getConnection();

    public static ResultSet testMethod(String select, String from) throws SQLException{
        String query = "SELECT " + select + "FROM " + from;
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
}
