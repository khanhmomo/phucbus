package com.phucprod.database_update;

import com.phucprod.database_query.SQLConnection;
import struct.route;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CreateDateRoute {

    public static String CreateDateRouteSQL(String new_date_route_dateID, String new_date_route_busID) throws ServletException, IOException {
        String status = null;
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_busCheck = con.prepareStatement("select * from route where bus_id = ? ");
            pst_busCheck.setString(1,new_date_route_busID);
            ResultSet rs_check = pst_busCheck.executeQuery();

            if (rs_check.next()) {
                PreparedStatement pst_dateRouteCheck = con.prepareStatement("select * from date_route where date_id = ? and date_bus = ?");
                pst_dateRouteCheck.setString(1, new_date_route_dateID);
                pst_dateRouteCheck.setString(2, new_date_route_busID);
                ResultSet rs_date_route_check = pst_dateRouteCheck.executeQuery();
                if(rs_date_route_check.next()) {
                    status = "date_already";
                }
                else {
                    PreparedStatement pst = con.prepareStatement("insert into date_route(date_id, date_from, date_to, date_bus, date_available) values(?,?,?,?,?)");
                    pst.setString(1, new_date_route_dateID);
                    pst.setInt(2, rs_check.getInt(3));
                    pst.setInt(3, rs_check.getInt(4));
                    pst.setString(4,new_date_route_busID);
                    pst.setInt(5, rs_check.getInt(5));
                    int rowCount = pst.executeUpdate();
                    if (rowCount > 0) {
                        status = "date_success";
                    } else {
                        status = "date_failed";
                    }
                }
            } else {
                status = "date_notfound";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
