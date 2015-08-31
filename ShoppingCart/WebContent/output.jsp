<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unsuccessful</title>
</head>
<body>
${message}
<div align="center">
<form action="CreateAccount" method="post">
<label >Name: </label>
<input  type="text" name="name" required ><br>
<label >Address: </label>
<input  type="text" name="address" required ><br>
<label >Credit Card Number(10-digit): </label>
<input  type="text" name="number" required ><br>
<input type="submit" value="Create Account" id="submit">
</form>
</div>
</body>
</html>