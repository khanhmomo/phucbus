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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
            PreparedStatement pst = con.prepareStatement("select * from route where bus_id = ?");
            pst.setString(1, ticket_bus_id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                pst_from.setInt(1, rs.getInt(3));
                pst_to.setInt(1, rs.getInt(4));
                PreparedStatement pst_user = con.prepareStatement("select * from users where user_name = ?");
                pst_user.setString(1, (String) session.getAttribute("name"));

                ResultSet rs_from = pst_from.executeQuery();
                ResultSet rs_to = pst_to.executeQuery();
                ResultSet rs_user = pst_user.executeQuery();
                if (rs_from.next() && rs_to.next() && rs_user.next()) {
                    route item = new route();
                    item.bus_id = rs.getInt(1);
                    item.price = rs.getInt(8);
                    item.start_station = rs_from.getString(2);
                    item.end_station = rs_to.getString(2);
                    item.seat_type = rs.getString(2);
                    item.start_time = rs.getTime(6);
                    item.arrive_time = rs.getTime(7);

                    user_info user_data = new user_info();
                    user_data.user_fname = rs_user.getString(5);
                    user_data.user_lname = rs_user.getString(6);
                    request.setAttribute("data", item);
                    request.setAttribute("date", ticket_date);
                    request.setAttribute("user_data", user_data);
                    dispatcher = request.getRequestDispatcher("booking.jsp");
                }
                else {
                    dispatcher = request.getRequestDispatcher("search.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("index.jsp");
            }
            dispatcher.forward(request,response);
        }  catch (Exception e) {
            e.printStackTrace();
        }


    }
}