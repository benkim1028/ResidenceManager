package backend;

import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.Vector;

/**
 * Created by GL on 2017-03-25.
 */
public class Contract {

    private static Connection con = Connector.getConnection();

    public static DefaultTableModel viewContract() {
        String query = "SELECT * FROM contract";
        return executeSearchQuery(query);
    }

    public static void deleteContract(String contractid) {
        String query = "DELETE FROM contract WHERE contractid = '" + contractid + "'";
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
            System.out.println("Contract " + contractid + " deleted");
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
    }

    public static String getStudentid(String contractid) {
        String query = "SELECT studentid FROM contract WHERE contractid = '" + contractid + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString("studentid").trim();
            }
        }catch (SQLException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(exceptionAsString);
        }
        return "";
    }

    public static boolean exists(String contractid) {
        String query = "SELECT * FROM contract WHERE contractid = '" + contractid + "'";
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
