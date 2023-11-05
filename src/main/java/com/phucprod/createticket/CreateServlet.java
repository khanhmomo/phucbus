package com.phucprod.createticket;

import struct.ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Objects;
import java.util.UUID;

@WebServlet("/ticket")
public class CreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ticket_date = request.getParameter("date");
        String ticket_busID = request.getParameter("bus_id");
        String ticket_phone = request.getParameter("phone");

        Connection con = null;
        RequestDispatcher dispatcher = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");
            pst_user.setString(1, (String) session.getAttribute("name"));
            ResultSet rs_user = pst_user.executeQuery();
            int ticket_userID;
            if (rs_user.next()) {
                ticket_userID = rs_user.getInt(1);
            }
            else {
                ticket_userID = -1;
            }


            PreparedStatement pst = con.prepareStatement("insert into tickets(ticket_id, user_id, ticket_phone, date_id, bus_id) values(?,?,?,?,?)");
            UUID ticket_id = UUID.randomUUID();
            pst.setString(1, String.valueOf(ticket_id));
            pst.setInt(2, ticket_userID);
            pst.setString(3, ticket_phone);
            pst.setString(4, ticket_date);
            pst.setString(5, ticket_busID);

            pst.executeUpdate();

            PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ?");
            pst_route.setString(1, ticket_busID);
            ResultSet rs = pst_route.executeQuery();

            if (rs.next()) {
                PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                pst_from.setInt(1, rs.getInt(3));
                pst_to.setInt(1, rs.getInt(4));


                ResultSet rs_from = pst_from.executeQuery();
                ResultSet rs_to = pst_to.executeQuery();

                if (rs_from.next() && rs_to.next()) {
                    ticket userTicket = new ticket();
                    userTicket.ticket_id = String.valueOf(ticket_id);
                    userTicket.ticket_fullname = rs_user.getString(6)+", "+rs_user.getString(5);
                    userTicket.ticket_from = rs_from.getString(2);
                    userTicket.ticket_to = rs_to.getString(2);
                    userTicket.ticket_time = rs.getTime(6);
                    userTicket.ticket_date = Date.valueOf(ticket_date);
                    userTicket.ticket_busID = Integer.parseInt(ticket_busID);
                    request.setAttribute("ticket_data", userTicket);
                    dispatcher = request.getRequestDispatcher("ticket.jsp");
                    dispatcher.forward(request,response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}