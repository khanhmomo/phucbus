package com.phucprod.booking;

import com.phucprod.database.BookingLoader;
import struct.route;
import struct.user_info;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ticket_bus_id = request.getParameter("bus_id");
        String ticket_date = request.getParameter("booking_date");
        RequestDispatcher dispatcher = null;

        try {
            BookingLoader loader = new BookingLoader();
            route item = loader.getRouteDetails(ticket_bus_id, session);

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
