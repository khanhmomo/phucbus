package com.phucprod.createdateroute;

import com.phucprod.database_update.CreateDateRoute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

@WebServlet("/create_new_date_route")
public class CreateDateRouteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String new_date_route_dateID = request.getParameter("new_date_route_dateID");
        String new_date_route_busID = request.getParameter("new_date_route_busID");

        RequestDispatcher dispatcher = null;
        Connection con = null;
        CreateDateRoute exec = new CreateDateRoute();
        String status = exec.CreateDateRouteSQL(new_date_route_dateID,new_date_route_busID);
        request.setAttribute("status", status);
        dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

}