package com.phucprod.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_email = request.getParameter("email");
        String user_pass = request.getParameter("pass");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
            PreparedStatement pst = con.prepareStatement("select * from users where user_email = ? and user_pw = ?");
            pst.setString(1, user_email);
            pst.setString(2, user_pass);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                session.setAttribute("name", rs.getString("user_name"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}