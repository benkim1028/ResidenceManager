import java.sql.*;
import java.io.*;

/**
 * Created by GL on 2017-03-25.
 */
public class test {
    private static Connection con;

    public static void main(String args[]) {
        try {
            con = Connector.getConnection();
            ResultSet rs = Resident.testMethod("*", "resident");
            while (rs.next()) {
                String id = rs.getString("name");
                System.out.println(id);
            }
            con.close();

        } catch (SQLException e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        System.out.println(exceptionAsString);
        }
    }
}
