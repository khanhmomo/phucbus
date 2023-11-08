package com.phucprod.createroute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/create_new_route")
public class CreateRouteServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String new_route_busID = request.getParameter("new_route_busID");
        String new_route_from = request.getParameter("new_route_from");
        String new_route_to = request.getParameter("new_route_to");
        String new_route_numseat = request.getParameter("new_route_numseat");
        String new_route_seattype = request.getParameter("new_route_seattype");
        String new_route_starttime = request.getParameter("new_route_starttime");
        String new_route_endtime = request.getParameter("new_route_endtime");
        String new_route_price = request.getParameter("new_route_price");

        RequestDispatcher dispatcher = null;
        Connection con = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst = con.prepareStatement("insert into route(bus_id, seat_type, start_station, end_station, number_of_seat, start_time, arrive_time, price) values(?,?,?,?,?,?,?,?)");
            pst.setString(1, new_route_busID);
            if (Objects.equals(new_route_seattype, "1")) pst.setString(2, "seating"); else pst.setString(2, "bed");
            pst.setString(3, new_route_from);
            pst.setString(4, new_route_to);
            pst.setString(5, new_route_numseat);
            pst.setString(6, new_route_starttime);
            pst.setString(7, new_route_endtime);
            pst.setString(8, new_route_price);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("index.jsp");
            if (rowCount > 0) {
                request.setAttribute("status_add_route", "success");
            } else {
                request.setAttribute("status_add_route", "failed");
            }
            session.setAttribute("admin", "admin");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}