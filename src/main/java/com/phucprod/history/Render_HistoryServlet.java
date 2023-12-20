package com.phucprod.history;

import com.phucprod.database_query.HistoryLoader;
import struct.ticket;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/bookinghistory")
public class Render_HistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try{

            HistoryLoader loader = new HistoryLoader();
            List<ticket> ticket_list = loader.getTicketList(session);

            request.setAttribute("data", ticket_list);
            dispatcher = request.getRequestDispatcher("history.jsp");
            dispatcher.forward(request,response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}