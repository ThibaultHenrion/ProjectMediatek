package com.anicetti.mediatek.services;

import com.anicetti.mediatek.services.auth.TokenRuntimeRegistry;
import mediatek2021.Mediatek;
import mediatek2021.NewDocException;
import mediatek2021.SuppressException;
import mediatek2021.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "documentsServlet", value = "/documents")
public class DocumentsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if(session != null) {
            String token = (String) session.getAttribute("token");

            response.setContentType("text/html");
            if(token != null && TokenRuntimeRegistry.isValid(token)) {
                Mediatek md = Mediatek.getInstance();

                request.setAttribute("liste_cd", md.catalogue(0));
                request.setAttribute("liste_dvd", md.catalogue(1));
                request.getRequestDispatcher("/documents.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");

        if(session != null) {
            String token = (String) session.getAttribute("token");

            if(token != null && TokenRuntimeRegistry.isValid(token)) {
                Mediatek md = Mediatek.getInstance();

                if(request.getParameter("action").equals("delete")) {
                    String id = request.getParameter("id");
                    try {
                        md.suppressDoc(Integer.parseInt(id));
                    } catch (SuppressException e) {
                        e.printStackTrace();
                    }
                }

                response.sendRedirect("/documents");

            } else {
                response.sendRedirect("/login");
            }
        } else {
            response.sendRedirect("/login");
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