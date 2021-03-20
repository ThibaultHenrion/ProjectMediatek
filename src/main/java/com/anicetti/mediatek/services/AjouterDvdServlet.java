package com.anicetti.mediatek.services;

import com.anicetti.mediatek.services.auth.TokenRuntimeRegistry;
import mediatek2021.Mediatek;
import mediatek2021.NewDocException;

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
        response.setContentType("text/html");
        if(session != null) {
            String token = (String) session.getAttribute("token");

            if(token != null && TokenRuntimeRegistry.isValid(token)) {
                request.getRequestDispatcher("/ajouter_dvd.jsp").forward(request, response);
            } else {
                response.sendRedirect("/login");
            }
        } else {
            response.sendRedirect("/login");
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
                String nom = request.getParameter("nom");
                String auteur = request.getParameter("auteur");
                String genre = request.getParameter("genre");
                boolean estAdulte = request.getParameter("est_adulte") != null;
                int type = 1;

                try {
                    md.newDocument(type, nom, auteur, genre, estAdulte);
                    response.sendRedirect("/documents");
                } catch (NewDocException e) {
                    request.setAttribute("erreur_ajout", e.getMessage());
                    request.getRequestDispatcher("/ajouter_dvd.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("/login");
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    public void destroy() { }
}