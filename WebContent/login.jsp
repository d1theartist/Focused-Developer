<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="include/header.jsp"></c:import>

<c:import url="include/topbar.jsp"></c:import>


	<!-- main content -->
	<div id="wrapper">
		<form method="post" action="LoginServlet" id="login-form">
			<h2 id="page-title">Login</h2>
			<hr id="title-line"> 
			<br>
					<input type="email" name="email" id="login_email" value="" placeholder="Email"/>
					<br>
					<input type="password" name="password" id="login_password" value="" placeholder="Password"/>

					<br>
						<input type="submit" value="Sign in" />
		</form>

		
		<c:import url="include/footer.jsp"></c:import>