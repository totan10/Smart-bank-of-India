package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dto.Customer;

@WebServlet("/customersignup")
public class CustomerSignup  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String mobile=req.getParameter("mob");
		long mob=Long.parseLong(mobile);
		String pwd=req.getParameter("pwd");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String dob=req.getParameter("dob");
		
		Date date=Date.valueOf(dob);
		Period period=Period.between(date.toLocalDate(), LocalDate.now());
		int age =period.getYears();
		Customer customer=new Customer();
		CustomerDao customerDao=new CustomerDao();
		
		if(age>18) {
			if(customerDao.check1(email).isEmpty() && customerDao.check2(mob).isEmpty())
			{
				customer.setCname(name);
				customer.setEmail(email);
				customer.setMob(mob);
				customer.setPwd(pwd);
				customer.setGender(gender);
				customer.setDate(date);
				customerDao.save(customer);
				//resp.getWriter().print("<h1>Account has been created successfully</h1>");
				Customer customer2=customerDao.check1(email).get(0);
				if(customer2.getGender().equals("male")) {
					resp.getWriter().println("<h1>Hello Sir ji</h1>");
				}
				else {
					resp.getWriter().println("<h1>Hello Ma'am</h1>");
				}
				resp.getWriter().print("<h1>Account has been created successfully and your Customer Id:"+customer2.getCid()+"</h1>");
				req.getRequestDispatcher("customer_login.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1>The email id: "+email+" and mobile no: "+mob+" are already existed</h1>");
			}
			
			
		}
		else {
			resp.getWriter().print("<h1>You are not eligible</h1>");
		}
		
	}

}
