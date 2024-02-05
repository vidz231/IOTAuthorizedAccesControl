<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: TRUNG VI
  Date: 2/5/2024
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finger Setup</title>
</head>
<body>
<!-- Header -->
<%@include file="Header.jsp"%>


<% User user = (User)session.getAttribute("user"); %>

<!-- Body -->
<h2 class="text-center">Let's set up</h2>
<h2 class="text-center">Fingerprints register</h2>
<section class="body">
    <div class="container">
        <div class="bodyContent">
            <!-- proceed fingerprints set up -->
            <div class="profileSetting text-center my-3">
                <% if(user.isFingerprintRegistered() == 0){%>
                <!-- Button trigger modal -->
                <form method="post" action="RegisterFingerServlet">
                    <input type="text" name="id" value="<%=user.getId()%>" hidden>
                    <input type="submit" value="Proceed" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                </form>
                <%}else{%>
                <p class="fs-5">You have already registered your fingerprints</p>
                <%}%>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5 text-center text-info" id="exampleModalLabel">Let's set up</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p class="fs-5"> Please place your finger on the fingerprint register</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- go back -->
        <div class="goBack text-center">
            <a href="Home.jsp" class="signUpText">Go back</a>
        </div>
    </div>

    </div>
</section>





<!-- Footer -->
<%@include file="Footer.jsp"%>

</body>
</html>
