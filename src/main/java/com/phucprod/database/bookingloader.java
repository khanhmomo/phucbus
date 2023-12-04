package com.phucprod.database;


import struct.route;
import struct.user_info;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookingloader {
    private int bus_id;
    private int price;
    private String start_station;
    private String end_station;
    private Time start_time;
    private Time arrive_time;
    private user_info user_data = new user_info();
    private String session_name;

    public bookingloader(int bus_id, int price, String start_station, String end_station, Time start_time, Time arrive_time, user_info user_data, String session_name) {
        this.bus_id = bus_id;
        this.price = price;
        this.start_station = start_station;
        this.end_station = end_station;
        this.start_time = start_time;
        this.user_data = user_data;
        this.session_name = session_name;
    }

    public static bookingloader getbookingdetailbybusid(String ticket_bus_id) {
        bookingloader bookingloader = null;

        try {
            Connection con = SQLConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from route where bus_id = ?");
            pst.setString(1, ticket_bus_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                pst_from.setInt(1, rs.getInt(3));
                pst_to.setInt(1, rs.getInt(4));
                PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");

                ResultSet rs_from = pst_from.executeQuery();
                ResultSet rs_to = pst_to.executeQuery();
                ResultSet rs_user = pst_user.executeQuery();
                if (rs_from.next() && rs_to.next() && rs_user.next()) {

                    int bus_id = rs.getInt(1);
                    int price = rs.getInt(8);
                    String start_station = rs_from.getString(2);
                    String end_station = rs_to.getString(2);
                    String seat_type = rs.getString(2);
                    Time start_time = rs.getTime(6);
                    Time arrive_time = rs.getTime(7);

                    user_info user_data = new user_info();
                    user_data.user_fname = rs_user.getString(5);
                    user_data.user_lname = rs_user.getString(6);
                    //sao z K tu
                }



            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
