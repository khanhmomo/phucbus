package com.phucprod.database_query;

import com.phucprod.admin.viewDateRoute.AdminDateRouteServlet;
import struct.route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDateRouteLoader {
    public List<route> AdminDateRouteLoaderSQL(String view_dateroute_dateID, String view_dateroute_from, String view_dateroute_to) {
        List<route> list = new ArrayList<route>();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_ticket = con.prepareStatement("select * from date_route where date_id = ? and date_from = ? and date_to = ?");
            pst_ticket.setString(1, view_dateroute_dateID);
            pst_ticket.setString(2, view_dateroute_from);
            pst_ticket.setString(3, view_dateroute_to);
            ResultSet rs = pst_ticket.executeQuery();
            if(rs.first()) {
                while(!rs.isAfterLast()) {
                    route item = new route();
                    item.bus_id = rs.getInt(4);
                    PreparedStatement pst = con.prepareStatement("select * from route where bus_id = ?");
                    pst.setInt(1, rs.getInt(4));
                    ResultSet rs_bus = pst.executeQuery();

                    if (rs_bus.next()) {
                        item.start_time = rs_bus.getTime(6);
                        item.arrive_time = rs_bus.getTime(7);
                    }
                    item.number_of_seat = rs_bus.getInt(5) - rs.getInt(5);
                    list.add(item);
                    rs.relative(1);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
