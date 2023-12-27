package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankAccount;

@WebServlet("/deposit")
public class Deposit extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt=req.getParameter("amt");
		double amount=Double.parseDouble(amt);
		long acno=(long) req.getSession().getAttribute("acno");
		BankDao bankDao=new BankDao();
		BankAccount bankAccount=bankDao.fetch_account_details(acno);
		bankAccount.setAmount(bankAccount.getAmount()+amount);
		bankDao.update_the_datails(bankAccount);
		resp.getWriter().print("<h1>Amount deposited successfully</h1>");
		
	}
}
