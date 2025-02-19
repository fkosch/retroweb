<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        			<a class="nav-link" href="user">User Administration</a>
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
	<h3>Welcome Admin: ${userName}!</h3>
	</div>
	<c:import url="include/footer.jsp"/>
</body>
</html>