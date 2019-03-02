package solutions.notes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/solutions.notes/NewNote")
public class NewNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		int ownerid = Integer.parseInt(request.getParameter("ownerid"));
		
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
		out.println("        <title>NewNote</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		// create a form for the data entry
		out.println("<form action=\"NewNote\" method=\"post\">");
		out.println("	<input type=\"text\" name=\"title\" placeholder=\"Enter the tittle for you note\">");
		out.println("	<br>");		
		out.println("	<textarea name=\"message\" placeholder=\"Enter the tittle for you note\" ></textarea>");
		out.println("	<br>");
		out.println("   <input type=\"hidden\" name=\"ownerid\" value=\""+ownerid+"\">");
		out.println("	<input type=\"submit\" value=\"NewNote\">");
		out.println("</form>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	
	
	}

	

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//HttpSession session = request.getSession();
			
		String title = request.getParameter("title");
		
		// Get the user's message
		String message = request.getParameter("message");
		
		
//				if (title == null || title.trim().length() == 0 || message == null || message.trim().length() == 0) {
//					doGet(request, response);
//				}		
//				else {		
	
		
		// Get a reference to the Note in the servlet context
		
		
		int ownerid = Integer.parseInt(request.getParameter("ownerid"));
		
		ArrayList<Note> notes = (ArrayList<Note>) getServletContext().getAttribute("notes");
		// Add a new entry to the note
		notes.add(new Note(ownerid, title , message));
		
		request.getSession().setAttribute("notes",notes);
		
		// Redirect the User back to the main page
		response.sendRedirect("Main");
	//}
	}
	}
