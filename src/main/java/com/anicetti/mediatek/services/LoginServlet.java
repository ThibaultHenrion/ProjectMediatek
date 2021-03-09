package com.anicetti.mediatek.services;

import com.anicetti.mediatek.services.auth.TokenGenerator;
import com.anicetti.mediatek.services.auth.TokenRuntimeRegistry;
import mediatek2021.Mediatek;
import mediatek2021.Utilisateur;

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

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("adohizaazpihdpihadzapidhazpdihzad");
        Mediatek md = Mediatek.getInstance();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Utilisateur usr = md.getUser(login, password);

        // REMOVE THIS ASAP
        if(login.equals("admin") && password.equals("admin")) {
            String token = TokenGenerator.generateNewToken();
            TokenRuntimeRegistry.addToken(token);

            HttpSession session = req.getSession();
            session.setAttribute("token", token);

            resp.sendRedirect("/documents");

        } else if(usr == null) {
            req.setAttribute("login_error", "Either login or password is wrong.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    public void destroy() {
    }
}