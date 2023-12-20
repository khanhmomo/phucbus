package com.phucprod.admin.viewRoute;

import com.phucprod.database_query.AdminDateRouteLoader;
import com.phucprod.database_query.AdminRouteLoader;
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
        String view_route_from = request.getParameter("view_route_from");
        String view_route_to = request.getParameter("view_route_to");
        RequestDispatcher dispatcher = null;
        try {
            AdminRouteLoader exec = new AdminRouteLoader();
            List<route> list = exec.AdminRouteLoaderSQL(view_route_from, view_route_to);
            request.setAttribute("selectedTab", "1");
            request.setAttribute("route_data", list);
            dispatcher = request.getRequestDispatcher("viewdata.jsp");
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}