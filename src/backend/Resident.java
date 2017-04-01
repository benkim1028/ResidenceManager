package backend;

import javax.swing.*;
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

    public static DefaultTableModel searchResident(boolean studentidbox, boolean namebox, boolean agebox, boolean phonebox, boolean emailbox, boolean roomidbox) {
        String select = createSelectString(studentidbox, namebox, agebox, phonebox, emailbox, roomidbox);
        String from = "FROM resident ";
        String query = select + from;
        System.out.println(query);
        return executeSearchQuery(query);
    }

    public static DefaultTableModel searchResident(boolean studentidbox, boolean namebox, boolean agebox, boolean phonebox, boolean emailbox, boolean roomidbox,
                                                   String studentidText, String nameText, int ageDrop, int ageVal, String phoneText, String emailText, String roomidText) {
        String select = createSelectString(studentidbox, namebox, agebox, phonebox, emailbox, roomidbox);
        String from = "FROM resident ";
        String where = createWhereString(studentidText, nameText, ageDrop, ageVal, phoneText, emailText, roomidText);
        String query = select + from + where;
        System.out.println(query);
        return executeSearchQuery(query);
    }

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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private static String createSelectString(boolean studentidbox, boolean namebox, boolean agebox, boolean phonebox, boolean emailbox, boolean roomidbox) {
        String select = "SELECT ";
        if (studentidbox) {
            select = select + "studentid, ";
        }
        if (namebox) {
            select = select + "name, ";
        }
        if (agebox) {
            select = select + "age, ";
        }
        if (phonebox) {
            select = select + "phone, ";
        }
        if (emailbox) {
            select = select + "email, ";
        }
        if (roomidbox) {
            select = select + "roomid, ";
        }
        select = select.substring(0, select.length() - 2) + " ";
        return select;
    }

    private static String createWhereString(String studentidText, String nameText, int ageDrop, int ageVal, String phoneText, String emailText, String roomidText) {
        String where = "WHERE ";
        if (!studentidText.equals("")) {
            where = where + "studentid LIKE '%" + studentidText + "%' AND ";
        }
        if (!nameText.equals("")) {
            where = where + "name LIKE '%" + nameText + "%' AND ";
        }
        if (ageVal != -1) {
            where = where + "age " + findSymbolString(ageDrop) + " '" + ageVal + "' AND ";
        }
        if (!emailText.equals("")) {
            where = where + "email LIKE '%" + emailText + "%' AND ";
        }
        if (!phoneText.equals("")) {
            where = where + "phone LIKE '%" + phoneText + "%' AND ";
        }
        if (!roomidText.equals("")) {
            where = where + "roomid LIKE '%" + roomidText + "%' AND ";
        }
        where = where.substring(0, where.length()-5);
        return where;
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
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static String findSymbolString(int dropIndex) {
        if (dropIndex == 0) {
            return "<";
        }
        else if (dropIndex == 1) {
            return ">";
        }
        else if (dropIndex == 2) {
            return "=";
        }
        else if (dropIndex == 3) {
            return "<=";
        }
        else {
            return ">=";
        }
    }
}
