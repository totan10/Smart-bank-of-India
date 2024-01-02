<%@page import="Dto.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="Dto.BankAccount"%>
<%@page import="Dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Transaction History</h1>
	<%long accno=(Long)request.getSession().getAttribute("acno") ;
	BankDao bankDao=new BankDao();
	BankAccount bankAccount=bankDao.fetch_account_details(accno);
	List<BankTransaction> list=bankAccount.getList();
	%>
	<h1>Account no:<%=accno %></h1>
	<h1>Account type:<%=bankAccount.getAccount_type() %></h1>
	
	<table cellspacing="0" border="2">
		<tr>
			<th>tid</th>
			<th>Deposit</th>
			<th>Withdraw</th>
			<th>Balance</th>
			<th>Transaction Date & Time</th>
		</tr>
		<%for(BankTransaction bankTransaction:list){ %>
		<tr>
			<th><%=bankTransaction.getTid() %></th>
			<th><%=bankTransaction.getDeposit()%></th>
			<th><%=bankTransaction.getWithdraw() %></th>
			<th><%=bankTransaction.getBalance() %></th>
			<th><%=bankTransaction.getDateTime() %></th>
		</tr>
		<%} %>	
	</table>
</body>
</html>