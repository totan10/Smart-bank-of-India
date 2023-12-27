<%@page import="Dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to Active account page</h1>
	<% List<BankAccount> list=(List<BankAccount>)request.getSession().getAttribute("list");
	if(list.isEmpty())
		{%>
		<h1>No active accounts found</h1>
	<%}else{ %>
		<h1>Select bank account</h1>
		<%for(BankAccount bankAccount:list){ %>
		
		<a href="setActiveAccount?accno=<%=bankAccount.getAcc_no() %>"><button><%=bankAccount.getAcc_no() %></button></a>
		
		<%} %>
	
	<%} %>
</body>
</html>