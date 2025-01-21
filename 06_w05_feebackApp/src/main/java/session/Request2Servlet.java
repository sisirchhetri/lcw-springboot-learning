package session;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@SuppressWarnings("serial")
@WebServlet("/servlet2")
public class Request2Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter printWriter = resp.getWriter();
		
		Cookie[] cookieList = req.getCookies();
		var isUserPresentFlag = false;
		
		HttpSession session = req.getSession();
		String code = (String) session.getAttribute("userCode");
		
		
		if(null != cookieList) {
			for(Cookie cookie : cookieList) {
				
				if(cookie.getName().equals("username")) {
					printWriter.println(
							"""							
							<h1>Welcome : %s </h1>
							<h1>Secert key : %s</h1>
							
							""".formatted(cookie.getValue(), code));
					System.out.println(
							String.format("Cookie Key -> %s %n Value : %s", 
										  "username", cookie.getValue())
							);
					isUserPresentFlag = true;
				}
				
			}
		}
		
		
		
		if(!isUserPresentFlag) {
			printWriter.println("""
					<h1>User Not Present </h1>
					<h1>Username Cookie Doesn't Exists</h1>
					""");
		}
		
		
	}

	
	
}
