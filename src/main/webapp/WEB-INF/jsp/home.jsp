<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<c:import url="include/head.jsp"/>
<body>
	<div class="fullwidth">
		<c:import url="include/header.jsp"/>
		<nav class="navbar navbar-expand-lg">
    		<ul class="navbar-nav mr-auto">
      			<c:if test = "${isAdmin}">
      			<li class="nav-item active">
        			<a class="nav-link" href="admin">Administration</a>
      			</li>
      			</c:if>
      			<li class="nav-item active">
        			<a class="nav-link" href="logout">Logout</a>
      			</li>
    		</ul>
		</nav>
	</div>
	<h3>Welcome ${userName}!</h3>
	<c:if test = "${errorMsg != null}">
		<div class="error-msg">${errorMsg}</div>
	</c:if>
	<button id="getProjects" >projects</button>
	<br/>
	<p id="projects" class="fullwidth"/>
	<c:import url="include/footer.jsp"/>
</body>
</html>