package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankAccount;
import Dto.BankTransaction;

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
		
		

		BankTransaction bankTransaction=new BankTransaction();
		bankTransaction.setBalance(bankAccount.getAmount());
		bankTransaction.setDateTime(LocalDateTime.now());
		bankTransaction.setDeposit(amount);
		bankTransaction.setWithdraw(0);
		
		List<BankTransaction> list=bankAccount.getList();
		list.add(bankTransaction);
		bankAccount.setList(list);
		
		bankDao.update_the_datails(bankAccount);
		resp.getWriter().print("<h1>Amount deposited successfully</h1>");
		req.getRequestDispatcher("accounthome.html").include(req, resp);
		
	}
}
