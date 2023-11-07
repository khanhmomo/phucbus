package com.phucprod.usersetting;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

@WebServlet("/usersetting")
public class UserSettingServlet extends HttpServlet {
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String method = request.getParameter("section");
        Connection con = null;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (Objects.equals(method, "fullname")) {
            String new_first = request.getParameter("new_first");
            String new_last = request.getParameter("new_last");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");
                String query = "update users set user_firstname=?, user_lastname=? where user_name=?;";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,new_first);
                pst.setString(2,new_last);
                pst.setString(3, (String) session.getAttribute("name"));
                pst.executeUpdate();
                request.setAttribute("status", "success");
                dispatcher = request.getRequestDispatcher("usersetting.jsp");
                dispatcher.forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (Objects.equals(method, "email")) {
            String new_email = request.getParameter("new_email");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");
                String query = "update users set user_email=? where user_name=?;";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1,new_email);
                pst.setString(2, (String) session.getAttribute("name"));
                pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("usersetting.jsp");
                request.setAttribute("status", "success");
                dispatcher.forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (Objects.equals(method, "password")){
            String old_pass = request.getParameter("old_pass");
            String new_pass = request.getParameter("new_pass");
            String new_repass = request.getParameter("new_repass");

            if (Objects.equals(old_pass, new_pass)) {
                dispatcher = request.getRequestDispatcher("usersetting.jsp");
                request.setAttribute("status", "oldeqlnew");
            }else if (!Objects.equals(new_pass, new_repass)) {
                dispatcher = request.getRequestDispatcher("usersetting.jsp");
                request.setAttribute("status", "samepass");
            }else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "admin");
                    String query = "update users set user_pw=? where user_name=?;";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1,new_pass);
                    pst.setString(2, (String) session.getAttribute("name"));
                    pst.executeUpdate();
                    dispatcher = request.getRequestDispatcher("usersetting.jsp");
                    request.setAttribute("status", "success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dispatcher.forward(request,response);
        }
    }
}