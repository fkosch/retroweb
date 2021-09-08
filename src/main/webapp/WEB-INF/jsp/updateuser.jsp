<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<c:import url="include/head.jsp"/>
<body>
	<form name="formUpdateUser" action="<c:url  value="/updateuser"/>" method="post">  
		<label>Name</label>
		<input type="text" name="name" value="${user.name}"/><br/>  
		<label>Password</label>
		<input type="password" name="password"/><br/>  
		<label>Email</label>
		<input type="email" name="email" value="${user.email}"/><br/> 
		<label>Administrator</label>
		<c:choose>
    		<c:when test="${user.admin}">
        		<input type="checkbox" name="admin" value="true" checked/>
    		</c:when>    
    		<c:otherwise>
    			<input type="checkbox" name="admin" value="true"/>
    		</c:otherwise>
		</c:choose>
		<br/><br/>
		<input type="hidden" name="id" value="${user.id}"/>
		<input type="submit" value="update user"/>
	</form>
	<br/>
	<a href="admin">Cancel</a>
	<script type="text/javascript" src="<c:url value = "resources/js/scriptUser.js"/>"></script>
</body>
</html>