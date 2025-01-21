package session;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/servlet1")
public class Request1Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username ="sisir";
		
		Cookie cookie = new Cookie("username", username);
		cookie.setMaxAge(5*60);
		resp.addCookie(cookie);
		
		//HTTP SESSION -> Setting Data in HTTP Session.
		HttpSession session = req.getSession();
		session.setAttribute("userCode", UUID.randomUUID().toString());
		session.setMaxInactiveInterval(10*60);
		
		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Cookie set successfully.</h1>");
		
		
		
	}

	
	
}
