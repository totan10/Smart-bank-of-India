package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankAccount;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		
		BankDao bankDao=new BankDao();
		
		if(email.equals("admin@gmail.com")&&name.equals("admin")) {
			res.getWriter().print("<h1>Admin Login Successfull</h1>");
			List<BankAccount> list=bankDao.fetchAll();
			
			req.getSession().setAttribute("list", list);
			req.getRequestDispatcher("adminhome.jsp").include(req, res);
		}
		else {
			res.getWriter().print("<h1>Invalid Credentials</h1>");
		}
	}
	
}
