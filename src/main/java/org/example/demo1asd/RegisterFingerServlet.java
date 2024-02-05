package org.example.demo1asd;

import ArduinoServerRequest.ArduinoRequestHandle;
import Utils.AppScriptRequest;
import Utils.JasonParser;
import jakarta.json.JsonObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static java.lang.Integer.parseInt;

@WebServlet(name = "RegisterFingerServlet", value = "/RegisterFingerServlet")
public class RegisterFingerServlet extends HttpServlet {
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
        try{
            int id = parseInt(request.getParameter("id"));
            if(id > 0){

                JsonObject registerResult = ArduinoRequestHandle.sendRegister("register",id);
                if(registerResult.getString("status").equals("success")){
                    int result = AppScriptRequest.registerFinger(registerResult,id);
                    if(result == 1){
                        response.sendRedirect("Home.jsp");
                    }
                    else{
                        response.sendRedirect("RegisterFinger.jsp");
                    }

                    response.sendRedirect("Home.jsp");
                }
                else{
                    response.sendRedirect("Register.jsp");
                }
                response.sendRedirect("RegisterFinger.jsp");
            }
            else{
                response.sendRedirect("Register.jsp");
            }
        }catch (Exception e){
        e.printStackTrace();
        }

    }

    public void destroy() {
    }
}