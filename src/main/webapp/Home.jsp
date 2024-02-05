<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: TRUNG VI
  Date: 2/5/2024
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Header -->
<%@include file="Header.jsp"%>


<% User user = (User) session.getAttribute("user"); %>
<% if (user == null) {
    response.sendRedirect("Login.jsp");
} %>


<!-- Body -->
<h2 class="text-center">Welcome back <%= user.getFirstname()%></h2>
<section class="body">
    <div class="container">
        <div class="bodyContent">
            <!-- profile set up -->
            <div class="profileSetting text-center my-3">
                <button class="btn btn-primary" id="btn_profile">
                    <a href="Profile.jsp" class="text-decoration-none text-white"
                    >Your profile</a
                    >
                </button>
            </div>

            <!-- fingerprints set up -->
            <div class="fingerprintsSetting text-center my-3">
                <button class="btn btn-primary" id="btn_profile">
                    <a
                            href="./FingerSetup.jsp"
                            class="text-decoration-none text-white"
                    >Set up your fingerprints</a
                    >
                </button>
            </div>
        </div>

        <!-- log out (go back to login page) -->
        <div class="signUp text-center">

            <a href="./index.html" class="signUpText">Log out</a>
        </div>
    </div>
</section>

<!-- Footer -->
<%@include file="Footer.jsp"%>

</body>
</html>
