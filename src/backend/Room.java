package backend;

import backend.Connector;

import javax.swing.*;
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
                                               boolean typeBox, boolean accommodationBox, boolean sizeBox, boolean rateBox, boolean featuresBox,
                                               String roomidText, int occupancyDrop, int occupancyVal, int typeDrop,
                                               int accommodationDrop, int accommodationVal, int sizeDrop, int sizeVal, int rateDrop, int rateVal,
                                               boolean kitchenBox, boolean bathroomBox, boolean loungeBox) {
        String select = createSelectString(roomidBox, roomNumberBox, occupancyBox, rnameBox, addressBox, typeBox, accommodationBox, sizeBox, rateBox, featuresBox);
        String from = "FROM room, roomtype ";
        String where = createWhereString(roomidText, occupancyDrop, occupancyVal, typeDrop, accommodationDrop, accommodationVal, sizeDrop, sizeVal, rateDrop, rateVal, kitchenBox, bathroomBox, loungeBox);
        String query = select + from + where;
        System.out.println(query);
        return executeSearchQuery(query);
    }

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

    private static String createWhereString(String roomidText, int occupancyDrop, int occupancyVal, int typeDrop,
                                     int accommodationDrop, int accommodationVal, int sizeDrop, int sizeVal, int rateDrop, int rateVal,
                                     boolean kitchenBox, boolean bathroomBox, boolean loungeBox) {
        String where = "WHERE room.type = roomtype.type AND ";
        if (!roomidText.equals("")) {
            where = where + "UPPER(room.roomid) LIKE UPPER('%" + roomidText + "%') AND ";
        }
        if (occupancyVal != -1) {
            where = where + "room.occupancy " + findSymbolString(occupancyDrop) + " " + occupancyVal + " AND ";
        }
        if (typeDrop == 1) {
            where = where + "room.type = 'One Bedroom'";
        }
        else if (typeDrop == 2) {
            where = where + "room.type = 'Two Bedrooms Suite'";
        }
        else if (typeDrop == 3) {
            where = where + "room.type = 'Four Bedrooms Suite'";
        }
        else if (typeDrop == 4) {
            where = where + "room.type = 'Six Bedrooms Suite'";
        }
        else if (typeDrop == 5) {
            where = where + "room.type = 'Studio'";
        }
        else {
            if (accommodationVal != -1) {
                where = where + "roomtype.accommodation " + findSymbolString(accommodationDrop) + " " + accommodationVal + " AND ";
            }
            if (sizeVal != -1) {
                where = where + "roomtype.rsize " + findSymbolString(sizeDrop) + " " + sizeVal + " AND ";
            }
            if (rateVal != -1) {
                where = where + "roomtype.rate " + findSymbolString(rateDrop) + " " + rateVal + " AND ";
            }
            if (kitchenBox) {
                where = where + "features LIKE '%kitchen%' AND ";
            }
            if (bathroomBox) {
                where = where + "features LIKE '%bathroom%' AND ";
            }
            if (loungeBox) {
                where = where + "features LIKE '%lounge%' AND ";
            }
            where = where.substring(0, where.length()-5);
        }
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
