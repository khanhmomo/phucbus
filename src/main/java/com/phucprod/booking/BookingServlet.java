package com.phucprod.booking;

import com.phucprod.database_query.BookingLoader;
import struct.route;
import struct.user_info;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ticket_bus_id = request.getParameter("bus_id");
        String ticket_date = request.getParameter("booking_date");
        RequestDispatcher dispatcher = null;
        PrintWriter out = response.getWriter();
        try {
            BookingLoader loader = new BookingLoader();
            route item = loader.getRouteDetails(ticket_bus_id, session);
            out.println(item.bus_id);

            if (item != null) {
                user_info user_data = loader.getUserInfo((String) session.getAttribute("name"));
                request.setAttribute("data", item);
                request.setAttribute("date", ticket_date);
                request.setAttribute("user_data", user_data);
                dispatcher = request.getRequestDispatcher("booking.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("search.jsp");
            }

            dispatcher.forward(request, response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}