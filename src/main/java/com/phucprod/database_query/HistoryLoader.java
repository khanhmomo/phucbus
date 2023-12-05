package com.phucprod.database_query;

import struct.ticket;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryLoader {

    public List<ticket> getTicketList(HttpSession session) throws SQLException, ClassNotFoundException {
        List<ticket> ticket_list = new ArrayList<ticket>();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from users where user_name = ?");
            pst.setString(1, (String) session.getAttribute("name"));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt(1);
                PreparedStatement pst_ticket = con.prepareStatement("select * from tickets where user_id = ?");
                pst_ticket.setInt(1, user_id);
                ResultSet rs_ticket = pst_ticket.executeQuery();

                if (rs_ticket.first()){
                    while (!rs_ticket.isAfterLast()) {
                        ticket userTicket = new ticket();
                        userTicket.ticket_id = rs_ticket.getString(1);
                        userTicket.ticket_fullname = rs.getString(6)+", "+rs.getString(5);
                        PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ?");
                        pst_route.setInt(1, rs_ticket.getInt(5));
                        ResultSet rs_route = pst_route.executeQuery();

                        if (rs_route.next()) {
                            int from_ID = rs_route.getInt(3);
                            int to_ID = rs_route.getInt(4);
                            PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                            PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                            pst_from.setInt(1, from_ID);
                            pst_to.setInt(1, to_ID);
                            ResultSet rs_from = pst_from.executeQuery();
                            ResultSet rs_to = pst_to.executeQuery();
                            if (rs_from.next() && rs_to.next()) {
                                userTicket.ticket_from = rs_from.getString(2);
                                userTicket.ticket_to = rs_to.getString(2);
                            }
                        }

                        userTicket.ticket_time = rs_route.getTime(6);
                        userTicket.ticket_date = rs_ticket.getDate(4);
                        userTicket.ticket_busID = rs_ticket.getInt(5);
                        userTicket.ticket_phone = rs_ticket.getString(3);
                        ticket_list.add(userTicket);

                        rs_ticket.relative(1);

                    }
                }

            }
        }
        return ticket_list;
    }
}
