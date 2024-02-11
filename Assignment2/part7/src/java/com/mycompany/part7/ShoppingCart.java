/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author shubhamjain
 */
@WebServlet(name = "cart", value = "/cart")

public class ShoppingCart extends HttpServlet {     
      
  protected void loginRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            passwordPopup(response);
        } else {
            String userInfo = authorization.substring(6).trim();
            System.out.println("user= " + userInfo);
        
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(userInfo);
            String namePassword = new String(decodedBytes, "UTF-8");
            
            int index = namePassword.indexOf(":");
            String user = namePassword.substring(0, index);
            String password = namePassword.substring(index + 1);

            if (isValidUserAndPassword(user, password)) {
                processShopping(request, response);
            } else {
                passwordPopup(response);
            }
        } catch (UnsupportedEncodingException e) {
            // Handle encoding exception
            e.printStackTrace();
        }
    }
}
    private void passwordPopup(HttpServletResponse response) {
        response.setStatus(response.SC_UNAUTHORIZED); // I.e., 401
        response.setHeader("WWW-Authenticate",
                "BASIC");
    }
    
    private boolean isValidUserAndPassword(String user, String password) {
        return "admin".equals(user) && "admin".equals(password);
}
    
    
    protected void processShopping(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Shopping Application</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Categories</h2>");
        out.println("<ul>");
        out.println("<li><a href=\"books.html\">Books</a></li>");
        out.println("<li><a href=\"music.html\">Music</a></li>");
        out.println("<li><a href=\"computers.html\">Computers</a></li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        
        // gets the current session
        HttpSession session = request.getSession(true);

        // selected items contain all the items checked by the user
        String[] selectedItems = request.getParameterValues("item");

        // we define a list of strings to store the cart items
        List<String> cartItems = (List<String>) session.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // Add selected items to the cart
        if (selectedItems != null) {
            for (String item : selectedItems) {
                cartItems.add(item);
            }
        }

        // Handle item removal
        handleItemRemoval(request);

        // Update the session with the modified cart
        session.setAttribute("cartItems", cartItems);

        displayUpdatedCart(request, response);

    }
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    loginRequest(request, response);
}
     
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    loginRequest(request, response);
}
     
     
     private void handleItemRemoval(HttpServletRequest request) {
        // Handle item removal
        String removeItem = request.getParameter("remove");

        if (removeItem != null) {
            HttpSession session = request.getSession(true);
            List<String> cartItems = (List<String>) session.getAttribute("cartItems");

            // Remove the selected item from the cart
            if (cartItems != null) {
                cartItems.remove(removeItem);
                session.setAttribute("cartItems", cartItems);
            }
        }
    }
     
     private void displayUpdatedCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<String> cartItems = (List<String>) session.getAttribute("cartItems");

        // Display the updated cart
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("The following items are in your shopping cart: ");
        out.println("<ul>");

        if (cartItems != null && !cartItems.isEmpty()) {
            for (String item : cartItems) {
                out.println("<li>" + item + " <a href='cart?remove=" + URLEncoder.encode(item, "UTF-8") + "'>Remove</a></li>");
            }
        } else {
            out.println("<li>No items in the cart</li>");
        }

        out.println("</ul>");

        out.println("</body></html>");
    }
         
   }

