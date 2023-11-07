package com.phucprod.history;

import struct.ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookinghistory")
public class Render_HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        String session_name = (String) session.getAttribute("name");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst = con.prepareStatement("select * from users where user_name = ?");
            pst.setString(1,session_name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int user_id = rs.getInt(1);
                PreparedStatement pst_ticket = con.prepareStatement("select * from tickets where user_id = ?");
                pst_ticket.setInt(1, user_id);
                ResultSet rs_ticket = pst_ticket.executeQuery();
                List<ticket> ticket_list = new ArrayList<ticket>();
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

                        /*DEBUG
                        out.println(userTicket.ticket_id);
                        out.println(userTicket.ticket_fullname);
                        out.println(userTicket.ticket_from);
                        out.println(userTicket.ticket_to);
                        out.println(userTicket.ticket_time);
                        out.println(userTicket.ticket_date);
                        out.println(userTicket.ticket_busID);
                        out.println(userTicket.ticket_phone);
                        */

                        rs_ticket.relative(1);

                    }
                    request.setAttribute("data", ticket_list);
                }

                dispatcher = request.getRequestDispatcher("history.jsp");
                dispatcher.forward(request,response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}