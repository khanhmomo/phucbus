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
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div id="booking" class="section">
        <%@ include file = "views/role_picker.jsp" %>
        <div id="booking" class="section">
        <div class="section-center">
            <div class="container">
                <div class="row">
                    <div class="booking-form">
                        <div class="w3-container">
                            <div class="w3-container">
                                <div class="w3-bar w3-pink">
                                    <button class="w3-bar-item w3-button tablink w3-white" onclick="openCity(event,'fname')">Full Name</button>
                                    <button class="w3-bar-item w3-button tablink" onclick="openCity(event,'email')">Email</button>
                                    <button class="w3-bar-item w3-button tablink" onclick="openCity(event,'pass')">Password</button>
                                </div>

                                <div id="fname" class="w3-container w3-white w3-border city">
                                    <div class="w3-padding-16">
                                        <form class="w3-container w3-card-4" method="post" action="usersetting">
                                            <h2> Update your full name</h2>
                                            <p>
                                                <label class="w3-text-pink"><b>First Name</b></label>
                                                <input class="w3-input w3-border" name="new_first" type="text"></p>
                                            <p>
                                                <label class="w3-text-pink"><b>Last Name</b></label>
                                                <input class="w3-input w3-border" name="new_last" type="text"></p>
                                            <p>
                                                <input name="section" type="hidden" value="fullname"></p>
                                                <button class="w3-btn w3-blue">Save</button></p>
                                        </form>
                                    </div>
                                </div>

                                <div id="email" class="w3-container w3-white w3-border city" style="display:none">
                                    <div class="w3-padding-16">
                                        <form class="w3-container w3-card-4" method="post" action="usersetting">
                                            <h2> Update your email</h2>
                                            <p>
                                                <label class="w3-text-pink"><b>New email</b></label>
                                                <input class="w3-input w3-border" name="new_email" type="text"></p>
                                            <p>
                                                <input name="section" type="hidden" value="email"></p>
                                                <button class="w3-btn w3-blue">Save</button></p>
                                        </form>
                                    </div>
                                </div>
                                <div id="pass" class="w3-container w3-white w3-border city" style="display:none">
                                    <div class="w3-padding-16">
                                        <form class="w3-container w3-card-4" method="post" action="usersetting">
                                            <h2> Update your password</h2>
                                            <p>
                                                <label class="w3-text-pink"><b>Current password</b></label>
                                                <input class="w3-input w3-border" name="old_pass" type="password"></p>
                                            <p>
                                                <label class="w3-text-pink"><b>New password</b></label>
                                                <input class="w3-input w3-border" name="new_pass" type="password"></p>
                                            <p>
                                                <label class="w3-text-pink"><b>Repeat new password</b></label>
                                                <input class="w3-input w3-border" name="new_repass" type="password"></p>
                                            <p>
                                                <input name="section" type="hidden" value="password"></p>
                                                <button class="w3-btn w3-blue">Save</button></p>
                                        </form>
                                    </div>
                                </div>

                                <script>
                                    function openCity(evt, cityName) {
                                        var i, x, tablinks;
                                        x = document.getElementsByClassName("city");
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
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <%@ include file = "views/footer.html" %>
        </div>
    </div>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">
    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "success") {
            swal("Your information has been save","Update success!","success");
        }
        if (status == "oldeqlnew") {
            swal("New password can't be the same with old password!","Update failed!","error");
        }
        if (status == "samepass") {
            swal("Password error","Passwords not match! ","error");
        }
    </script>
</body>
</html>