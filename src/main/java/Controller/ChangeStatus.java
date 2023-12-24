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

@WebServlet("/Changestatus")
public class ChangeStatus extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acno=req.getParameter("accno");
		long accno=Long.parseLong(acno);
		BankDao bankDao=new BankDao();
		BankAccount bankAccount=bankDao.fetch_account_details(accno);
		if(bankAccount.isStatus()) {
			bankAccount.setStatus(false);
		}
		else {
			bankAccount.setStatus(true);
		}
		bankDao.update_the_datails(bankAccount);
		resp.getWriter().print("<h1>Status got updated</h1>");
		
		//Here we are going to take the updated information from BankAccount table
		
		List<BankAccount> list=bankDao.fetchAll();
		req.getSession().setAttribute("list", list);
		req.getRequestDispatcher("adminhome.jsp").include(req, resp);
		
	}
}
