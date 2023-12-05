package com.phucprod.viewsearch;

import com.phucprod.database_query.SearchLoader;
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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int search_from = Integer.parseInt(request.getParameter("from"));
        int search_to = Integer.parseInt(request.getParameter("to"));

        String search_date = request.getParameter("date");
        String search_seattype = request.getParameter("seat_type").toLowerCase();
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;

        try{
            SearchLoader loadSearch = new SearchLoader();
            List<route> list_route = loadSearch.ListRoute(search_from, search_to, search_date, search_seattype);

            for (route item : list_route) {
                out.println(item.bus_id);
            }
            if (list_route != null) {
                request.setAttribute("data", list_route);
                request.setAttribute("date", search_date);
                dispatcher = request.getRequestDispatcher("search.jsp");
            }
            else {
                dispatcher = request.getRequestDispatcher("index.jsp");
            }
            dispatcher.forward(request,response);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}