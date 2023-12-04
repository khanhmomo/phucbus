package com.phucprod.booking;

import struct.route;
import struct.user_info;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String ticket_bus_id = request.getParameter("bus_id");
        String ticket_date = request.getParameter("booking_date");
        RequestDispatcher dispatcher = null;

        String session_name;
        //session_name = session.getAttribute();
        session_name = (String) session.getAttribute("name");
        //Deo hieu lam :))))))))) =))




        try {


            request.setAttribute("data", item);
            request.setAttribute("date", ticket_date);
                    request.setAttribute("user_data", user_data);
                    dispatcher = request.getRequestDispatcher("booking.jsp");

                               dispatcher = request.getRequestDispatcher("search.jsp");
                          dispatcher.forward(request,response);
        }  catch (Exception e) {
            e.printStackTrace();
        }


    }
}