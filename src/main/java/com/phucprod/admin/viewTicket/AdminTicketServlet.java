package com.phucprod.admin.viewTicket;

import com.phucprod.database_query.AdminTicketLoader;
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
            AdminTicketLoader exec = new AdminTicketLoader();
            List<ticket_custom> ticket_list = exec.AdminTicketLoaderSQL(view_ticket_dateID,view_ticket_busID);
            request.setAttribute("selectedTab", "2");
            request.setAttribute("ticket_data", ticket_list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}