<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="include/head.jsp"/>
<body>
	<p>Welcome Admin: ${userName}!</p>
	<a href="home">Home</a><br/>
	<a href="user">User Administration</a><br/>
	<a href="logout">Logout</a>
</body>
</html>