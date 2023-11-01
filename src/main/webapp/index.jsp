<%
  if(session.getAttribute("name")==null) {
      response.sendRedirect("login.jsp");
  }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <h1>BOOKING PAGE IN CONSTRUCTION!</h1>
        <a href="logout">LOGOUT</a>
        <a href="logout"><%=session.getAttribute("name")%></a>
    </body>
</html>
