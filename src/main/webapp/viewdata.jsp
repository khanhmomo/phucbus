<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Equals" %>
<%@ page import="java.util.Objects" %>
<%@ page import="struct.ticket_custom" %>
<%@ page import="java.util.List" %>
<%@ page import="struct.route" %>
<%
  if(session.getAttribute("name") == null) {
    response.sendRedirect("login.jsp");
  }
  if(session.getAttribute("name") != null && session.getAttribute("admin") != "admin") {
    response.sendRedirect("index.jsp");
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
<input type="hidden" id="selectedTabInput" value=<%=request.getAttribute("selectedTab")%>>
<%@ include file="views/admin_header.html"%>
<br><br>


  <div class="w3-container">
    <div class="w3-bar w3-pink">
      <button class="w3-bar-item w3-button tablink w3-white" onclick="openCity(event,'1')">View TICKET list</button>
      <button class="w3-bar-item w3-button tablink" onclick="openCity(event,'2')">View active ROUTE</button>
      <button class="w3-bar-item w3-button tablink" onclick="openCity(event,'3')">View ROUTE by DATE</button>
    </div>

    <div id="1" class="w3-container w3-white w3-border city">
      <div class="w3-padding-16">
        <form class="w3-container w3-card-4" method="post" action="admin_view_ticket">
          <h2> Query ticket list by DATE and BUS ID</h2>
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
                <p>
                  <label class="w3-text-pink"><b>Date</b></label>
                  <input class="w3-input w3-border" name="view_ticket_dateID" type="date"></p>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group">
                <p>
                  <label class="w3-text-pink"><b>Bus ID</b></label>
                  <input class="w3-input w3-border" name="view_ticket_busID" type="text"></p>
              </div>
            </div>
          </div>
          <button class="w3-btn w3-pink w3-round-xxlarge">Search</button></p>
        </form>

        <table class="w3-table w3-bordered">
          <tr class = "w3-pink">
            <th>Ticket ID</th>
            <th>First Name</th>
            <th>Last Name</th>
          </tr>
          <% List<ticket_custom> list = (List<ticket_custom>) request.getAttribute("ticket_data");
          if(list != null) {
          for(ticket_custom item : list) {
          %>
          <tr>
            <td><%=item.ticketID%></td>
            <td><%=item.firstname%></td>
            <td><%=item.lastname%></td>
          </tr>
          <%
            }}%>
        </table>

      </div>
    </div>

    <div id="2" class="w3-container w3-white w3-border city" style="display:none">
      <div class="w3-padding-16">
        <form class="w3-container w3-card-4" method="post" action="admin_view_route">
          <h2> Query ROUTE</h2>
          <div class="row">

            <div class="col-md-6">
              <div class="form-group">
                <label class="w3-text-pink"><b>From</b></label>
                <select name="view_route_from" class="w3-select w3-input w3-border">
                  <option value="-1" >Select depnarture</option>
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
                <label class="w3-text-pink"><b>To</b></label>
                <select name="view_route_to" class="w3-select w3-input w3-border " required>
                  <option value="-1" class="w3-text-pink">Select destination</option>
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


          <p><button class="w3-btn w3-pink w3-round-xxlarge">Search</button></p>
        </form>
        <table class="w3-table w3-bordered">
          <tr class = "w3-pink">
            <th>Bus ID</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Seat type</th>
            <th>Price</th>
          </tr>
          <% List<route> list_route = (List<route>) request.getAttribute("route_data");
            if(list_route != null) {
              for(route item : list_route) {
          %>
          <tr>
            <td><%=item.bus_id%></td>
            <td><%=item.start_time%></td>
            <td><%=item.arrive_time%></td>
            <td><%=item.seat_type%></td>
            <td><%=item.price%></td>
          </tr>
          <%
              }}%>
        </table>
      </div>
    </div>
    <div id="3" class="w3-container w3-white w3-border city" style="display:none">
      <div class="w3-padding-16">
        <form class="w3-container w3-card-4" method="post" action="admin_view_dateroute">
          <h2>Query ROUTE by Date</h2>
          <div class="row">
            <div class="col-md-4">
              <div class="form-group">
                <p>
                  <label class="w3-text-pink"><b>Date</b></label>
                  <input class="w3-input w3-border" name="view_dateroute_dateID" type="date"></p>
              </div>
            </div>
            <div class="col-md-4">
              <div class="form-group">
                <label class="w3-text-pink"><b>From</b></label>
                <select name="view_dateroute_from" class="w3-select w3-input w3-border">
                  <option value="-1" >Select depnarture</option>
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
            <div class="col-md-4">
              <div class="form-group">
                <label class="w3-text-pink"><b>To</b></label>
                <select name="view_dateroute_to" class="w3-select w3-input w3-border " required>
                  <option value="-1" class="w3-text-pink">Select destination</option>
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
          <button class="w3-btn w3-pink w3-round-xxlarge">Search</button></p>
        </form>
        
        <table class="w3-table w3-bordered">
          <tr class = "w3-pink">
            <th>Bus ID</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Taken Seat</th>
          </tr>
          <% List<route> list_route_date = (List<route>) request.getAttribute("dateroute_data");
            if(list_route_date != null) {
              for(route item : list_route_date) {
          %>
          <tr>
            <td><%=item.bus_id%></td>
            <td><%=item.start_time%></td>
            <td><%=item.arrive_time%></td>
            <td><%=item.number_of_seat%></td>
          </tr>
          <%
              }}%>
        </table>
      </div>
    </div>

    <script>
      function openCity(evt, cityName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("city");
        var param = document.getElementById("selectedTabInput").value;

        for (i = 0; i < x.length; i++) {
          x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
          tablinks[i].className = tablinks[i].className.replace(" w3-white", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " w3-white";




      }
    </script>
  </div>


<%@ include file = "views/footer.html" %>
</body>
</html>