package com.anicetti.mediatek.init;

import com.anicetti.mediatek.persistant.MediatekData;
import mediatek2021.Mediatek;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value="/initializeResources", loadOnStartup=1)
public class InjectionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        try {
            Class.forName("com.anicetti.mediatek.persistant.MediatekData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
