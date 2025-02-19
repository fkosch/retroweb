<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<c:import url="include/head.jsp"/>
<body>
	<div class="fullwidth">
		<c:import url="include/header.jsp"/>
		<nav class="navbar navbar-expand-lg">
    		<ul class="navbar-nav mr-auto">
    			<li class="nav-item active">
        			<a class="nav-link" href="newuser">New User</a>
      			</li>
				<li class="nav-item active">
        			<a class="nav-link" href="admin">Administration</a>
      			</li>
      			<li class="nav-item active">
        			<a class="nav-link" href="home">Home</a>
      			</li>
      			<li class="nav-item active">
        			<a class="nav-link" href="logout">Logout</a>
      			</li>
    		</ul>
		</nav>
	</div>
	<h2>User Administration</h2>
	<c:if test = "${errorMsg != null}">
		<div class="error-msg">${errorMsg}</div>
	</c:if>
	<table class="table table-striped">
		<tr><th>Name</th><th>Email</th><th>Admin</th><th>Created</th><th>Updated</th></tr>
		<c:forEach items="${users}" var = "user">
 			<tr><td><a href="updateuser?id=<c:out value = "${user.id}"/>"><c:out value = "${user.name}"/></a></td>
 			<td><c:out value = "${user.email}"/></td>
 			<td><c:out value = "${user.admin}"/></td>
 			<td><c:out value = "${user.created}"/></td>
 			<td><c:out value = "${user.updated}"/></td></tr>
		</c:forEach>
	</table>
	<c:import url="include/footer.jsp"/>
</body>
</html>