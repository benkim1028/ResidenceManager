package backend;

import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.Vector;

/**
 * Created by GL on 2017-03-25.
 */
public class Resident {

    private static Connection con = Connector.getConnection();

    public static DefaultTableModel viewResident() {
        String query = "SELECT * FROM resident";
        return executeSearchQuery(query);
    }

    public static void updateResident(String studentid, int age, String phone, String email) throws SQLException {
        String query = "UPDATE resident SET age = '" + age + "', phone = '" + phone + "', email = '" + email + "' WHERE studentid = '" + studentid + "'";
        Statement stmt = con.createStatement();
        stmt.executeQuery(query);
        System.out.println("Resident " + studentid + " updated");
    }

    public static void deleteResident(String studentid) {
        String query = "DELETE FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            System.out.println("Resident " + studentid + " deleted");
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
    }

    public static String getName(String studentid) {
        String query = "SELECT name FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("name").trim();
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return "";
    }

    public static int getAge(String studentid) {
        String query = "SELECT age FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("age");
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return 0;
    }

    public static String getPhone(String studentid) {
        String query = "SELECT phone FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("phone").trim();
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return "";
    }

    public static String getEmail(String studentid) {
        String query = "SELECT email FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("email").trim();
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return "";
    }

    public static boolean exists(String studentid) {
        String query = "SELECT * FROM resident WHERE studentid = '" + studentid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return false;
    }

    private static DefaultTableModel executeSearchQuery(String query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            Vector<String> columnNames = new Vector<String>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNames.add(rsmd.getColumnLabel(i));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);

        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return null;
    }
}
