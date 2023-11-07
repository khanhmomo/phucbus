<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="struct.route" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="struct.user_info" %>
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

  <script>
    function printPageArea(areaID){
      var printContent = document.getElementById(areaID).innerHTML;
      var originalContent = document.body.innerHTML;
      document.body.innerHTML = printContent;
      window.print();
      document.body.innerHTML = originalContent;
    }
  </script>
</head>
<body>

<%
  ticket data = (ticket) request.getAttribute("ticket_data");

%>

<div id="booking" class="section">

  <%@ include file = "views/header.html" %>


  <div class="section-center">
    <div class="container">
      <div class="row">
        <div class="booking-form">
          <div class="w3-container">
            <div id="print">
              <h4 class="w3-text-white"><%=request.getAttribute("title")%></h4>

              <h4 class="w3-text-white">Ticket ID: <%=data.ticket_id%></h4>

              <img src=
                           "https://chart.googleapis.com/chart?cht=qr&chl=<%=data.ticket_id%>&chs=160x160&chld=L|0"
                   class="qr-code img-thumbnail img-responsive" />

              <table class="w3-table w3-white w3-bordered">
                <tr>
                  <th>Passenger Name:</th>
                  <td><%=data.ticket_fullname%></td>
                </tr>
                <tr>
                  <th>Bus ID</th>
                  <td><%=data.ticket_busID%></td>
                </tr>
                <tr>
                  <th>Date</th>
                  <td><%=data.ticket_date%></td>
                </tr>
                <tr>
                  <th>From</th>
                  <td><%=data.ticket_from%></td>
                </tr>
                <tr>
                  <th>To</th>
                  <td><%=data.ticket_to%></td>
                </tr>
                <tr>
                  <th>Departure time(*)</th>
                  <td><%=data.ticket_time%></td>
                </tr>
              </table>
              <p class="w3-text-light-gray" style="font-style: italic;">(*)Time might be changed due to traffic condition</p>
              <p class="w3-text-light-gray" style="font-style: italic;">Please arrive on time for boarding (usually 15 mins prior to departure)</p>
            </div>
            <button class="w3-button w3-block w3-section w3-pink w3-ripple w3-padding"
                    href="javascript:void(0);"
                    onclick="printPageArea('print')">
              Print this ticket
            </button>
            <a href= "index.jsp">
              <button class="w3-button w3-block w3-section w3-white w3-text-pink w3-ripple w3-padding">Back to Home</button>
            </a>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file = "views/footer.html" %>
</body>
</html>