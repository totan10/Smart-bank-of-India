<%@page import="Dto.Customer"%>
<%@page import="Dao.BankDao"%>
<%@page import="Dto.BankAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Balance page</h1>
	<% long acnumber=(long)request.getSession().getAttribute("acno");
	BankDao bankDao=new BankDao();
	BankAccount bankAccount=bankDao.fetch_account_details(acnumber);
	Customer customer=bankAccount.getCustomer();
	%>
	<h1>Hello<%if(customer.getGender().equals("male")){ %>Mr.<%}else{ %>Ms<%} %><%=customer.getCname() %></h1>
	<h1>Hello!! Your account balance is:<%=bankAccount.getAmount() %></h1>
	
</body>
</html>