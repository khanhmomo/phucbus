<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
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
        <a href= "" class="w3-bar-item w3-button w3-mobile">PhucBus</a>
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
                    <form action="search" method="post" id="search_form">
                        <div class="form-group">
                            <h1 style="color: white;">Booking Information</h1>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label style="color: white;">From</label>
                                    <label>
                                        <select name="from" class = "form-control" style="width: 500px;">
                                            <option value="-1">Select departure</option>
                                            <%
                                                try{
                                                    String Query ="select * from cities";
                                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
                                                    Statement stm =con.createStatement();
                                                    ResultSet rs=stm.executeQuery(Query);
                                                    while(rs.next()) {
                                            %>
                                            <option value="<%=rs.getInt("city_id")%>"><%=rs.getString("city_name")%></option>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label style="color: white;">To</label>
                                    <select name="to" class = "form-control" style="width: 500px;">
                                        <option value="-1">Select destination</option>
                                        <%
                                            try{
                                                String Query ="select * from cities";
                                                Class.forName("com.mysql.cj.jdbc.Driver");
                                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root","admin");
                                                Statement stm =con.createStatement();
                                                ResultSet rs=stm.executeQuery(Query);
                                                while(rs.next()) {
                                        %>
                                        <option value="<%=rs.getInt("city_id")%>"><%=rs.getString("city_name")%></option>
                                        <%
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        %>>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <span class="form-label">Departing</span>
                                    <input name="date" class="form-control" type="date" required>
                                </div>
                            </div>

                            <div class="col-md-2">
                                <div class="form-group">
                                    <span class="form-label">Adults (18+)</span>
                                    <select name="adult_cnt" class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                    </select>
                                    <span class="select-arrow"></span>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <span class="form-label">Children (0-17)</span>
                                    <select name="child_cnt" class="form-control">
                                        <option>0</option>
                                        <option>1</option>
                                        <option>2</option>
                                    </select>
                                    <span class="select-arrow"></span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <span class="form-label">Seat type</span>
                                    <select name="seat_type" class="form-control">
                                        <option>Seating</option>
                                        <option>Bed </option>
                                    </select>
                                    <span class="select-arrow"></span>
                                </div>
                            </div>
                        </div>
                        <div class="text-right">
                            <div class="form-btn ">
                                <button class="submit-btn">Search</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>