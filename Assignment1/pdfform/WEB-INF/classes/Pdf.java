import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class Pdf extends HttpServlet{
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String htmlResponse = "<html>";
        htmlResponse +="<body>";
        htmlResponse +="<h1 style=\"text-align:center;\"> Tuition Waiver Form </h1>";

        //Section 1
        htmlResponse += "<h2 style=\"color:red;\"> Section 1 </h2>";
        String a_term = request.getParameter("term");
        htmlResponse += "<b>Academic Term: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + a_term + "</span>";
        String a_year = request.getParameter("year");
        htmlResponse += "<b style=\"margin-left: 20px;\">Academic Year: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + a_year + "</span>";
        String status = request.getParameter("status");
        htmlResponse += "<b style=\"margin-left: 20px;\">Employee status: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + status + "</span>" + "<br/>";

        //Section 2
        htmlResponse += "<h2 style=\"color:red;\"> Section 2 </h2>";

        String student_name = request.getParameter("sname");
        htmlResponse += "<b> Student name: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + student_name + "</span>";
        String employee_relation = request.getParameter("relation");
        htmlResponse += "<b style=\"margin-left: 20px;\">Relationship to Employee: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + employee_relation + "</span>";
        String student_nuid = request.getParameter("snuid");
        htmlResponse += "<b style=\"margin-left: 20px;\">Student's NUID: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + student_nuid + "</span>" + "<br/>" + "<br/>";

        String employee_name = request.getParameter("ename");
        htmlResponse += "<b>Employee's name(if diff from student): </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + employee_name + "</span>";
        String employee_nuid = request.getParameter("enuid");
        htmlResponse += "<b style=\"margin-left: 20px;\">Employee's NUID: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + employee_nuid + "</span>" + "<br/>" + "<br/>";

        String dept = request.getParameter("dept");
        htmlResponse += "<b>Department: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 10%;\">" + dept + "</span>";
        String loc = request.getParameter("loc");
        htmlResponse += "<b style=\"margin-left: 20px;\">Campus Location: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + loc + "</span>";
        String number = request.getParameter("number");
        htmlResponse += "<b style=\"margin-left: 20px;\">Phone Number: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + number + "</span>";
        String supervisor_name = request.getParameter("supervisor");
        htmlResponse += "<b style=\"margin-left: 20px;\">Supervisor's name: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + supervisor_name + "</span>" + "<br/>" + "<br/>";

        //Section 3
        htmlResponse += "<h2 style=\"color:red;\"> Section 3 </h2>";

        String school = request.getParameter("s3");
        htmlResponse += "<b>Applicable School: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 10%;\">" + school + "</span>" + "<br/>" + "<br/>";

        int numRows = 4;
        htmlResponse += "<div>";

        // Row 1 (headers)
        String[] headers = {"Course No.", "Course Name", "Supervisor's Signature", "Credit Hrs", "Day(s)", "Time"};
        String[] field_name = {"courseno", "coursename", "ssign", "credithrs", "days", "time"};
        int[] widths = {15, 15, 20, 15, 15, 15};

        for (int i = 0; i < headers.length; i++) {
            htmlResponse += "<div style=\"display: inline-block; width: " + widths[i] + "%;\">" + "<b>" + headers[i] + "</b>" + "</div>";
        }

       for (int row = 1; row < numRows; row++) {
            for (int i = 0; i < headers.length; i++){
                String paramName = "row" + (row + 1) + "_" + field_name[i];
                String input_field_data = request.getParameter(paramName);
                htmlResponse += "<div style=\"display: inline-block; width: " + widths[i] + "%;\">" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + input_field_data + "</span>"+ "</div>";
                }
       }

        //Section 4
        htmlResponse += "<h2 style=\"color:red;\"> Section 4 </h2>";
        String employee_signature = request.getParameter("esign");
        htmlResponse += "<b>Employee's signature: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + employee_signature + "</span>";
        String employee_date = request.getParameter("edate");
        htmlResponse += "<b style=\"margin-left: 20px;\">Date: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">"+ employee_date + "</span>" + "<br/>";

        //Section 5
        htmlResponse += "<h2 style=\"color:red;\"> Section 5 </h2>";
        String hrm_signature = request.getParameter("hrmsign");
        htmlResponse += "<b>HRM approval: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">"+hrm_signature+"</span>";
        String hrm_date = request.getParameter("hrmdate");
        htmlResponse += "<b style=\"margin-left: 20px;\"> Date: </b>" + "<span style=\"border-bottom: 2px solid black; display: inline-block; width: 5%;\">" + hrm_date + "</span>";

        htmlResponse += "</body>";
        htmlResponse += "</html>";

        out.println(htmlResponse);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }
}