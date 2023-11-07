<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="struct.route" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="struct.ticket" %>
<%
    if(session.getAttribute("name")==null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>PhucBus</title>
    <link rel="icon" type="image/png" href="assets/images/icons/favicon.ico"/>
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="assets/css/bootstrap.min.css" />

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="assets/css/style.css" />

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>
<body>



<div id="booking" class="section">

    <%@ include file = "views/header.html" %>


    <div class="section-center">
        <div class="container">
            <div class="row">
                <div class="booking-form">
                    <div class="w3-container">
                        <h2 class="w3-text-white">BOOKING HISTORY</h2>
                        <form method="post" action="viewticket">
                            <table class="w3-table w3-white w3-bordered">
                                <tr>
                                    <th>Ticket ID</th>
                                    <th>Date</th>
                                    <th>Bus ID</th>
                                    <th>From</th>
                                    <th>To</th>
                                </tr>


                                <% List<ticket> list = (List<ticket>) request.getAttribute("data");
                                    for(ticket item : list) {
                                %>
                                <tr>
                                    <td><%=item.ticket_id%></td>
                                    <td><%=item.ticket_date%></td>
                                    <td><%=item.ticket_busID%></td>
                                    <td><%=item.ticket_from%></td>
                                    <td><%=item.ticket_to%></td>
                                    <td>

                                        <button
                                                class="w3-btn w3-round-xxlarge w3-pink"
                                                name="ticket_id" type="submit" value=<%=item.ticket_id%>
                                        >
                                            View Ticket
                                        </button>
                                    </td>

                                </tr>
                                <%
                                    }%>
                            </table>
                            <form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file = "views/footer.html" %>
</body>
</html>