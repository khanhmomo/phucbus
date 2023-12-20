package com.phucprod.database_query;

import struct.route;
import struct.user_info;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingLoader {
    public route getRouteDetails(String busId, HttpSession session) throws SQLException, ClassNotFoundException {
        route item = null;

        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from route where bus_id = ?");
            pst.setString(1, busId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                pst_from.setInt(1, rs.getInt(3));
                pst_to.setInt(1, rs.getInt(4));
                PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");
                pst_user.setString(1, (String) session.getAttribute("name"));

                ResultSet rs_from = pst_from.executeQuery();
                ResultSet rs_to = pst_to.executeQuery();
                ResultSet rs_user = pst_user.executeQuery();
                if (rs_from.next() && rs_to.next() && rs_user.next()) {
                    item = new route();
                    item.bus_id = rs.getInt(1);
                    item.price = rs.getInt(8);
                    item.start_station = rs_from.getString(2);
                    item.end_station = rs_to.getString(2);
                    item.seat_type = rs.getString(2);
                    item.start_time = rs.getTime(6);
                    item.arrive_time = rs.getTime(7);
                }
            }

            return item;
        }
    }

    public user_info getUserInfo(String userName) throws SQLException, ClassNotFoundException {
        user_info user_data = null;

        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pstUser = con.prepareStatement("select * from users where user_name = ?");
            pstUser.setString(1, userName);
            ResultSet rsUser = pstUser.executeQuery();

            if (rsUser.next()) {
                user_data = new user_info();
                user_data.user_fname = rsUser.getString(5);
                user_data.user_lname = rsUser.getString(6);
            }
        }

        return user_data;
    }
}