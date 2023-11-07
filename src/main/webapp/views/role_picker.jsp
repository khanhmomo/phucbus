<%
    if(session.getAttribute("admin") == "admin"){
%>      <%@ include file="admin_header.html"%>
<%}

else {if(session.getAttribute("admin") == "user"){%>
    <%@ include file="header.html"%>
<%}} %>