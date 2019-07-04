<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- search bar -->
	<div>
		<section id="searchbar">
			
			<div class="login">
				<c:set var="name" value="${sessionScope.userName}" scope="session"></c:set>
				<c:if test="${empty name}">
					Welcome Guest.
					<a href=${pageContext.request.contextPath}/home?page=login> Login</a>
				</c:if>
				<c:if test="${not empty name}">
				<form method="get" action="Logout" id="logout_btn"><input type="submit" value="logout"/></form>
					Welcome back ${name}.  
					
				</c:if>
				
			</div>
			<input type="text" placeholder="Search...">
		</section>
	</div>