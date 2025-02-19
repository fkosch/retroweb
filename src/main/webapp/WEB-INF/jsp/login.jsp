<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>   
	<c:import url="include/head.jsp"/>
	<body>
		<c:import url="include/header.jsp"/>
        <form action="<c:url  value="/login"/>" method="post">
        	<h3>Please Sign In</h3>
        	<table>
				<tr>
					<td>Login:</td><td><input type="text" name="username" placeholder="name" required /></td>  
				</tr><tr>
					<td>Password:</td><td><input type="password" name="password" placeholder="password" required /></td> 
				</tr><tr>
					<td><button type="submit" id="loginButton">login</button></td>
				</tr>
				<c:if test="${msg!=null}">
				<tr>
					<td/><td class="error-msg">${msg}</td>
				</tr>
				</c:if>
			</table>
        </form>
        <c:import url="include/footer.jsp"/>
    </body>
</html>