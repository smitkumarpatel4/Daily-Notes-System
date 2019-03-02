package solutions.notes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/solutions.notes/Register"}, loadOnStartup=1)
public class NoteRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Create the Array of Users
		ArrayList<NoteUser> users = new ArrayList<NoteUser>();
		users.add(new NoteUser("Albert", "Cervantes", "acervan5@calstatela.edu", "abcd"));
		users.add(new NoteUser("John", "Doe", "john@doe.com", "abcd"));
		users.add(new NoteUser("Joe", "Boxer", "joe@boxer.com", "abcd"));
		users.add(new NoteUser("Mary", "Jane", "mary@jane.com", "abcd"));
		
		getServletContext().setAttribute("users", users);
		
		// Create the Array of Notes
		ArrayList<Note> notes = new ArrayList<Note>();
		
		notes.add(new Note(0, "Albert's 1st Note", "Hello from Albert's 1st note!"));
		notes.add(new Note(0, "Albert's 2nd Note", "Hello from Albert's 2nd note!"));
		
		notes.add(new Note(1, "John's 1st Note", "Hello from John's 1st note!"));
		notes.add(new Note(1, "John's 2nd Note", "Hello from John's 2nd note!"));
		
		notes.add(new Note(2, "Joe's 1st Note", "Hello from Joe's 1st note!"));
		notes.add(new Note(2, "Joe's 2nd Note", "Hello from Joe's 2nd note!"));
		
		notes.add(new Note(3, "Mary's 1st Note", "Hello from Mary's 1st note!"));
		notes.add(new Note(3, "Mary's 2nd Note", "Hello from Mary's 2nd note!"));
		
		getServletContext().setAttribute("notes", notes);
	}
	
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		// Do we have any errors?
    		String nameError = (String) request.getAttribute("nameError");
    		String emailError = (String) request.getAttribute("emailError");
    		String passwordError = (String) request.getAttribute("passwordError");
    		
    		String name = request.getParameter("name");
    		String email = request.getParameter("email");
    		String password1 = request.getParameter("password1");
    		String password2 = request.getParameter("password2");
    		
    		name = name == null ? "" : name;
    		email = email == null ? "" : email;
    		password1 = password1 == null ? "" : password1;
    		password2 = password2 == null ? "" : password2;
    		
    		
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
    		out.println("        <title>Notes Register</title>");
    		out.println("</head>");
    		out.println("<body>");
    		out.println("<div class=\"container\">");
    		
    		out.println("<h1 class=\"display-1\">Register</h1>");
    		
    		out.println("<form action=\"Register\" method=\"post\">");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"name\">Full Name</label>");
    		
    		
    		if (nameError != null) {
    			out.println("        <input class=\"form-control is-invalid\"  value=\"" + name + "\" type=\"text\" name=\"name\" id=\"name\" placeholder=\"Enter your first and last names\">");
    			out.println("        <div class=\"invalid-feedback\">" + nameError + "</div>");
    		}  			
    		else	
    			out.println("        <input class=\"form-control\" type=\"text\" value=\"" + name + "\" name=\"name\" id=\"name\" placeholder=\"Enter your first and last names\">");
    		
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"email\">E-mail Address</label>");
    		
    		
    		if (emailError != null) {
    			out.println("        <input class=\"form-control is-invalid\" value=\"" + email + "\" type=\"email\" name=\"email\" id=\"email\" placeholder=\"Enter your e-mail address\">");
    			out.println("        <div class=\"invalid-feedback\">" + emailError + "</div>");
    		}
    		else
    			out.println("        <input class=\"form-control\" value=\"" + email + "\" type=\"email\" name=\"email\" id=\"email\" placeholder=\"Enter your e-mail address\">");
    		
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"password\">Password</label>");
    		
    		
    		if (passwordError != null) {
    			out.println("        <input class=\"form-control is-invalid\" value=\"" + password1 + "\" type=\"password\" name=\"password1\" id=\"password1\" placeholder=\"Enter your password\">");
    			out.println("        <div class=\"invalid-feedback\">" + passwordError + "</div>");
     		}
    		else
    			out.println("        <input class=\"form-control\" value=\"" + password1 + "\" type=\"password\" name=\"password1\" id=\"password1\" placeholder=\"Enter your password\">");
    		
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <label for=\"password\">Re-Enter Password</label>");
    		out.println("        <input class=\"form-control\" value=\"" + password2 + "\" type=\"password\" name=\"password2\" id=\"password2\" placeholder=\"Re-enter your password\">");
    		out.println("    </div>");
    		out.println("    <div class=\"form-group\">");
    		out.println("        <button type=\"submit\" class=\"btn btn-primary\">Register</button>");
    		out.println("    </div>");
    		out.println("</form>");
    		out.println("<p>Already have an account? <a href=\"Login\">Login</a></p>");
    		
    		
    		out.println("</div>");
    		out.println("</body>");
    		out.println("</html>");
	}
	
	protected void displayWelcome(NoteUser user, HttpServletResponse response) throws ServletException, IOException {
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
		out.println("        <title>Success</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		out.println("<h1 class=\"display-1\">Welcome, " + user.getFirst() + "!</h1>");
		out.println("<p class=\"lead\">You have successfully registered!</p>");
		out.println("<a href=\"Login\" class=\"btn btn-primary\">Login</a>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public static boolean validateEmail(String email) {                 

        
	       boolean status=true;    
	       String Email=  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	       Pattern pattern = Pattern.compile(Email);
	       Matcher matcher=pattern.matcher(email);
	       if(matcher.matches())
	       {
	           status=false;
	       }
	       else{
	           status=true;
	       }
	           return status;
	            
	    }
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Parse the request parameters and validate
		String fullName = request.getParameter("name");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		
		String password2 = request.getParameter("password2");
		
		// Assume there are no errors to begin with
		boolean hasError = false;
				// validate name
				String firstName= null;
				String lastName= null;
				if (fullName.contains(" ")){
					// split full name in to first name and last name

					String[] name= fullName.split(" ");
					firstName= name[0];
					lastName= name[1];
				
					if(firstName == null || firstName.trim().length()== 0 ||lastName == null || lastName.trim().length()== 0  ){
						request.setAttribute("nameError", "Invalid Name");
						hasError = true;
						doGet(request, response);
					}
				
				}
				
				else{
					request.setAttribute("nameError", "Invalid Name");
					hasError = true;
					doGet(request, response);
				}
		
				// validate e-mail
				if (email == null || email.trim().length()==0 || validateEmail(email)){
					request.setAttribute("emailError","Invalid E-mail");
					hasError = true;
				}
				 
				
				// validate password
				if(password1 == null || password1.trim().length()==0 || password2 == null || password2.trim().length() == 0 || !password1.equals(password2)){
					request.setAttribute("passwordError","Invalid PassWords");
					hasError = true;
				}
				  								
		if (hasError) {
				doGet(request, response);
			}
		else {
			NoteUser newUser = new NoteUser(firstName, lastName, email, password1);
			ArrayList<NoteUser> users = (ArrayList<NoteUser>) getServletContext().getAttribute("users");
			users.add(newUser);
		 	displayWelcome(newUser, response);
		}
		}	
}
 