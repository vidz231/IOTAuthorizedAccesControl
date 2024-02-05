package org.example.demo1asd;

import Model.User;
import Utils.AppScriptRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    User authUser;
        HttpSession session = request.getSession();
        try {
            authUser = AppScriptRequest.authenticate(username,password);
            if(authUser != null)
            {
                session.setAttribute("user",authUser);
                response.sendRedirect("Home.jsp");
            }
            else
            {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //create a request to appscript for authentication
    //if authenticated, redirect to home page
    //else redirect to login page

    }

    public void destroy() {
    }
}