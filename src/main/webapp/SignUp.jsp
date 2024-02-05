<%--
  Created by IntelliJ IDEA.
  User: TRUNG VI
  Date: 2/5/2024
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<!-- Body -->
<h2 class="text-center">Register</h2>
<section class="body">
    <form
            action="RegisterServlet"
            method="POST"
            id="formQL"
    >
        <div class="mb-3 mx-5">
            <label for="userInput" class="form-label">First Name</label>
            <input
                    type="text"
                    class="form-control"
                    name="firstName"
                    id="userInput"
                    aria-describedby="helpId"
                    placeholder="enter your first name"
            />
        </div>
        <div class="mb-3 mx-5">
            <label for="userInput" class="form-label">Username</label>
            <input
                    type="text"
                    class="form-control"
                    name="username"
                    id="userInput"
                    aria-describedby="helpId"
                    placeholder="username"
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
                    placeholder="password"
            />
        </div>
        <div class="mb-3 mx-5">
            <label for="emailInput" class="form-label">Email</label>
            <input
                    type="text"
                    class="form-control"
                    name="email"
                    id="emailInput"
                    aria-describedby="helpId"
                    placeholder="email"
            />
        </div>
        <% if(request.getAttribute("message") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
                <div class="signUp text-center">
                    <a href="./index.html" class="signUpText">Login here</a>
                </div>
        <div class="buttonLogin text-center my-5">
            <input
                    name=""
                    id="loginButton"
                    class="btn btn-primary"
                    type="submit"
                    value="Register"
            />
        </div>
    </form>
</section>
<!-- End Body -->
<%@ include file="Footer.jsp" %>
</body>
</html>
