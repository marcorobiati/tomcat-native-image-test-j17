package com.mycompany.tomcatnativeimagetest.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ToPageJspServlet", urlPatterns = {"/ToPageJspServlet"})
public class ToPageJspServlet extends HttpServlet {

    {
        System.out.println("ToPageJspServlet lone block");
    }

    static {
        System.out.println("ToPageJspServlet static block");
    }

    public ToPageJspServlet() {
        super();
        System.out.println("ToPageJspServlet constructor.");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("page.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
