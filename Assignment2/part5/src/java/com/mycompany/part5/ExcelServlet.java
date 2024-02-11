/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author shubhamjain
 */
@WebServlet(name = "excel", value = "/Excelfile.xls")

public class ExcelServlet extends HttpServlet {
    
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
            
        try{

        // Read the Excel file
        String name = request.getParameter("excelFileName");
        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("/documents/") + name;
            FileInputStream file = new FileInputStream(new File(realPath));
            HSSFWorkbook hwbook = new HSSFWorkbook(file);
            HSSFSheet hsheet = hwbook.getSheetAt(0);
            
            Iterator<Row>  iterator = hsheet.iterator();

            out.println("<html><body><table>");

            while(iterator.hasNext()){

            Row row = iterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            out.println("<tr>");

            while(cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                switch(cell.getCellType()){
                    case STRING:
                        out.print("<td>" + cell.getStringCellValue() + "</td>");
                        break;

                    case NUMERIC:
                        out.print("<td>" + cell.getNumericCellValue() + "</td>");
                        break;
                }
            }
            out.println("</tr>");
        }

            out.println("</table></body></html>");

            hwbook.close();
            file.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
            }
    }
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        handleRequest(request,response);
    }

}
