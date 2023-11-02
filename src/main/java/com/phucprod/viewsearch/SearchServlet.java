package com.phucprod.viewsearch;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int search_from = Integer.parseInt(request.getParameter("from"));
        int search_to = Integer.parseInt(request.getParameter("to"));
        String search_date = request.getParameter("date");
        String search_adult = request.getParameter("adult_cnt");
        String search_child = request.getParameter("child_cnt");
        String search_seattype = request.getParameter("seat_type");

        PrintWriter out = response.getWriter();


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
            PreparedStatement pst = con.prepareStatement("select * from route where start_station = ? and end_station = ? and seat_type = ?");
            pst.setInt(1, search_from);
            pst.setInt(2, search_to);
            pst.setString(3,search_seattype.toLowerCase());

            ResultSet rs = pst.executeQuery();

            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    PreparedStatement pst_from = con.prepareStatement("select * from cities where city_id = ?");
                    PreparedStatement pst_to = con.prepareStatement("select * from cities where city_id = ?");

                    pst_from.setInt(1, search_from);
                    pst_to.setInt(1, search_to);
                    ResultSet rs_from = pst_from.executeQuery();
                    ResultSet rs_to = pst_to.executeQuery();
                    if (rs_from.next() && rs_to.next()) {
                        out.println("BUS ID: " + rs.getInt(1));
                        out.println("FROM: " + rs_from.getString(2));
                        out.println("TO: " + rs_to.getString(2));
                        out.println("SEAT TYPE: " + rs.getString(2));
                        out.println("PRICE: " + rs.getInt(8));
                    }
                    out.println("DATE: " + search_date);
                    out.println("ADULT: " + search_adult);
                    out.println("CHILD: " + search_child);
                    rs.relative(1);
                }
            } else {
                out.println("NO ROUTE FOUND");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}