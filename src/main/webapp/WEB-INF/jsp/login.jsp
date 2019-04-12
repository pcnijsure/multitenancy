<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<form action="/mysql/login" method="post">
Name:<input type="text" name="name"/><br/>
Details:<input type="text" name="details"/><br/>
<input type="submit" value="login"/> 
</form>
</body>
</html>