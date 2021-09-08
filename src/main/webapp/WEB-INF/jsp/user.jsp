<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="include/head.jsp"/>
<body>
	<h2>User Administration</h2>
	<p>Your name: ${userName}</p>
	<br/>
	<table>
		<tr><th>Name</th><th>Email</th><th>Admin</th><th>Created</th><th>Updated</th></tr>
		<c:forEach items="${users}" var = "user">
 			<tr><td><a href="updateuser?id=<c:out value = "${user.id}"/>"><c:out value = "${user.name}"/></a></td>
 			<td><c:out value = "${user.email}"/></td>
 			<td><c:out value = "${user.admin}"/></td>
 			<td><c:out value = "${user.created}"/></td>
 			<td><c:out value = "${user.updated}"/></td></tr>
		</c:forEach>
	</table>

	<p>${errorMsg}</p>
	<a href="newuser">Create a new user</a>
	<br/>
	<a href="home">Home</a> 
</body>
</html>