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

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amt=req.getParameter("amt");
		double amount=Double.parseDouble(amt);
		long acno=(long) req.getSession().getAttribute("acno");
		BankDao bankDao=new BankDao();
		BankAccount bankAccount=bankDao.fetch_account_details(acno);
		if(bankAccount.getAmount()<amount) {
			resp.getWriter().print("<h1>Insufficient balance. Your available balance is: "+bankAccount.getAmount()+"</h1>");
			req.getRequestDispatcher("withdraw.html").include(req, resp);
		}
		else {
			if(amount>bankAccount.getAcc_limit()) {
				resp.getWriter().print("<h1>You are exceeding actual account limit.Your account limit is: "+bankAccount.getAcc_limit()+"</h1>");
				req.getRequestDispatcher("withdraw.html").include(req, resp);
			}
			else {
				bankAccount.setAmount(bankAccount.getAmount()- amount);
				
				BankTransaction bankTransaction=new BankTransaction();
				bankTransaction.setBalance(bankAccount.getAmount());
				bankTransaction.setDateTime(LocalDateTime.now());
				bankTransaction.setDeposit(0);
				bankTransaction.setWithdraw(amount);
				
				List<BankTransaction> list=bankAccount.getList();
				list.add(bankTransaction);
				bankAccount.setList(list);
				bankDao.update_the_datails(bankAccount);
				resp.getWriter().print("<h1>Amount has been withdrwan successfully</h1>");
				req.getRequestDispatcher("accounthome.html").include(req, resp);
			}
			
		}
	}	
}
