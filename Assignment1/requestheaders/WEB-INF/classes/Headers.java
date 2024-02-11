import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.util.*;  

public class Headers extends HttpServlet{
    	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();

            out.println("<html>");
            out.println("<body>");
            out.println("<h1>The request headers are:</h1><br/>");
            out.println("<ol>");
            Enumeration<String> hNames = req.getHeaderNames();
            while(hNames.hasMoreElements()){
                String hName = hNames.nextElement();
                out.print("<li><b>" + hName + " : </b>");  
                Enumeration<String> hValue = req.getHeaders(hName);
                while(hValue.hasMoreElements()){
                    out.println(hValue.nextElement() + "<br/>");  
                }
                out.println("</li>");
            }

            out.println("</ol>");
            out.println("</html>");
            out.println("</body>");

        }
}