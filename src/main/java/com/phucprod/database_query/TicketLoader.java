package com.phucprod.database_query;

import struct.ticket;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketLoader {

    public ticket loadTicket(String ticket_id, HttpSession session) throws SQLException, ClassNotFoundException {
        ticket userTicket = new ticket();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from tickets where ticket_id = ?");
            pst.setString(1, ticket_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                userTicket.ticket_id = ticket_id;
                userTicket.ticket_date = rs.getDate(4);
                userTicket.ticket_busID = rs.getInt(5);
                PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ?");
                pst_route.setInt(1, userTicket.ticket_busID);
                ResultSet rs_route = pst_route.executeQuery();
                if (rs_route.next()) {
                    PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                    PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                    PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");
                    pst_from.setInt(1, rs_route.getInt(3));
                    pst_to.setInt(1, rs_route.getInt(4));
                    pst_user.setString(1, (String) session.getAttribute("name"));
                    ResultSet rs_from = pst_from.executeQuery();
                    ResultSet rs_to = pst_to.executeQuery();
                    ResultSet rs_user = pst_user.executeQuery();
                    if (rs_from.next() && rs_to.next() && rs_user.next()) {
                        userTicket.ticket_fullname = rs_user.getString(6) + ", " + rs_user.getString(5);
                        userTicket.ticket_from = rs_from.getString(2);
                        userTicket.ticket_to = rs_to.getString(2);
                    }
                    userTicket.ticket_time = rs_route.getTime(6);
                }
            }

        }
        return userTicket;
    }
}
