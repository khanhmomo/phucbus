package com.phucprod.admin.viewDateRoute;

import struct.route;

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

@WebServlet("/admin_view_dateroute")
public class AdminDateRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view_dateroute_dateID = request.getParameter("view_dateroute_dateID");
        String view_dateroute_from = request.getParameter("view_dateroute_from");
        String view_dateroute_to = request.getParameter("view_dateroute_to");
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");

            PreparedStatement pst_ticket = con.prepareStatement("select * from date_route where date_id = ? and date_from = ? and date_to = ?");
            pst_ticket.setString(1, view_dateroute_dateID);
            pst_ticket.setString(2, view_dateroute_from);
            pst_ticket.setString(3, view_dateroute_to);

            ResultSet rs = pst_ticket.executeQuery();
            List<route> list = new ArrayList<route>();

            if(rs.first()) {
                while(!rs.isAfterLast()) {
                    route item = new route();
                    item.bus_id = rs.getInt(4);
                    PreparedStatement pst = con.prepareStatement("select * from route where bus_id = ?");
                    pst.setInt(1, rs.getInt(4));
                    ResultSet rs_bus = pst.executeQuery();

                    if (rs_bus.next()) {
                        item.start_time = rs_bus.getTime(6);
                        item.arrive_time = rs_bus.getTime(7);
                    }
                    item.number_of_seat = rs_bus.getInt(5) - rs.getInt(5);
                    list.add(item);
                    rs.relative(1);
                }
            }

            request.setAttribute("selectedTab", "3");
            request.setAttribute("dateroute_data", list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}