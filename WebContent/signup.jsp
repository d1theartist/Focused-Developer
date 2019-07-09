<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="include/header.jsp"></c:import>

<c:import url="include/topbar.jsp"></c:import>


	<!-- main content -->
	<div id="wrapper">
		<form method="post" action="ServeNewUser" id="login-form" name="signup_form" onSubmit="return passwordMatch()">
			<h2 id="page-title">Sign Up</h2>
			<hr id="title-line"> 
			<br>
			
					<input type="name" name="name" id="signup_name" value="" placeholder="Name" required/>
					<br>
					<input type="email" name="email" id="signup_email" value="" placeholder="Email" required/>
					<br>
					<br>
					<input type="password" name="password" id="password1" value="" placeholder="Password" required/>
					<br>
					<input type="password" name="password2" id="password2" value="" placeholder="Confirm Password" required/>
					<br>
					<br>
						<input type="submit" value="Sign up"/>
						<br> <br>

		</form>
		
		 
		<!--  attempted is set inside the servlet when a log in attempt fails -->
		<c:remove var="attempted"/>
		
		<script>

	
		
	
		</script>
		
		<c:import url="include/footer.jsp"></c:import>