import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class FormGetParameterNames extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>The form data filled by you are as follows (through getParameterNames()):</h1><br/>");

        String htmlResponse = "<html>";

        Enumeration<String> parameterNames = request.getParameterNames();

        // Iterate through parameter names
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            if ("confirm-password".equals(paramName)) {
                continue;
            }

            String[] paramValues = request.getParameterValues(paramName);

            // Iterating through multiple values
            if (paramValues.length > 1) {
                for (String paramValue : paramValues) {
                    htmlResponse += "<b>Your " + paramName + " is: </b> " + paramValue + "<br/>";
                }
            } else {
                htmlResponse += "<b>Your " + paramName + " is: </b>" + paramValues[0] + "<br/>";
            }
        }

        htmlResponse += "</html>";
        out.println(htmlResponse);
    }

}
