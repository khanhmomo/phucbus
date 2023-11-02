package com.phucprod.registration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("username");
        String user_email = request.getParameter("email");
        String user_pass = request.getParameter("pass");
        String user_repass = request.getParameter("repass");

        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");

            PreparedStatement pst_emailcheck = con.prepareStatement("select * from users where user_email = ? ");
            pst_emailcheck.setString(1, user_email);
            PreparedStatement pst_namecheck = con.prepareStatement("select * from users where user_name = ? ");
            pst_namecheck.setString(1, user_name);

            ResultSet rs_email = pst_emailcheck.executeQuery();
            ResultSet rs_name = pst_namecheck.executeQuery();
            if (rs_email.next() || rs_name.next()) {
                dispatcher = request.getRequestDispatcher("register.jsp");
                request.setAttribute("status", "already");
                dispatcher.forward(request, response);
            }
            else if (!Objects.equals(user_pass, user_repass)) {
                dispatcher = request.getRequestDispatcher("register.jsp");
                request.setAttribute("status", "pw_noteql");
                dispatcher.forward(request, response);
            }
            else {
                PreparedStatement pst = con.prepareStatement("insert into users(user_name, user_pw, user_email) values(?,?,?) ");
                pst.setString(1, user_name);
                pst.setString(2, user_pass);
                pst.setString(3, user_email);


                int rowCount = pst.executeUpdate();
                dispatcher = request.getRequestDispatcher("register.jsp");
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failed");
                }
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}