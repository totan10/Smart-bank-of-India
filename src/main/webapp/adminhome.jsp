<%@page import="java.util.List"%>
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
	<h1>Welcome to Admin Home Page</h1>
	<% List<BankAccount> list=(List<BankAccount>)request.getSession().getAttribute("list");%>

	<table border="2" cellspacing="0">
	
		<tr>
		<th>Account_number</th>
		<th>Account_type</th>
		<th>Customer_name</th>
		<th>Customer_id</th>
		<th>Account_status</th>
		<th>Change_status</th>
		</tr>
		
		<%for(BankAccount bankAccount:list){ %>
		
		<tr>
		<th><%=bankAccount.getAcc_no() %></th>
		<th><%=bankAccount.getAccount_type() %></th>
		<th><%=bankAccount.getCustomer().getCname() %></th>
		<th><%=bankAccount.getCustomer().getCid() %></th>
		<th><%=bankAccount.isStatus() %></th>
		<th><a href="Changestatus?accno=<%=bankAccount.getAcc_no() %>"><button>Change Status</button></a></th>
		</tr>
<%} %>		
	</table>
</body>
</html>