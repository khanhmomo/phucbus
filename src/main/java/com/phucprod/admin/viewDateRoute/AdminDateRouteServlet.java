package com.phucprod.admin.viewDateRoute;

import com.phucprod.database_query.AdminDateRouteLoader;
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
            AdminDateRouteLoader exec = new AdminDateRouteLoader();
            List<route> list = exec.AdminDateRouteLoaderSQL(view_dateroute_dateID,view_dateroute_from,view_dateroute_to);
            request.setAttribute("selectedTab", "3");
            request.setAttribute("dateroute_data", list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}