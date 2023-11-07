package com.phucprod.viewticket;

import struct.ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/viewticket")
public class ViewTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        PrintWriter out =response.getWriter();
        HttpSession session = request.getSession();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
            PreparedStatement pst = con.prepareStatement("select * from tickets where ticket_id = ?");
            pst.setString(1, request.getParameter("ticket_id"));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ticket userTicket = new ticket();
                userTicket.ticket_id = request.getParameter("ticket_id");
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
                        userTicket.ticket_fullname = rs_user.getString(6)+", "+rs_user.getString(5);
                        userTicket.ticket_from = rs_from.getString(2);
                        userTicket.ticket_to = rs_to.getString(2);
                    }
                    userTicket.ticket_time = rs_route.getTime(6);
                }
                /*Debug
                out.println(userTicket.ticket_id);
                out.println(userTicket.ticket_fullname);
                out.println(userTicket.ticket_busID);
                out.println(userTicket.ticket_date);
                out.println(userTicket.ticket_time);
                out.println(userTicket.ticket_from);
                out.println(userTicket.ticket_to);
                */
                request.setAttribute("ticket_data", userTicket);
                request.setAttribute("title", "PHUCBUS TICKET");
                dispatcher = request.getRequestDispatcher("ticket.jsp");
                dispatcher.forward(request,response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}