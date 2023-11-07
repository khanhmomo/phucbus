<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="struct.route" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="struct.user_info" %>
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
                        <h2 class="w3-text-white">BOOKING DETAIL</h2>
                        <%
                            route data = (route) request.getAttribute("data");
                            user_info user = (user_info) request.getAttribute("user_data");
                        %>
                        <h4 class="w3-text-white">TRAVELER: <%=user.user_lname%>, <%=user.user_fname%></h4>
                        <table class="w3-table w3-white w3-bordered">
                            <tr>
                                <th>Bus ID</th>
                                <td><%=data.bus_id%></td>
                            </tr>
                            <tr>
                                <th>Date</th>
                                <td><%=request.getAttribute("date")%></td>
                            </tr>
                            <tr>
                                <th>From</th>
                                <td><%=data.start_station%></td>
                            </tr>
                            <tr>
                                <th>To</th>
                                <td><%=data.end_station%></td>
                            </tr>
                            <tr>
                                <th>Departure time(*)</th>
                                <td><%=data.start_time%></td>
                            </tr>
                            <tr>
                                <th>Arrival time(*)</th>
                                <td><%=data.arrive_time%></td>
                            </tr>
                            <tr>
                                <th>Seat type</th>
                                <td><%=data.seat_type%></td>
                            </tr>
                            <tr>
                                <th>Price</th>
                                <td><%=data.price%></td>
                            </tr>
                        </table>
                        <br>
                        <form action="ticket" class="w3-card-4 w3-white w3-container" method="post">
                            <h2 class="w3-center w3-text-pink">YOUR BOOKING INFORMATION</h2>

                            <div class="w3-row w3-section">
                                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-phone"></i></div>
                                <div class="w3-rest">
                                    <input class="w3-input w3-border" name="phone" type="text" placeholder="Phone">
                                </div>
                            </div>

                            <select class="w3-select" name="payment">
                                <option value="" disabled selected>Choose your payment method</option>
                                <option value="1">Cash (at reception)</option>
                                <option value="2">Mobile Banking (including MoMo and ZaloPay)</option>
                                <option value="3">Visa, MasterCard, etc</option>
                            </select>
                            <input type="hidden" name="date" value=<%=request.getAttribute("date")%>>
                            <input type="hidden" name="bus_id" value=<%=data.bus_id%>>
                            <button class="w3-button w3-block w3-section w3-pink w3-ripple w3-padding">CONFIRM</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file = "views/footer.html" %>
</body>
</html>