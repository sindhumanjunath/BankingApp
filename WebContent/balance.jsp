<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Balance Statement</title>
</head>
<body>
	<h5>Your Balance is: </h5>
	<%	
		String balance = (String) session.getAttribute("BALANCE");
		out.println(balance);
	
	%>
</body>
</html>