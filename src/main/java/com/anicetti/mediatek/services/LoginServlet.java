package com.anicetti.mediatek.services;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.setAttribute("data", 14);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("adohizaazpihdpihadzapidhazpdihzad");
        System.out.println(req.getParameter("password"));
        System.out.println(req.getParameter("login"));
        System.out.println(resp);
        HttpSession s = req.getSession();
        s.setAttribute("user", req.getParameter("login"));
    }

    public void destroy() {
    }
}