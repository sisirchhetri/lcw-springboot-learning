package session;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/servlet1")
public class Request1Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cookieList = req.getCookies();
		
		if(null != cookieList) {
			for(Cookie cookie : cookieList) {
				
				if(cookie.getName().equals("username")) {
					System.out.println(
							String.format("Cookie Key -> %s %n Value : %s", 
										  "username", cookie.getValue())
							);
				}
				
			}
		}
	}

	
	
}
