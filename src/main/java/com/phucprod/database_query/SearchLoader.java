package com.phucprod.database_query;

import struct.route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchLoader {
    public List<route> ListRoute(int se_from, int se_to, String date, String type) throws SQLException, ClassNotFoundException {
        List<route> list = new ArrayList<>();
        try(Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from date_route where date_id = ? and date_from = ? and date_to = ?");
            pst.setString(1, date);
            pst.setInt(2, se_from);
            pst.setInt(3, se_to);
            ResultSet rs = pst.executeQuery();
            if (rs.first()){
                List<route> list_route = new ArrayList<route>();
                while (!rs.isAfterLast()) {

                    PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                    PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                    PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ? and seat_type = ?");
                    pst_from.setInt(1, se_from);
                    pst_to.setInt(1, se_to);
                    pst_route.setInt(1, rs.getInt(4));
                    pst_route.setString(2, type);
                    ResultSet rs_from = pst_from.executeQuery();
                    ResultSet rs_to = pst_to.executeQuery();
                    ResultSet rs_route = pst_route.executeQuery();
                    if (rs_from.next() && rs_to.next() && rs_route.next() && rs.getInt(5) > 0) {
                        route item = new route();
                        item.bus_id = rs.getInt(4);
                        item.price = rs_route.getInt(8);
                        item.start_station = rs_from.getString(2);
                        item.end_station = rs_to.getString(2);
                        item.seat_type = rs_route.getString(2);
                        item.start_time = rs_route.getTime(6);
                        item.arrive_time = rs_route.getTime(7);
                        list_route.add(item);
                    }

                    else {
                        return null;
                    }
                    rs.relative(1);
                }
                return list_route;
            }
        }
        return null;
    }
}
