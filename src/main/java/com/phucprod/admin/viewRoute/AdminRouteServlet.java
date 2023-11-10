package com.phucprod.admin.viewRoute;

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

@WebServlet("/admin_view_route")
public class AdminRouteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String view_route_from = request.getParameter("view_route_from");
        String view_route_to = request.getParameter("view_route_to");
        RequestDispatcher dispatcher = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");

            PreparedStatement pst_ticket = con.prepareStatement("select * from route where start_station = ? and end_station = ?");
            pst_ticket.setString(1, view_route_from);
            pst_ticket.setString(2, view_route_to);


            ResultSet rs = pst_ticket.executeQuery();
            List <route> list = new ArrayList<route>();

            if(rs.first()) {
                while(!rs.isAfterLast()) {
                    route item = new route();
                    item.bus_id = rs.getInt(1);
                    item.start_time = rs.getTime(6);
                    item.arrive_time = rs.getTime(7);
                    item.seat_type = rs.getString(2);
                    item.price = rs.getInt(8);
                    list.add(item);
                    rs.relative(1);
                }
            }
            request.setAttribute("selectedTab", "1");
            request.setAttribute("route_data", list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}