package solutions.notes;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/solutions.notes/DeleteNote")
public class DeleteNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
        	// get a reference of notes to array list entries
   			ArrayList<Note> allnotes = (ArrayList<Note>) getServletContext().getAttribute("notes");
   			
   			
   			//Get the id of the note removed
   			int id=Integer.parseInt(request.getParameter("id"));
   			int ownerid=Integer.parseInt(request.getParameter("ownerid"));
   			
   			//Locate the entry to remove it
   			for(Note note : allnotes){
   				if(note.getId() == id && note.getOwnerId() == ownerid){
   					allnotes.remove(note);
   					break;
   				}
   			}
   			//send (redirect) the user back to the main GuestBook page
   			response.sendRedirect("Main");
   		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
