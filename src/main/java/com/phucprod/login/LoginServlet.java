package com.phucprod.login;

import com.phucprod.database_query.LoginAuth;
import struct.loginauth;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_email = request.getParameter("uemail");
        String user_pass = request.getParameter("pass");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try{
            LoginAuth login = new LoginAuth();
            loginauth LoginAttemp = login.Query(user_email, user_pass);
            if (LoginAttemp.CheckAuth == 1) {
                if (LoginAttemp.CheckAdmin == 1) {
                    session.setAttribute("admin", "admin");
                } else {
                    session.setAttribute("admin", "user");
                }
                session.setAttribute("name", LoginAttemp.UserName);
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