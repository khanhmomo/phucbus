<!DOCTYPE html>
<html lang="en">
<head>
    <title>PhucBus</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="assets/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <!--===============================================================================================-->
</head>
<body>
    <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
    <div class="limiter">
        <div class="container-login100" style="background-image: url('assets/images/bg-01.jpg');">
            <div class="wrap-login100">
                <form class="login100-form validate-form" method="POST" action="register" id="register-form">
                    <img class="login100-form-logo" src="assets/images/logo.png" >

                    <div class="wrap-input100 validate-input" data-validate = "Enter username">
                        <input class="input100" type="text" name="username" placeholder="Username">
                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Enter first name">
                        <input class="input100" type="text" name="firstname" placeholder="First Name">
                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Enter last name">
                        <input class="input100" type="text" name="lastname" placeholder="Last name">
                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Enter email">
                        <input class="input100" type="text" name="email" placeholder="Email">
                        <span class="focus-input100" data-placeholder="&#xf207;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Enter password">
                        <input class="input100" type="password" name="pass" placeholder="Password">
                        <span class="focus-input100" data-placeholder="&#xf191;"></span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Re-enter password">
                        <input class="input100" type="password" name="repass" placeholder="Re-Password">
                        <span class="focus-input100" data-placeholder="&#xf191;"></span>
                    </div>
                    <input type="hidden" name="admin" value="false">

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Register
                        </button>
                    </div>

                    <div class="text-center p-t-90">
                        <a class="txt1" href="login.jsp">
                            Already have an account? Login now
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>




    <div id="dropDownSelect1"></div>

    <!--===============================================================================================-->
    <script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/bootstrap/js/popper.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/daterangepicker/moment.min.js"></script>
    <script src="assets/vendor/daterangepicker/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="assets/vendor/countdowntime/countdowntime.js"></script>
    <!--===============================================================================================-->
    <script src="assets/js/main.js"></script>
    <!--===============================================================================================-->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="alert/dist/sweetalert.css">

    <script type="text/javascript">
        var status = document.getElementById("status").value;
        if (status == "success") {
            swal("Welcome to PhucBus","Account created!","success");
        }
        if (status == "already") {
            swal("Username or Email exists!","Registration failed!","error");
        }
        if (status == "pw_noteql") {
            swal("Password error","Passwords not match! ","error");
        }
    </script>

</body>
</html>