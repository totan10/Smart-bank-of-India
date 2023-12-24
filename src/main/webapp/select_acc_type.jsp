<%@page import="Dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Customer customer=(Customer) request.getSession().getAttribute("customer");%>
<h1>Hello Dear <%= customer.getCname() %></h1>
<h1>Welcome to Select Account Type Page</h1>
<form action="createbankaccount">
<input type="radio" name="accounttype" value="saving">Saving Account<br><br>
<input type="radio" name="accounttype" value="current">Current Account<br><br>
<button>Submit</button>
<button type="reset">Cancel</button></form>
</body>
</html>