/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part_6;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shubhamjain
 */
@WebServlet(name = "pdfsigned", urlPatterns = "/pdfform")

public class TuitionWaiver extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String htmlResponse = "<html>";
        htmlResponse += "<body>";
        htmlResponse += "<h1 style=\"text-align:center;\"> Tuition Waiver Form </h1>";

        // Iterate over parameter names
        java.util.Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);

            // Append the parameter name and value to the HTML response
            htmlResponse += "<b>" + paramName + ": </b>" +
                            "<span>" + paramValue + "</span>" +
                            "<br/>";
        }

        htmlResponse += "</body>";
        htmlResponse += "</html>";

        out.println(htmlResponse);
    }
}