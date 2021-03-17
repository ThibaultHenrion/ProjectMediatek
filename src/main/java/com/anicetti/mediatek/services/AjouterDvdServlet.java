package com.anicetti.mediatek.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ajouterDvdServlet", value = "/ajouter_dvd")
public class AjouterDvdServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if(session != null) {
            String token = (String) session.getAttribute("token");

            
        }
    }

    /*private String message;

    public void init() {
        message = "Login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        request.getRequestDispatcher("/documents.jsp").forward(request, response);
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
            req.getRequestDispatcher("/documents.jsp").forward(req, resp);
        } else if(usr == null) {
            req.setAttribute("login_error", "Either login or password is wrong.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    public void destroy() {
    }*/
}