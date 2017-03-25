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
            String query = "SELECT * FROM school";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("sname");
                System.out.println(id);
            }
            if (stmt != null) {
                stmt.close();
            }


            // Verify the connection is open. Then close it.
            con.close();

        } catch (SQLException e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        System.out.println(exceptionAsString);
        System.out.println("---------------------------------");
        }
    }
}
