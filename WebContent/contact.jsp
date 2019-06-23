<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="include/header.jsp"></c:import>

	<!-- search bar -->
	<div>
		<section id="searchbar">
			<input type="text" placeholder="Search...">
		</section>
	</div>


	<!-- main content -->
	<div id="wrapper">
		<form action="contact_form" method="post" id="contact-form">
			<h2 id="page-title">Contact</h2>
			<hr id="title-line"> 
			<br>
			<input type="text" id="name" name="name" placeholder="Name">
			<input type="text" id="email" name="email" placeholder="Email">
			<br>
			<input type="text" id="subject" name="subject" placeholder="Subject">
			<br>
			<input type="text" id="message" name="message" placeholder="Message">
			<input type="submit" value="Submit">
		</form>

		
		<c:import url="include/footer.jsp"></c:import>