package hbv;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteVrai")
public class DeleteTerminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession(false);
		
		if (session == null) {
			
			response.sendRedirect("login.html");
			return;
		}

		
		String username = (String) session.getAttribute("username");

		
		int id = Integer.parseInt(request.getParameter("id"));

		try 
		{
	
			Connection con = DatabaseConnection.getConnection();

			PreparedStatement stmt = con.prepareStatement("DELETE FROM appointmentsApp WHERE id=? AND username=?");
			stmt.setInt(1, id);
			stmt.setString(2, username);
			stmt.executeUpdate();

			DatabaseConnection.releaseConnection(con);

			
			String message = "Ihr Termin wurde erfolgreich storniert.";
			String redirectUrl = "termin";
			String script = "alert('" + message + "'); window.location.href='" + redirectUrl + "';";
			response.getWriter().println("<script>" + script + "</script>");

		} catch (Exception e) {
			response.getWriter().println("Erreur : " + e.getMessage());
		}
	}
}
