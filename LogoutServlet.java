package hbv;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

     response.setContentType("text/html");

     HttpSession session = request.getSession();
		
     session.invalidate(); // Supprime toutes les variables de session

		response.sendRedirect("index.html"); // Redirige vers la page de connexion
	}

}
