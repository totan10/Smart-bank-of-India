package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dto.Customer;

@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String cid=req.getParameter("custid");
		int customerid=Integer.parseInt(cid);
		String password=req.getParameter("pwd");
		CustomerDao customerDao=new CustomerDao();
		
		Customer customer=customerDao.login(customerid);
		if(customer==null) {
			res.getWriter().print("<h1>Invalid cust id</h1>");
			req.getRequestDispatcher("home.html").include(req, res);
		}
		else {
			if(customer.getPwd().equals(password)) {
				res.getWriter().print("<h1>Login Success</h1>");
				//session tracking
				req.getSession().setAttribute("customer", customer); //Its is used to store the user information for future purpose as key value pair
				req.getRequestDispatcher("select_acc_type.jsp").include(req, res);
				
			}
			else {
				res.getWriter().print("<h1>Invalid password</h1>");
				req.getRequestDispatcher("home.html").include(req, res);
			}
		}
		
	}

}
