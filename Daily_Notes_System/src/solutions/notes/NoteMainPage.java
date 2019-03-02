package solutions.notes;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/solutions.notes/Main")
public class NoteMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the session
		HttpSession session = request.getSession();
		
		// Get a reference to the NoteUser object stored in the session
		NoteUser user = (NoteUser) session.getAttribute("user");
		
		// If the User doesn't exist, then they didn't login. So, kick them back to Login
		if (user == null) {
			return;
		}
		
		// Get a reference to the notes array list
		ArrayList<Note> allNotes = (ArrayList<Note>) getServletContext().getAttribute("notes");
		
		//new construct a list of notes that belong to the currant user
		ArrayList<Note> notes =new ArrayList<Note>();
		for(Note note : allNotes) {
			if (note.getOwnerId() == user.getId()) {
				notes.add(note);
			}
				
			}
		// Get a reference to the current note (if applicable)
		Note currentNote = null;
		try {
			int noteId = Integer.parseInt(request.getParameter("noteId"));
			for(Note note : notes) {
				if (note.getId() == noteId) {
					currentNote = note;
					break;
				}
			}
		}catch(Exception e) {}
		
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
		out.println("        <title>Notes</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<p class=\"text-right\"><a href=\"Logout\">Logout</a></p>");
		out.println("<h1 class=\"display-2\">" + user.getFirst() + "'s Notes</h1>");
		out.println("<div class=\"row\">");
		out.println("	<div class=\"col-4\">");
		
		// Display all notes (that belong to the current user
		if (notes.size() == 0) {
			out.println("<p>No notes to display</p>");
		}
		out.println("<ul class=\"list-group\">");		
		for(Note note : notes) {
		if (note.getOwnerId() == user.getId()) {
				if (currentNote == note)
					out.println("<a class=\"list-group-item list-group-item-action active\" href=\"Main?noteId=" + note.getId() + "\">" + note.getTitle() + "</a>");
				else
					out.println("<a class=\"list-group-item list-group-item-action\" href=\"Main?noteId=" + note.getId() + "\">" + note.getTitle() + "</a>");
		}
		}
		out.println("</ul>");
		
		
		out.println("	<div>");		
		out.println("<a href=\"NewNote?ownerid="+user.getId()+"\"class=\"card-link\">New Note</a>");
		out.println("	</div>");
	
		out.println("	</div>");
		
		out.println("	<div class=\"col\">");
		
		
		
		if (currentNote == null) {
			out.println("<p class=\"lead\">Please create or select a note to display.</p>");
		}else {
			out.println("		<div class=\"card\">");
			out.println("  			<div class=\"card-body\">");
			out.println("    			<h5 class=\"card-title\">" + currentNote.getTitle() + "</h5>");
			out.println("    			<h6 class=\"card-subtitle mb-2 text-muted\">" + currentNote.getCreated() + "</h6>");
			out.println("	 			<p class=\"card-text\">" + currentNote.getText() + "</p>");
			out.println("				<a href=\"EditNote?id="+currentNote.getId()+"&ownerid="+currentNote.getOwnerId()+"\" class=\"card-link\">Edit</a>");
			out.println("				<a href=\"DeleteNote?id="+currentNote.getId()+"&ownerid="+currentNote.getOwnerId()+"\"class=\"card-link\">Delete</a>");
			out.println("  			</div>");
			out.println("		</div>");
		}
		out.println("	</div>");
		out.println("</div>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
 