package backend;

import backend.Connector;

import javax.swing.table.DefaultTableModel;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.Vector;

/**
 * Created by GL on 2017-03-25.
 */
public class Room {

    private static Connection con = Connector.getConnection();

    public static DefaultTableModel searchRoom(boolean roomidBox, boolean roomNumberBox, boolean occupancyBox, boolean rnameBox, boolean addressBox,
                                               boolean typeBox, boolean accommodationBox, boolean sizeBox, boolean rateBox, boolean featuresBox) {
        String select = createSelectString(roomidBox, roomNumberBox, occupancyBox, rnameBox, addressBox, typeBox, accommodationBox, sizeBox, rateBox, featuresBox);
        String from = "FROM room, roomtype ";
        String where = "WHERE room.type = roomtype.type";
        String query = select + from + where;
        System.out.println(query);
        return executeSearchQuery(query);
    }

    private static String createSelectString(boolean roomidBox, boolean roomNumberBox, boolean occupancyBox, boolean rnameBox, boolean addressBox,
                                             boolean typeBox, boolean accommodationBox, boolean sizeBox, boolean rateBox, boolean featuresBox) {
        String select = "SELECT ";
        if (roomidBox) {
            select = select + "room.roomid, ";
        }
        if (roomNumberBox) {
            select = select + "room.roomnumber, ";
        }
        if (occupancyBox) {
            select = select + "room.occupancy, ";
        }
        if (rnameBox) {
            select = select + "room.rname, ";
        }
        if (addressBox) {
            select = select + "room.address, ";
        }
        if (typeBox) {
            select = select + "room.type, ";
        }
        if (accommodationBox) {
            select = select + "roomtype.accommodation, ";
        }
        if (sizeBox) {
            select = select + "roomtype.rsize, ";
        }
        if (rateBox) {
            select = select + "roomtype.rate, ";
        }
        if (featuresBox) {
            select = select + "roomtype.features, ";
        }
        select = select.substring(0, select.length() - 2) + " ";
        return select;
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
