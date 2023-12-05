package com.phucprod.database_query;

import struct.route;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminRouteLoader {
    public List<route> AdminRouteLoaderSQL(String view_route_from, String view_route_to) throws ServletException, IOException {
        List<route> list = new ArrayList<route>();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_ticket = con.prepareStatement("select * from route where start_station = ? and end_station = ?");
            pst_ticket.setString(1, view_route_from);
            pst_ticket.setString(2, view_route_to);
            ResultSet rs = pst_ticket.executeQuery();

            if(rs.first()) {
                while(!rs.isAfterLast()) {
                    route item = new route();
                    item.bus_id = rs.getInt(1);
                    item.start_time = rs.getTime(6);
                    item.arrive_time = rs.getTime(7);
                    item.seat_type = rs.getString(2);
                    item.price = rs.getInt(8);
                    list.add(item);
                    rs.relative(1);
                }
            }
        }
            catch (Exception e){
                e.printStackTrace();
            }
        return list;
    }
}
