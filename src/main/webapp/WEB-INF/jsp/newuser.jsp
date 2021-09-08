<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="include/head.jsp"/>
<body>
	<form name="formNewUser" action="<c:url  value="/newuser"/>" method="post">  
		<label>Name</label>
		<input type="text" name="name"/><br/>  
		<label>Password</label>
		<input type="password" name="password"/><br/>  
		<label>Email</label>
		<input type="email" name="email"/><br/> 
		<label>Administrator</label>
		<input type="checkbox" name="admin"/><br/><br/>
		<input type="submit" value="add user"/>
	</form>
	<br/>
	<a href="admin">Cancel</a>
	<script type="text/javascript" src="<c:url value = "resources/js/scriptUser.js"/>"></script>
</body>
</html>