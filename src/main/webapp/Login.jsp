<%--
  Created by IntelliJ IDEA.
  User: TRUNG VI
  Date: 2/5/2024
  Time: 2:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="Header.jsp"%>
<!-- Body -->
<h2 class="text-center">Login</h2>
<section class="body">
    <form action="LoginServlet" method="post" id="formQL">
        <div class="mb-3 mx-5">
            <label for="userInput" class="form-label">Username</label>
            <input
                    type="text"
                    class="form-control"
                    name="username"
                    id="userInput"
                    aria-describedby="helpId"
                    placeholder="Username"
            />
        </div>
        <div class="mb-3 mx-5">
            <label for="passwordInput" class="form-label">Password</label>
            <input
                    type="password"
                    class="form-control"
                    name="password"
                    id="passwordInput"
                    aria-describedby="helpId"
                    placeholder="Password"
            />
        </div>
    <div class="signUp text-center">
        <a href="./SignUp.jsp" class="signUpText">Sign up here</a>
    </div>
    <div class="logged text-center">
        <a href="./loginalrdy.html" class="loggedText">Come to logged</a>
    </div>
    <div class="buttonLogin text-center my-5">
        <button
                name="submit button"
                id="loginButton"
                class="btn btn-primary"
                type="button"
                value="Login"
        >Login</button>
    </div>
    </form>
    <script>
        document.getElementById("loginButton").addEventListener("click", function () {
            document.getElementById("formQL").submit();
        })
    </script>
</section>

<%@include file="Footer.jsp"%>
</body>
</html>
