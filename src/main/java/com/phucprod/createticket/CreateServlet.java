package com.phucprod.createticket;

import com.phucprod.database_update.CreateTicket;
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

        RequestDispatcher dispatcher = null;
        CreateTicket createTicket = new CreateTicket();
        ticket userTicket = null;
        try {
            userTicket = createTicket.CreateTicketSQL(ticket_date, ticket_busID, ticket_phone, session);
            request.setAttribute("ticket_data", userTicket);
            request.setAttribute("title", "CONGRATULATION! YOUR TICKET HAS BEEN BOOKED");
            dispatcher = request.getRequestDispatcher("ticket.jsp");
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}