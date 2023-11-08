package com.phucprod.viewdata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/viewdata")
public class AdminViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("viewdata.jsp");
        dispatcher.forward(request,response);
    }

}