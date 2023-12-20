package com.phucprod.database_update;

import com.phucprod.database_query.SQLConnection;
import struct.ticket;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.UUID;

public class CreateTicket {
    public ticket CreateTicketSQL(String ticket_date, String ticket_busID, String ticket_phone, HttpSession session) throws SQLException, ClassNotFoundException {
        ticket userTicket = new ticket();
        try (Connection con = SQLConnection.getConnection()) {
            PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");
            pst_user.setString(1, (String) session.getAttribute("name"));
            ResultSet rs_user = pst_user.executeQuery();
            int ticket_userID;
            if (rs_user.next()) {
                ticket_userID = rs_user.getInt(1);
            } else {
                ticket_userID = -1;
            }


            PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ?");
            pst_route.setString(1, ticket_busID);
            ResultSet rs = pst_route.executeQuery();

            if (rs.next()) {
                PreparedStatement pst_date_route = con.prepareStatement("select * from date_route where date_bus = ? and date_id = ?");
                pst_date_route.setInt(1, Integer.parseInt(ticket_busID));
                pst_date_route.setDate(2, Date.valueOf(ticket_date));
                ResultSet rs_date_route = pst_date_route.executeQuery();

                if (rs_date_route.next()) {
                    int avai_seat = rs_date_route.getInt(5);
                    if (avai_seat > 0) {
                        avai_seat = avai_seat - 1;

                        String query = "update date_route set date_available=? where date_id=? and date_bus=?;";
                        PreparedStatement pst_seat_ud = con.prepareStatement(query);

                        // Set values to the statement
                        pst_seat_ud.setInt(1, avai_seat);
                        pst_seat_ud.setDate(2, Date.valueOf(ticket_date));
                        pst_seat_ud.setInt(3, Integer.parseInt(ticket_busID));

                        // Execute the SQL update statement
                        int rowCount = pst_seat_ud.executeUpdate();
                        if (rowCount > 0) {
                            PreparedStatement pst = con.prepareStatement("insert into tickets(ticket_id, user_id, ticket_phone, date_id, bus_id) values(?,?,?,?,?)");
                            UUID ticket_id = UUID.randomUUID();
                            pst.setString(1, String.valueOf(ticket_id));
                            pst.setInt(2, ticket_userID);
                            pst.setString(3, ticket_phone);
                            pst.setString(4, ticket_date);
                            pst.setString(5, ticket_busID);


                            pst.executeUpdate();

                            PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                            PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                            pst_from.setInt(1, rs.getInt(3));
                            pst_to.setInt(1, rs.getInt(4));

                            ResultSet rs_from = pst_from.executeQuery();
                            ResultSet rs_to = pst_to.executeQuery();

                            if (rs_from.next() && rs_to.next()) {
                                userTicket.ticket_id = String.valueOf(ticket_id);
                                userTicket.ticket_fullname = rs_user.getString(6) + ", " + rs_user.getString(5);
                                userTicket.ticket_from = rs_from.getString(2);
                                userTicket.ticket_to = rs_to.getString(2);
                                userTicket.ticket_time = rs.getTime(6);
                                userTicket.ticket_date = Date.valueOf(ticket_date);
                                userTicket.ticket_busID = Integer.parseInt(ticket_busID);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTicket;
    }
}
