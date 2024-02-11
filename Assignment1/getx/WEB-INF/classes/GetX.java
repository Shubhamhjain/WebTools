import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

public class GetX extends HttpServlet{
    	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<HTML>");
            out.println("<BODY>");

            out.println("<h2> HttpServletRequest GetX() </h2>");
            out.println("<b> getAuthType : </b>" + request.getAuthType() + "<br/>");
            out.println("<b> getContextPath : </b>" + request.getContextPath() + "<br/>");
            out.println("<b> getCookies : </b>" + request.getCookies() + "<br/>");
            out.println("<b> getDateHeader : </b>" + request.getDateHeader("If-Modified-Since") + "<br/>");
            out.println("<b> getMethod : </b>" + request.getMethod() + "<br/>");  
            out.println("<b> getPathInfo : </b>" + request.getPathInfo() + "<br/>");
            out.println("<b> getPathTranslated : </b>" + request.getPathTranslated() + "<br/>");
            out.println("<b> getQueryString : </b>" + request.getQueryString() + "<br/>");
            out.println("<b> getRemoteUser : </b>" + request.getRemoteUser() + "<br/>");
            out.println("<b> getRequestedSessionId : </b>" + request.getRequestedSessionId() + "<br/>");
            out.println("<b> getRequestURI : </b>" + request.getRequestURI() + "<br/>");
            out.println("<b> getRequestURL : </b>" + request.getRequestURL() + "<br/>");
            out.println("<b> getServletPath : </b>" + request.getServletPath() + "<br/>");
            out.println("<b> getSession : </b>" + request.getSession() + "<br/>");
            out.println("<b> getHttpServletMapping : </b>" + request.getHttpServletMapping() + "<br/>");
            
            out.println("<b>Headers:</b>");
            out.println("<ul>");
            Enumeration<String> headerNames = request.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String hName = headerNames.nextElement();
                out.print("<li><b>" + hName + " : </b>");  
                Enumeration<String> hValue = request.getHeaders(hName);
                while(hValue.hasMoreElements()){
                    out.println(hValue.nextElement() + "<br/>");  
                }
                out.println("</li>");
            }
            out.println("</ul>");

            out.println("<h2> ServletRequest GetX() </h2>");
            out.println("<b> getCharacterEncoding : </b>" + request.getCharacterEncoding() + "<br/>");
            out.println("<b> getContentLength : </b>" + request.getContentLength() + "<br/>");
            out.println("<b> getContentLengthLong : </b>" + request.getContentLengthLong() + "<br/>");
            out.println("<b> getContentType : </b>" + request.getContentType() + "<br/>");
            out.println("<b> getLocalAddr : </b>" + request.getLocalAddr() + "<br/>");
            out.println("<b> getLocalName : </b>" + request.getLocalName() + "<br/>");
            out.println("<b> getLocalPort : </b>" + request.getLocalPort() + "<br/>");
            out.println("<b> getProtocol : </b>" + request.getProtocol() + "<br/>");
            out.println("<b> getRemoteAddr : </b>" + request.getRemoteAddr() + "<br/>");
            out.println("<b> getRemoteHost : </b>" + request.getRemoteHost() + "<br/>");
            out.println("<b> getRemotePort : </b>" + request.getRemotePort() + "<br/>");
            out.println("<b> getScheme : </b>" + request.getScheme() + "<br/>");
            out.println("<b> getServerName : </b>" + request.getServerName() + "<br/>");
            out.println("<b> getServerPort : </b>" + request.getServerPort() + "<br/>");
            out.println("<b> getLastModified : </b>" + getLastModified(request) + "<br/>");
          
            out.println("</BODY>");
            out.println("</HTML>");

        }
}