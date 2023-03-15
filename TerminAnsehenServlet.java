package hbv;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/termin")
public class TerminAnsehenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession(false);
		
		if (session == null) {
		
			response.sendRedirect("login.html");
			return;
		}

		String username = (String) session.getAttribute("username");

		try 
		{
			
			Connection con = DatabaseConnection.getConnection();

			
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM appointmentsApp WHERE username=?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			// Display the appointments in an HTML table
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Meine Termine</title>");
			out.println("<style>\n"
					+ "    table {\n"
					+ "        border-collapse: collapse;\n"
					+ "        width: 100%;\n"
					+ "        font-family: Arial, sans-serif;\n"
					+ "        font-size: 14px;\n"
					+ "    }\n"
					+ "\n"
					+ "    th, td {\n"
					+ "        text-align: left;\n"
					+ "        padding: 8px;\n"
					+ "        border-bottom: 1px solid #ddd;\n"
					+ "    }\n"
					+ "\n"
					+ "    th {\n"
					+ "        background-color: #008CBA;\n"
					+ "        color: white;\n"
					+ "    }\n"
					+ "\n"
					+ "    tr:nth-child(even) {\n"
					+ "        background-color: #f2f2f2;\n"
					+ "    }\n"
					+ "</style>\n"
					+ "");
			out.println("</head>");
			
			out.println("<body>");
			out.println("<h1>Meine Termine</h1>");
			out.println("<table border=\"1\">");
			out.println("<tr>");
			out.println("<th>Datum Ersten Termin</th>");
			out.println("<th>Uhrzeit</th>");
			out.println("<th>Datum zweiten Termin</th>");
			out.println("<th>Uhrzeit</th>");
			out.println("<th>Impfsoff</th>");
			out.println("<th>Termin stornieren</th>");
			out.println("</tr>");

			while (rs.next()) {
				String date1 = rs.getString("date1");
				String heure1 = rs.getString("heure1");
				String date2 = rs.getString("date2");
				String heure2 = rs.getString("heure2");
				String vaccine = rs.getString("vaccine");
				int id = rs.getInt("id");

				out.println("<tr>");
				out.println("<td>" + date1 + "</td>");
				out.println("<td>" + heure1 + "</td>");
				out.println("<td>" + date2 + "</td>");
				out.println("<td>" + heure2 + "</td>");
				out.println("<td>" + vaccine + "</td>");
				out.println("<td>");
				out.println("<form action=\"deleteVrai\" method=\"POST\">");
				out.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
				out.println("<input type=\"submit\" value=\"stornieren\">");
				out.println("</form>");
				out.println("</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

			DatabaseConnection.releaseConnection(con);
      //response.sendRedirect("centralVrai");

		} catch (Exception e) {
			response.getWriter().println("Erreur : " + e.getMessage());
		}
	}
}


