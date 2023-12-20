package com.phucprod.database_query;

import struct.ticket_custom;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminTicketLoader {
    public List<ticket_custom> AdminTicketLoaderSQL(String view_ticket_dateID, String view_ticket_busID) throws ServletException, IOException{
        List<ticket_custom> ticket_list = new ArrayList<ticket_custom>();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_ticket = con.prepareStatement("select * from tickets where bus_id = ? and date_id = ?");
            pst_ticket.setString(1, view_ticket_busID);
            pst_ticket.setString(2, view_ticket_dateID);

            ResultSet rs = pst_ticket.executeQuery();
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    ticket_custom item = new ticket_custom();
                    item.ticketID = rs.getString(1);
                    PreparedStatement pst_user = con.prepareStatement("select * from users where id = ?");
                    pst_user.setInt(1, rs.getInt(2));
                    ResultSet rs_user = pst_user.executeQuery();
                    if (rs_user.next()) {
                        item.firstname = rs_user.getString(5);
                        item.lastname = rs_user.getString(6);
                    }
                    ticket_list.add(item);
                    rs.relative(1);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket_list;
    }
}
