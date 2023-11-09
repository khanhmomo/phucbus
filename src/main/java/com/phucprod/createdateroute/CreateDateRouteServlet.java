package com.phucprod.createdateroute;

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
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");
            PreparedStatement pst_busCheck = con.prepareStatement("select * from route where bus_id = ? ");
            pst_busCheck.setString(1,new_date_route_busID);
            ResultSet rs_check = pst_busCheck.executeQuery();

            if (rs_check.next()) {
                PreparedStatement pst_dateRouteCheck = con.prepareStatement("select * from date_route where date_id = ? and date_bus = ?");
                pst_dateRouteCheck.setString(1, new_date_route_dateID);
                pst_dateRouteCheck.setString(2, new_date_route_busID);
                ResultSet rs_date_route_check = pst_dateRouteCheck.executeQuery();
                if(rs_date_route_check.next()) {
                    request.setAttribute("status","date_already");
                }
                else {
                    PreparedStatement pst = con.prepareStatement("insert into date_route(date_id, date_from, date_to, date_bus, date_available) values(?,?,?,?,?)");
                    pst.setString(1, new_date_route_dateID);
                    pst.setInt(2, rs_check.getInt(3));
                    pst.setInt(3, rs_check.getInt(4));
                    pst.setString(4,new_date_route_busID);
                    pst.setInt(5, rs_check.getInt(5));
                    int rowCount = pst.executeUpdate();
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    if (rowCount > 0) {
                        request.setAttribute("status", "date_success");
                    } else {
                        request.setAttribute("status", "date_failed");
                    }
                }
            } else {
                request.setAttribute("status","date_notfound");
            }
            dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}