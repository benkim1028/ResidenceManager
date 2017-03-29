package backend;

import backend.Connector;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * Created by GL on 2017-03-25.
 */
public class Room {

    private static Connection con = Connector.getConnection();

//    public static DefaultTableModel searchRoom(boolean roomidBox, boolean roomNumberBox, boolean occupancyBox, boolean rnameBox, boolean addressBox,
//                                               boolean typeBox, boolean accommodationBox, boolean sizeBox, boolean rateBox, boolean featuresBox) {
//        String select = createSelectString(roomidBox, roomNumberBox, occupancyBox, rnameBox, addressBox, typeBox, accommodationBox, sizeBox, rateBox, featuresBox);
//        String from = "FROM room, roomtype";
//        String query = select + from;
//        System.out.println(query);
//        return executeSearchQuery(query);
//    }

    private static String createSelectString(boolean roomidBox, boolean roomNumberBox, boolean occupancyBox, boolean rnameBox, boolean addressBox,
                                             boolean typeBox, boolean accommodationBox, boolean sizeBox, boolean rateBox, boolean featuresBox) {
        String select = "SELECT ";
        if (roomidBox) {
            select = select + "roomid, ";
        }
        if (roomNumberBox) {
            select = select + "roomnumber, ";
        }
        if (occupancyBox) {
            select = select + "occupancy, ";
        }
        if (rnameBox) {
            select = select + "rname, ";
        }
        if (addressBox) {
            select = select + "address, ";
        }
        if (typeBox) {
            select = select + "type, ";
        }
        if (accommodationBox) {
            select = select + "accommodation, ";
        }
        return "";
    }

}
