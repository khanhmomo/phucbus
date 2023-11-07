package com.phucprod.viewsearch;

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
        String search_seattype = request.getParameter("seat_type");

        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
            PreparedStatement pst = con.prepareStatement("select * from date_route where date_id = ? and date_from = ? and date_to = ?");
            pst.setString(1, search_date);
            pst.setInt(2, search_from);
            pst.setInt(3, search_to);
            ResultSet rs = pst.executeQuery();


            if (rs.next()) {
                if (rs.first()){
                    List<route> list_route = new ArrayList<route>();
                    while (!rs.isAfterLast()) {
                        PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                        PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");
                        PreparedStatement pst_route = con.prepareStatement("select * from route where bus_id = ? and seat_type = ?");
                        pst_from.setInt(1, search_from);
                        pst_to.setInt(1, search_to);
                        pst_route.setInt(1, rs.getInt(4));
                        pst_route.setString(2, search_seattype.toLowerCase());


                        ResultSet rs_from = pst_from.executeQuery();
                        ResultSet rs_to = pst_to.executeQuery();
                        ResultSet rs_route = pst_route.executeQuery();
                        if (rs_from.next() && rs_to.next() && rs_route.next() && rs.getInt(5) > 0) {
                            route item = new route();
                            item.bus_id = rs.getInt(4);
                            item.price = rs_route.getInt(8);
                            item.start_station = rs_from.getString(2);
                            item.end_station = rs_to.getString(2);
                            item.seat_type = rs_route.getString(2);
                            item.start_time = rs_route.getTime(6);
                            item.arrive_time = rs_route.getTime(7);
                            list_route.add(item);
                            request.setAttribute("data", list_route);
                            request.setAttribute("date", rs.getDate(1));
                            dispatcher = request.getRequestDispatcher("search.jsp");
                        }
                        else {
                            dispatcher = request.getRequestDispatcher("index.jsp");
                        }
                        rs.relative(1);
                    }
                }

            } else {

                dispatcher = request.getRequestDispatcher("index.jsp");
            }
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}