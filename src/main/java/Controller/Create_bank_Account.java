package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.BankDao;
import Dao.CustomerDao;
import Dto.BankAccount;
import Dto.Customer;

@WebServlet("/createbankaccount")
public class Create_bank_Account  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acc_type=req.getParameter("accounttype");
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		List<BankAccount> list=customer.getBankAccounts();
		
		boolean flag=true;
		for (BankAccount bankAccount : list) {
			if(bankAccount.getAccount_type().equals(acc_type)) {
				flag=false;
				break;
			}
		}
		if(flag) {
			BankAccount bankAccount=new BankAccount();
			bankAccount.setAccount_type(acc_type);
			
			if(bankAccount.getAccount_type().equals("saving")) 
				bankAccount.setAcc_limit(10000);
			else
				bankAccount.setAcc_limit(15000);
			
			bankAccount.setCustomer(customer);
			
			BankDao bankDao=new BankDao();
			bankDao.save_account(bankAccount);
			
			List<BankAccount> list2=list;
			list2.add(bankAccount);
			customer.setBankAccounts(list2);
			
			CustomerDao customerDao=new CustomerDao();
			customerDao.update(customer);
			resp.getWriter().print("<h1>Congratulations your account has been successfully created. Wait for manager approval</h1>");	
			req.getRequestDispatcher("home.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1>Account already existed</h1>");
		}
	}
}
