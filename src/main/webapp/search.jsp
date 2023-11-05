<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="struct.route" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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

    <div class="w3-bar w3-pink w3-sans-serif">
        <a href= "index.jsp" class="w3-bar-item w3-button w3-mobile">PhucBus</a>
        <a href="bookinghistory" class="w3-bar-item w3-button w3-mobile">Booking History</a>
        <a href="about" class="w3-bar-item w3-button w3-mobile">About</a>
        <div class="w3-dropdown-hover w3-mobile w3-right">
            <button class="w3-button">Welcome <%=session.getAttribute("name")%>! <i class="fa fa-caret-down"></i></button>
            <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
                <a href="#" class="w3-bar-item w3-button w3-mobile">Account Setting</a>
                <a href="logout" class="w3-bar-item w3-button w3-mobile">Logout</a>

            </div>
        </div>
    </div>


    <div class="section-center">
        <div class="container">
            <div class="row">
                <div class="booking-form">
                    <div class="w3-container">
                        <h2 class="w3-text-white">SEARCH RESULT</h2>
                        <p class="w3-text-white">Date: <%=request.getAttribute("date")%></p>
                        <p class="w3-text-white">Available route(s) base on your search</p>

                        <form method="post" action="booking">
                            <table class="w3-table w3-white w3-bordered">
                                <tr>
                                    <th>Bus ID</th>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Departing</th>
                                    <th>Arrival</th>
                                    <th>Price</th>
                                </tr>


                                <% List<route> list = (List<route>) request.getAttribute("data");
                                    for(route item : list) {
                                        %>
                                            <tr>
                                                <td><%=item.bus_id%></td>
                                                <td><%=item.start_station%></td>
                                                <td><%=item.end_station%></td>
                                                <td><%=item.start_time%></td>
                                                <td><%=item.arrive_time%></td>
                                                <td><%=item.price%></td>

                                                <td>
                                                    <input type="hidden" name="booking_date" value=<%=request.getAttribute("date")%>>
                                                    <button
                                                            class="w3-btn w3-round-xxlarge w3-pink"
                                                            name="bus_id" type="submit" value=<%=item.bus_id%>
                                                    >
                                                        Book
                                                    </button>
                                                </td>
                                            </tr>
                                        <%
                                    }%>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>