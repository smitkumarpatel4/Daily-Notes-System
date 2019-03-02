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


@WebServlet("/solutions.notes/EditNote")

public class EditNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected Note getNote(HttpServletRequest request){
		//Get the id of the entry we are editing
		int id=(int) Integer.parseInt(request.getParameter("id"));
			
		//Get a reference to the array list of entries
		ArrayList<Note> notes =(ArrayList<Note>) getServletContext().getAttribute("notes");
		
		//Find the entry
		for (Note note: notes)
			if(note.getId()==id)
				return note;				
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected NoteUser getNoteUser(HttpServletRequest request){
		//Get the id of the entry we are editing
		int ownerid = (int) Integer.parseInt(request.getParameter("ownerid"));
		
		//Get a reference to the array list of entries
		ArrayList<NoteUser> users = (ArrayList<NoteUser>)getServletContext().getAttribute("users");
		
		//Find the entry
		for (NoteUser user: users)
			if(user.getId()==ownerid)
				return user;				
		return null;
	}
	
	
       
  
	@SuppressWarnings({ "unchecked", "unused" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Get a reference to the NoteUser object stored in the session
		NoteUser user = getNoteUser(request);
		Note currentNote = getNote(request);
	
		
		// Get a reference to the notes array list
		ArrayList<Note> allNotes = (ArrayList<Note>) getServletContext().getAttribute("notes");
		
		int id=(int) Integer.parseInt(request.getParameter("id"));
		int ownerid = Integer.parseInt(request.getParameter( "ownerid"));	
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
		
		// Display current note notes (that belong to the current user)
		
		
		out.println();
		out.println("<ul class=\"list-group\">");		
		out.println("<a class=\"list-group-item list-group-item-action active\" href=\"Main?noteId=" + currentNote.getId() + "\">" + currentNote.getTitle() + "</a>");

		out.println("</ul>");
		
		
		out.println("	<div>");		
		out.println("<a href=\"NewNote?ownerid="+user.getId()+"\"class=\"card-link\">New Note</a>");
		out.println("	</div>");
	
		out.println("	</div>");
		//create a form to edit a current note
		out.println("<Form action= \"EditNote\" method= \"post\">");
		out.println("	<div class=\"col\">");

		out.println("		<div class=\"card\">");
		out.println("  			<div class=\"card-body\">");
		out.println( "			<input class=\"card-title\" type=\"text\" name=\"title\" value=\""+currentNote.getTitle()+"\" placeholder= \"Enter your name\">");
		out.println( "</h5>");
		out.println("    			<h6 class=\"card-subtitle mb-2 text-muted\">");
		
		out.println( "</h6>");
		out.println("			<textarea class=\"card-text\" name=\"text\">"+currentNote.getText()+ "</textarea>");
		out.println("   	<input type=\"hidden\" name=\"ownerid\" value=\""+ownerid+"\">");
		out.println("   	<input type=\"hidden\" name=\"id\" value=\""+id+"\">");
		
		out.println(" 			<input type=\"submit\" value=\"Save\"class=\"card-link\">");
		
		
		out.println("			<a href=\"DeleteNote?id="+currentNote.getId()+"&ownerid="+currentNote.getOwnerId()+"\"class=\"card-link\">Delete</a>");
		out.println("  			</div>");
		out.println("		</div>");
		out.println("	</div>");
		out.println("</Form>");
		
		
		out.println("</div>");
		out.println("</html>");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the name
		String title = request.getParameter("title");
		//get the message
		String text = request.getParameter("text");
		
		Note note = getNote(request);
		//get the entry in question
						
		//updating the entry
		note.setTitle(title);
		note.setText(text);
		
		response.sendRedirect("Main"); 

	}

}