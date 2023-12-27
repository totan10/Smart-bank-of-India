package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankAccount;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt=req.getParameter("amt");
		double amount=Double.parseDouble(amt);
		long acno=(long) req.getSession().getAttribute("acno");
		BankDao bankDao=new BankDao();
		BankAccount bankAccount=bankDao.fetch_account_details(acno);
		if(bankAccount.getAmount()<amount) {
			resp.getWriter().print("<h1>Insufficient balance</h1>");
			req.getRequestDispatcher("withdraw.html").include(req, resp);
		}
		else {
			bankAccount.setAmount(bankAccount.getAmount()- amount);
			bankDao.update_the_datails(bankAccount);
			resp.getWriter().print("<h1>Amount has been withdrwan successfully</h1>");
		}
		
	}
}