package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dto.BankAccount;

@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acno1=(long) req.getSession().getAttribute("acno");
		
		String accno=req.getParameter("accno");
		long acno2=Long.parseLong(accno);
		
		String amt=req.getParameter("amt");
		double amount=Double.parseDouble(amt);
		
		BankDao bankDao=new BankDao();
		BankAccount bankAccount1=bankDao.fetch_account_details(acno1);
		
		BankAccount bankAccount2=bankDao.fetch_account_details(acno2);
		
		if(acno1==acno2) {
			resp.getWriter().println("<h1>You can't send money to the same account</h1>");
			req.getRequestDispatcher("transfer.html").include(req, resp);			
		}
		else {
			if(!bankAccount2.isStatus()) {
				resp.getWriter().println("<h1>Receiver's account is not activated</h1>");
				req.getRequestDispatcher("transfer.html").include(req, resp);
			}
			else {
				
				
				if(bankAccount1.getAmount()<amount) {
					resp.getWriter().print("<h1>Insufficient balance. Your available balance is: "+bankAccount1.getAmount()+"</h1>");
					req.getRequestDispatcher("transfer.html").include(req, resp);
				}
				else {
					if(amount>bankAccount1.getAcc_limit()) {
						resp.getWriter().print("<h1>You are exceeding actual account limit.Your account limit is: "+bankAccount1.getAcc_limit()+"</h1>");
						req.getRequestDispatcher("transfer.html").include(req, resp);
					}
					else {
						bankAccount1.setAmount(bankAccount1.getAmount()- amount);
						bankAccount2.setAmount(bankAccount2.getAmount()+ amount);
						bankDao.update_the_datails(bankAccount1);
						bankDao.update_the_datails(bankAccount2);
						resp.getWriter().print("<h1>Rs."+amount+" has been transferred to "+bankAccount2.getCustomer().getCname()+" successfully</h1>");
						resp.getWriter().print("<h1>"+bankAccount1.getCustomer().getCname()+"!! Your updated balance is: "+bankAccount1.getAmount()+"</h1>");
						req.getRequestDispatcher("accounthome.html").include(req, resp);
					}
				}
			}		
		}		
	}
}
