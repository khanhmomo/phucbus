package com.phucprod.admin.viewTicket;

import struct.ticket;
import struct.ticket_custom;

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

@WebServlet("/admin_view_ticket")
public class AdminTicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view_ticket_dateID = request.getParameter("view_ticket_dateID");
        String view_ticket_busID = request.getParameter("view_ticket_busID");


        RequestDispatcher dispatcher = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst_ticket = con.prepareStatement("select * from tickets where bus_id = ? and date_id = ?");
            pst_ticket.setString(1, view_ticket_busID);
            pst_ticket.setString(2, view_ticket_dateID);

            ResultSet rs = pst_ticket.executeQuery();
            List<ticket_custom> ticket_list = new ArrayList<ticket_custom>();
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
            request.setAttribute("selectedTab", "2");
            request.setAttribute("ticket_data", ticket_list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}