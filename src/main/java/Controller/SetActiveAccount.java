package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setActiveAccount")
public class SetActiveAccount extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acno=req.getParameter("accno");
		long accno=Long.parseLong(acno);
		
		req.getSession().setAttribute("acno", accno);
		req.getRequestDispatcher("accounthome.html").include(req, resp);
	}
}
