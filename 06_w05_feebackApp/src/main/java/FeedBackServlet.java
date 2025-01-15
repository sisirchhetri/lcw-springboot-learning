import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Get Form Data
		String email = req.getParameter("email");
		String phoneNo = req.getParameter("phoneNumber");
		String feedback = req.getParameter("comments");
		
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
//		printWriter.println("<h1> Feedback Submitted </h1>");
//				printWriter.println(String.format("<h1> Feedback Submitted Via String Fomat</h1> "
//						+ "<h1> Email Id: %s </h1>"
//						+ "<h1> Phone No: %s </h1>"
//						+ "<h1> Feedback: %s </h1>",email, phoneNo, feedback));
				
		
		printWriter.println(String.format("""
				<h1> Feedback Submitted Via Jdk21 Text Blocks( Jdk 15 Feature)</h1> 
				<h1> Email Id: %s </h1>
				<h1> Phone No: %s </h1>
				<h1> Feedback: %s </h1>
				""",email, phoneNo, feedback));
		
//		resp.sendRedirect("/home");
		
					    
				
/*printWriter.println(
	    "<h1> Feedback Submitted </h1>\n" +
	    "<h1> Email Id: " + email + "</h1>\n" +
	    "<h1> Phone No: " + phoneNo + "</h1>\n" +
	    "<h1> Feedback: " + feedback + "</h1>"
	);
*/
		
	}
	
	
	

}
