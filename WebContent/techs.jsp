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
	<jsp:useBean id="projectTechs" class="com.focuseddeveloper.beans.ProjectTech" scope="session"></jsp:useBean>
	
	<div id="wrapper">
		<section id="content-primary" class="borderone">
			<!-- <h2 id="page-title"><jsp:getProperty property="title" name="projectTechs"/></h2> -->
			<h2 id="page-title">${projectTech.title}</h2>
			<h4 id="subtitle">${projectTech.subtitle}</h4>
			<hr id="title-line"> 
			<br>
			<img id="title-image" src="${pageContext.request.contextPath}/${projectTech.imagePath}" />
			<!-- img id="title-image" src="https://picsum.photos/600/200"/ -->
			<br>

			<p id=title-summary>${projectTech.summary}</p>
			<br>
			

			<c:forEach items="${projectTech.projectsList}" var="project" >
			<h3 id="project-title">${project.title}</h3>
			<p id="project-summary">${project.summary}</p>
				<ul>
					<c:forEach items="${project.keyFeatures}" var="feature">
					<li>${feature[0]}
					</c:forEach>
				</ul>
				<a href=${pageContext.request.contextPath}/project?page=${project.webTitle} >Click here to learn more.</a>
			
			</c:forEach>
			
			
			 
			
		</section>
		
		<c:import url="include/footer.jsp"></c:import>