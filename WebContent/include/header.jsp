<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><% 
    if( request.getAttribute("title") == null){
        out.print("Homepage");
    }else{
    	out.print(request.getAttribute("title"));
    }
	
	//alert(page);
    %></title>
	<link rel="stylesheet" href="assets/css/style.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="assets/js/script.js"></script>

	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


</head>

<body>

	<!-- header -->
	<header id="header">
		<div class="borderone">
			<h1>The Focused Developer</h1>
			<small>by Charles Johnson - Software Developer</small></br>
		</div>
		<nav class="nav-main">
			<ul>

				<li><a href=${pageContext.request.contextPath}/home?page=home class="nav-item">Home</a></li>
				<li><a href=${pageContext.request.contextPath}/home?page=about class="nav-item">About</a></li>
				<li><a href="JavaScript:void(0)" onclick=changeVisible("drop-down1")
				 class="nav-item">Projects</a>
					<div id="drop-down1" class="nav-content">
						<div class="nav-sub">
							<ul>
								<li><a href=${pageContext.request.contextPath}/projects?page=c>C++</a></li>
								<li><a href=${pageContext.request.contextPath}/projects?page=java>Java</a></li>
								<li><a href=${pageContext.request.contextPath}/projects?page=android>Android</a></li>
							</ul>
						</div>
					</div>
				
				</li>
				<li><a href=${pageContext.request.contextPath}/home?page=contact class="nav-item">Contact</a></li>
			</ul>
		</nav>
	</header>