<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: TRUNG VI
  Date: 2/5/2024
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="Header.jsp" %>


<% User user = (User) session.getAttribute("user"); %>
<!-- Body -->
<h2 class="text-center">Your profile</h2>
<section class="body">
    <div class="container">
        <div class="bodyContent px-5 text-danger">
            <h3 class="Username my-3">First Name: <%= user.getFirstname()%> </h3>
            <h3 class="Password my-3">Email: <%=user.getEmail()%></h3>
            <h3 class="Password my-3">Fingerprint Registered: <%=user.isFingerprintRegistered() ==1? "✅":"❌"%></h3>

        </div>

        <!-- go back -->
        <div class="goBack text-center">
            <a href="./Home.jsp" class="signUpText">Go back</a>
        </div>
    </div>

    <!-- log out (go back to login page) -->
    <div class="signUp text-center">
        <a href="./index.html" class="signUpText">Log out</a>
    </div>
    </div>
</section>


<%@ include file="Footer.jsp" %>

</body>
</html>
