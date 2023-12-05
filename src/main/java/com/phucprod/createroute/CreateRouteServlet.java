package com.phucprod.createroute;

import com.phucprod.database_update.CreateRoute;
import struct.route;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

@WebServlet("/create_new_route")
public class CreateRouteServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        route item = new route();
        ArrayList<String> data = new ArrayList<>();
        data.add(request.getParameter("new_route_busID"));
        data.add(request.getParameter("new_route_from"));
        data.add(request.getParameter("new_route_to"));
        data.add(request.getParameter("new_route_numseat"));
        data.add(request.getParameter("new_route_seattype"));
        if (Objects.equals(data.get(4), "1")) data.set(4, "seating");
        if (Objects.equals(data.get(4), "2")) data.set(4, "bed");
        data.add(request.getParameter("new_route_starttime"));
        data.add(request.getParameter("new_route_endtime"));
        data.add(request.getParameter("new_route_price"));

        RequestDispatcher dispatcher = null;
        Connection con = null;

        try {
            CreateRoute exec = new CreateRoute();
            boolean rowCount = exec.CreateRouteSQL(data);
            dispatcher = request.getRequestDispatcher("index.jsp");
            if (rowCount) {
                request.setAttribute("status", "route_success");
            } else {
                request.setAttribute("status", "route_failed");
            }
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}