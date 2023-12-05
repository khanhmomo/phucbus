package com.phucprod.database_update;

import com.phucprod.database_query.SQLConnection;
import struct.route;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CreateRoute {
    public static boolean CreateRouteSQL(ArrayList<String> data) throws ServletException, IOException {
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("insert into route(bus_id, seat_type, start_station, end_station, number_of_seat, start_time, arrive_time, price) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, String.valueOf(data.get(0)));
            pst.setString(2, data.get(4));
            pst.setString(3, data.get(1));
            pst.setString(4, data.get(2));
            pst.setString(5, data.get(3));
            pst.setString(6, data.get(5));
            pst.setString(7, data.get(6));
            pst.setString(8, data.get(7));
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
