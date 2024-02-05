package org.example.demo1asd;

import Model.User;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstName");

            String usernamePattern = "^[a-zA-Z0-9_]{3,16}$";
            String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
            String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

            if (!username.matches(usernamePattern)) {
                message = "Invalid username, username must contain only letters, numbers and underscores and have a length of 6-16 characters";
                // Handle invalid username
                request.setAttribute("message", message);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                return;

            }

            if (!password.matches(passwordPattern)) {
                message = "Invalid password, password must contain at least one digit, one lowercase, one uppercase, one special character and have a length of 8-20 characters";
                // Handle invalid password
                request.setAttribute("message", message);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                return;


            }

            if (!email.matches(emailPattern)) {
                message = "Invalid email";
                // Handle invalid email
                request.setAttribute("message", message);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
                return;

            }
            User user = new User(0, username, email, firstname, 0);
            int status = Utils.AppScriptRequest.registerUser(user, password);
            if (status == 1) {
                response.sendRedirect("Login.jsp");
            } else {
                message = "Username or email already exists";
                request.setAttribute("message", message);
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        //create a request to appscript for authentication
        //if authenticated, redirect to Login page
        //else redirect to login page


    }

    public void destroy() {
    }
}