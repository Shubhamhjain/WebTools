/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part4;

/**
 *
 * @author shubhamjain
 */
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "csv", urlPatterns = "/csv.xls")
public class CsvJdbc extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String csvFileName = request.getParameter("csvFileName");

        try{
            Class.forName("org.relique.jdbc.csv.CsvDriver");

            Connection connection = DriverManager.getConnection("jdbc:relique:csv:/Users/shubhamjain/NetBeansProjects/part4/documents");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + csvFileName);
            ResultSetMetaData data = resultSet.getMetaData();

            int columns = data.getColumnCount();

            out.println("<html>");
            out.println("<body>");
            out.println("<table>");

            out.println("<tr>");

            for(int i=1; i<=columns; i++) {
                out.println("<th>"+data.getColumnName(i)+"</th>");
            }
            out.println("</tr>");

            while(resultSet.next()) {
                out.println("<tr>");
                for(int i=1; i<=columns; i++) {
                    out.println("<td>"+resultSet.getString(i)+"</td>");
                }
                out.println("</tr>");

            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");    

            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(Exception  e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());

        }
    }
}

