import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class Form extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>The form data filled by you are as follows:</h1><br/>");

        String htmlResponse = "<html>";

        //email
        String email = request.getParameter("email");
        htmlResponse += "<b>Your email is: </b>" + email + "<br/>";

        //password
        String password = request.getParameter("password");
        htmlResponse += "<b>Your password is: </b>" + password + "<br/>";

        //Upload the picture
        String pic_path = request.getParameter("upload-picture");
        
        htmlResponse +="<b> Picture path is: </b>" + pic_path + "<br/>";

        //Gender
        String gender = request.getParameter("gender");
        htmlResponse += "<b>Your gender is: </b>" + gender + "<br/>";

        //Country
        String country = request.getParameter("country");
        htmlResponse += "<b>Your country is: </b>" + country + "<br/>";

        //Hobbies   
        String hobbies[] = request.getParameterValues("hobby");
        if (hobbies != null) {
            for (String hobby : hobbies) {
                htmlResponse += "<b>Your hobby is: </b>" + hobby + "<br/>";
            }
        }

        //Address
        String address = request.getParameter("address");
        htmlResponse += "<b>Your address is: </b>" + address + "<br/>"; 
        htmlResponse += "</html>";

        out.println(htmlResponse);

    }
}