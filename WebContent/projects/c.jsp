<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>C++ Projects</title>
	<link rel="stylesheet" href="../assets/css/style.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="../assets/js/script.js"></script>
</head>

<body>

	<!-- header -->
	<header id="header">
		<div class="borderone">
			<h1>The Focused Developer</h1>
			<small>by Charles Johnson - Software Developer</small>
		</div>
		<nav class="nav-main">
			<ul>

				<li><a href="../index.html" class="nav-item">Home</a></li>
				<li><a href="../about.html" class="nav-item">About</a></li>
				<li><a href="JavaScript:void(0)" onclick=changeVisible("drop-down1")
				 class="nav-item">Projects</a>
					<div id="drop-down1" class="nav-content">
						<div class="nav-sub">
							<ul>
								<li><a href="c.html">C++</a></li>
								<li><a href="#">Java</a></li>
								<li><a href="#">Android</a></li>
							</ul>
						</div>
					</div>
				
				</li>
				<li><a href="../contact.html" class="nav-item">Contact</a></li>
			</ul>
		</nav>
	</header>


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
			<img id="title-image" src="https://picsum.photos/600/200"/>
			<br>

			<p id=title-summary>${projectTech.summary}</p>
			<br>
			

			<c:forEach items="${projectTech.projectsList}" var="project" >
			<h3 id="project-title">${project.title}</h3>
			<p id="project-summary">${project.summary}</p>
				<ul>
					<c:forEach items="${project.keyFeatures}" var="feature">
					<li>${feature[0]}, ${feature[1]}
					</c:forEach>
				</ul>
			
			</c:forEach>
			
			
			 
			
		</section>
		
		<footer class="borderone"> &copy; The Focused Developer</footer>
	</div>
	<script>
		changeVisible("drop-down1");
	</script>
</body>

</html>