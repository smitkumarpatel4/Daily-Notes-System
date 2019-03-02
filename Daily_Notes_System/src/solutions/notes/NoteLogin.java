package solutions.notes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/solutions.notes/Login")
public class NoteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);		
	}
	
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		// Set the content type to HTML
    		response.setContentType("text/html");
    		
    		// Get a Print Writer
    		PrintWriter out = response.getWriter();
    		
    		// Generate the template HTML
    		out.println("<!DOCTYPE html>");		
    		out.println("<html lang=\"en\">");
    		out.println("<head>");
    		out.println("    <meta charset=\"UTF-8\">");
    		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
    		out.println("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
    		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
    		out.println("        <title>Notes Login</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<div class=\"container\">");
    		
    		out.println("<h1 class=\"display-1\">Login</h1>");
    		
    		out.println("<form action=\"Login\" method=\"post\">");
    		
    		if (request.getAttribute("loginError") != null) {
    			out.println("<p class=\"text-light bg-danger\">Invalid username and/or password.</p>");
    		}
    		
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"email\">E-mail Address</label>");
    		out.println("        <input class=\"form-control\" type=\"email\" name=\"email\" id=\"email\" placeholder=\"Enter your e-mail address\">");
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"password\">Password</label>");
    		out.println("        <input class=\"form-control\" type=\"password\" name=\"password\" id=\"password\" placeholder=\"Enter your password\">");
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <button type=\"submit\" class=\"btn btn-primary\">Login</button>");
    		out.println("    </div>");
    		out.println("</form>");
    		out.println("<p>Don't have an account? <a href=\"Register\">Sign-Up!</a></p>");
    		
    		out.println("</div>");
    		out.println("</body>");
    		out.println("</html>");
	}

    	@SuppressWarnings("unchecked")
		protected NoteUser getUser(String email, String password) {
    		ArrayList<NoteUser> users = (ArrayList<NoteUser>) getServletContext().getAttribute("users");
    		  for(NoteUser user: users)
    			  if( user.getEmail().equals(email)&& user.getPassword().equals( password) )
    				  return user;
     		
    		return null;
    	}
    	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		// find user
		NoteUser user = getUser(email, password);
		
		if (user == null) {
			request.setAttribute("loginError", true);
			doGet(request, response);			
		} 
		else {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("Main");
		}
	}

}
