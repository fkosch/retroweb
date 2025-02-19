<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<c:import url="include/head.jsp"/>
</head>
<body>
	<div class="fullwidth">
		<c:import url="include/header.jsp"/>
		<nav class="navbar navbar-expand-lg">
    		<ul class="navbar-nav mr-auto">
    			<li class="nav-item active">
        			<a class="nav-link" href="user">User Administration</a>
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
	<h5 class="text-center">New User</h5>
	<form class="form-default" name="formNewUser" action="<c:url  value="/newuser"/>" method="post" onsubmit="return checkNewUserInputs()">  
		<table>
			<tr>
				<td class="align-middle">Name:</td><td class="align-middle"><input type="text" name="name"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Password:</td><td class="align-middle"><input type="password" name="password"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Email:</td><td class="align-middle"><input type="email" name="email"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Administration:</td><td class="align-middle"><input type="checkbox" name="admin"/>
			</tr><tr>
				<td><input type="submit" value="add user"/></td>
			</tr>
		</table>			
	</form>
	<c:import url="include/footer.jsp"/>
</body>
</html>