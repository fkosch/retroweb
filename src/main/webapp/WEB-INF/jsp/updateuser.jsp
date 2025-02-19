<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<c:import url="include/head.jsp"/>
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
        			<a class="nav-link" href="deleteuser?id=${user.id}">Delete User</a>
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
	<h5 class="text-center">Update User: ${user.name}</h5>
	<form class="form-default" name="formUpdateUser" action="<c:url  value="/updateuser"/>" method="post" onsubmit="return checkUpdateUserInputs()">  
		<table>
			<tr>
				<td class="align-middle">Name:</td><td class="align-middle"><input type="text" name="name" value="${user.name}"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Password:</td><td class="align-middle"><input type="password" name="password"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Email:</td><td class="align-middle"><input type="email" name="email" value="${user.email}"/></td>  
			</tr><tr>
			<tr>
				<td class="align-middle">Administration:</td><td class="align-middle">
				<c:choose>
    				<c:when test="${user.admin}">
        				<input type="checkbox" name="admin" value="true" checked/>
    				</c:when>    
    				<c:otherwise>
    					<input type="checkbox" name="admin" value="true"/>
    				</c:otherwise>
				</c:choose> 
			</tr><tr>
				<td><input type="submit" value="update user"/></td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${user.id}"/>
	</form>
	<c:import url="include/footer.jsp"/>
</body>
</html>