import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class FormGetParameterMap extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>The form data filled by you are as follows (through getParameterMap()):</h1><br/>");
        String htmlResponse = "<html>";

        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            String parameterName = entry.getKey();
            String[] parameterValues = entry.getValue();

            if ("confirm-password".equals(parameterName)) {
                continue;
            }
            // Iterating through multiple values
            if (parameterValues.length > 1) {
                for (String parameterValue : parameterValues) {
                    htmlResponse += "<b>Your " + parameterName + " is: </b> " + parameterValue + "<br/>";
                }
            } else {
                htmlResponse += "<b>Your " + parameterName + " is: </b>" + parameterValues[0] + "<br/>";
            }
        }
        htmlResponse += "</html>";
        out.println(htmlResponse);

    }

}