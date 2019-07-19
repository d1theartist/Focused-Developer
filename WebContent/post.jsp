<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="include/header.jsp"></c:import>

<c:import url="include/topbar.jsp"></c:import>

	<!--  
	<c:out value="${param.parentID}"/>
	<br>
	<c:out value="${param.topic}"/>
	
	<c:out value="${sessionScope.currentUser.name}"/>
	<br>
	
	<c:out value="${parentPost.ID}"/>
	<c:out value="${sessionScope.currentUser.access}"/>
	-->
	<!-- main content -->
	
	<div id="wrapper">
		<form method="post" action="ServeNewPost" id="login-form" name="signup_form" >
			<h2 id="page-title">Post</h2>
			<hr id="title-line"> 
			<c:if test="${empty parentPost}">
				<c:if test="${sessionScope.currentUser.access != 'ADMIN'}">
					<c:redirect url="/home?page=home" />
				</c:if>
				<input type="topic" name="topic" id="topic" placeholder="Topic" required/>
				<input type="hidden" value="0" name="parentID" id="parentID">
				<br>
			</c:if>
			<c:if test="${not empty parentPost}">
				<input type="hidden" value="${parentPost.topic}" name="topic" id="topic">
				<input type="hidden" value="${parentPost.ID}" name="parentID" id="parentID">
				Reply to: <c:out value="${parentPost.userName}"/>
				<br>
				<c:out value="${parentPost.topic}"/>
				<br>
				<c:out value="${parentPost.message}"/>
				<br>
				<br>
			</c:if>
			<br>
			
					
					<br>
					<input type="text" id="message" name="message" placeholder="Your post..." required>
		
					<br>
					<br>
						<input type="submit" value="Submit Post"/>
						<br> <br>

		</form>
		
		 
		
		<script>

	
		
	
		</script>
		
		<c:import url="include/footer.jsp"></c:import>