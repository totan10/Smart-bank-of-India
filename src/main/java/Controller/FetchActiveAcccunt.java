package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.BankAccount;
import Dto.Customer;

@WebServlet("/fetchActiveAccount")
public class FetchActiveAcccunt extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		List<BankAccount> list=customer.getBankAccounts();
		
		List<BankAccount> list2=new ArrayList<BankAccount>();
		for (BankAccount bankAccount : list) {
			if(bankAccount.isStatus()) {
				//System.out.println("Status is true");
				list2.add(bankAccount);
			}
		}
		req.getSession().setAttribute("list", list2);
		req.getRequestDispatcher("accounts.jsp").include(req, resp);
		
	}
}
