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
        String user_email = request.getParameter("uemail");
        String user_pass = request.getParameter("pass");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst = con.prepareStatement("select * from users where user_email = ? and user_pw = ?");
            pst.setString(1, user_email);
            pst.setString(2, user_pass);

            PreparedStatement pst_uname = con.prepareStatement("select * from users where user_name = ? and user_pw = ?");
            pst_uname.setString(1, user_email);
            pst_uname.setString(2, user_pass);

            ResultSet rs = pst.executeQuery();
            ResultSet rs_uname = pst_uname.executeQuery();

            int check = 0;
            if (rs.next()) check = 1;
            if (rs_uname.next()) check = 2;


            if (check != 0) {
                if (check == 1) {
                    if (rs.getInt("user_admin") == 1) {
                        session.setAttribute("admin", "admin");
                    } else {
                        session.setAttribute("admin", "user");
                    }

                    session.setAttribute("name", rs.getString("user_name"));
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }
                if (check == 2) {
                    if (rs_uname.getInt("user_admin") == 1) {
                        session.setAttribute("admin", "admin");
                    } else {
                        session.setAttribute("admin", "user");
                    }
                    session.setAttribute("name", rs_uname.getString("user_name"));
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }

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