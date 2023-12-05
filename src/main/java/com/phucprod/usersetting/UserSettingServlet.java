package com.phucprod.usersetting;

import com.phucprod.database_update.UserSetting;

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

        UserSetting update = new UserSetting();

        if (Objects.equals(method, "fullname")) {
            String new_first = request.getParameter("new_first");
            String new_last = request.getParameter("new_last");

            try {
                update.updateFullName(new_first, new_last, session);
                request.setAttribute("status", "success");
                dispatcher = request.getRequestDispatcher("usersetting.jsp");
                dispatcher.forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (Objects.equals(method, "email")) {
            String new_email = request.getParameter("new_email");
            try {
                update.updateEmail(new_email, session);
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
                    update.updatePassword(new_pass, session);
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