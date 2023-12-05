package com.phucprod.viewticket;

import com.phucprod.database_query.TicketLoader;
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
        HttpSession session = request.getSession();
        String ticket_id = request.getParameter("ticket_id");
        try {

            TicketLoader viewTicket = new TicketLoader();
            ticket userTicket = viewTicket.loadTicket(ticket_id, session);
            request.setAttribute("ticket_data", userTicket);
            request.setAttribute("title", "PHUCBUS TICKET");
            dispatcher = request.getRequestDispatcher("ticket.jsp");
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}