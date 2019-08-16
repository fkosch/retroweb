<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>   
	<c:import url="include/head.jsp"/>
	<body>
		<form class="form-login" action="<c:url  value="/login"/>" method="post">
			<h1>Please Sign In</h1>
			<p>
				Login name:
				<input class="login-input" name="username" type="text" placeholder="name" required />       
			</p>
			<p>
				Password:
				<input class="login-input" name="password" type="password" placeholder="password" required />
			</p> 
			<p class="error-msg">${msg}</p>
			<button class="login-input" type="submit" id="loginButton">login</button>
		</form>
    </body>
</html>
