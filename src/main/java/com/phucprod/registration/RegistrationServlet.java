package com.phucprod.registration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

import com.phucprod.database_update.RegisLoader;
import struct.regisform;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    private  static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        regisform data = new regisform();
        data.user_name = request.getParameter("username");
        data.user_email = request.getParameter("email");
        data.user_pass = request.getParameter("pass");
        data.user_repass = request.getParameter("repass");
        data.user_firstname = request.getParameter("firstname");
        data.user_lastname = request.getParameter("lastname");
        String checkAdmin = request.getParameter("admin");
        if (Objects.equals(checkAdmin, "false")) data.user_admin = false;
        else data.user_admin = true;

        RequestDispatcher dispatcher = null;

        try {

            RegisLoader TryRegis = new RegisLoader();
            boolean checkExist = TryRegis.check_Exist(data.user_email, data.user_name);
            boolean checkPass = TryRegis.check_Pass(data.user_pass, data.user_repass);

            if (checkExist) {
                if (Objects.equals(data.user_admin, true)) {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("status", "already");
                    dispatcher.forward(request, response);
                } else if(Objects.equals(data.user_admin, false)){
                    dispatcher = request.getRequestDispatcher("register.jsp");
                    request.setAttribute("status", "already");
                    dispatcher.forward(request, response);
                }

                request.setAttribute("status", "already");
                dispatcher.forward(request, response);
            }
            else if (!checkPass) {
                if (Objects.equals(data.user_admin, true)) {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("status", "pw_noteql");
                    dispatcher.forward(request, response);
                } else if(Objects.equals(data.user_admin, false)){
                    dispatcher = request.getRequestDispatcher("register.jsp");
                    request.setAttribute("status", "pw_noteql");
                    dispatcher.forward(request, response);
                }
            }
            else {

                boolean Execute_Regis = TryRegis.Regis(data);

                if (Objects.equals(data.user_admin, true)) {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                } else if(Objects.equals(data.user_admin, false)){
                    dispatcher = request.getRequestDispatcher("register.jsp");
                }

                if (Execute_Regis) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failed");
                }
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}