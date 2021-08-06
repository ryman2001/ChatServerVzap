
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Registration.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Oswald">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>Alumni Registration</title>

    </head>
    <body>

        <h1 class="text-center"><b>Alumni</b>Registration</h1>
        <form class="col-lg-6 offset-lg-3" action = "UserServlet" method="POST">
            <div class="row justify-content-center">
                <input class="form-control set-width" type="text" placeholder="Enter your Name" name="name" required>
                <input class="form-control set-width" type="text" placeholder="Enter your Surname" name="surname" required>
                <input class="form-control set-width" type="email" placeholder="Enter your Email" name="email" required>
                <input class="form-control set-width" type="text" placeholder="Enter your ID Number" name="userID" required>
                <input class="form-control set-width" type="password" placeholder="Enter a Password" name="password" required>
                <input class="form-control set-width" type="password" placeholder="Confirm Password" name="passwordConfirm" required>
                <input class="form-control set-width" type="text" placeholder="Enter your Course ID (Optional)" name="courseID">
                <input class="form-control set-width" type="text" placeholder="Enter your Cell Phone Number(Optional)" name="cellNum">
                <input class="form-control set-width" type="submit" value="registrationRequestCreation" name="action">
            </div>
        </form>
        <p class="text-center">Already have an Account?</p>
        <div class="container text-center" >
            <a href="AlumniLogin.jsp">Login Here!</a>
        </div>
    </body>
</html>