<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="include/header.jsp"></c:import>


	<!-- banner -->
	<div>
		<section id="banner"></section>
	</div>


<c:import url="include/topbar.jsp"></c:import>

	<c:set var="title" value="${title}" scope="request"/> 
    	  <c:if test="${empty title}">  
    	  	 <c:redirect url="/home?page=home" />
    	  </c:if>

	<!-- main content -->
	<div id="wrapper">
		<section id="primary" class="borderone">
			<h2>Project Spotlight</h2>
			
			
    	  
			
			<h2 id="page-title">${project.title}</h2>
			<h4 id="subtitle">${project.subtitle}</h4>
			<hr id="title-line"> 
			<br>
			<img id="highLight-image" src="${pageContext.request.contextPath}/${project.imagePath}" />
			<!--   img id="title-image" src="https://picsum.photos/600/200"/ -->
			<br>

			<p id=title-summary>${project.summary}</p>
			<br>
			<h2>Features and Highlights</h2>
			<ul>
				<c:forEach items="${project.keyFeatures}" var="feature">
				<li>${feature[0]}: ${feature[1]}
				</c:forEach>
			</ul>
			
			<!-- 
			<p> <img src="https://picsum.photos/800/500"/> Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem.</p>

			<p>Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a,</p>
			 -->
		</section>
		<section id="secondary" class="borderone">
		
			<h2>Recent Updates! 
			<!-- Link to Post New Message -->
				<c:url value="/home" var="myURL">
				<c:param name="page" value="post"/>
				<c:param name="parentID" value="0"/>
				<c:param name="topic" value="Your Topic"/>
				</c:url>
				<c:if test="${empty name}">
					<a href=${pageContext.request.contextPath}/home?page=login> <i class="far fa-comment-alt" id="message-icon" title='Login in to post!'></i></a>
				</c:if>
				<c:if test="${not empty name}">
					<a href=${myURL}> <i class="far fa-comment-alt" id="message-icon" title='Create a new post!'></i></a>
				</c:if>
			</h2> 
				<c:forEach items="${postList}" var="post">
				<div id="post">
				<c:set var="parentID" value="${post.ID}" scope="session"></c:set>
				<h2>${post.topic}</h2>
				Post by: <i>${post.userName}
				<fmt:parseDate value = "${post.date}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
				<fmt:formatDate type = "date" value = "${parsedDate}" dateStyle="medium" />
				</i>
				<p>
				${post.message}
				</p>
				<!-- Link to Reply to Top Level Post -->
				<c:url value="/home" var="replyURL">
				<c:param name="page" value="post"/>
				<c:param name="parentID" value="${post.ID}"/>
				<c:param name="topic" value="${post.topic}"/>
				</c:url>
				<c:if test="${empty name}">
					<a href=${pageContext.request.contextPath}/home?page=login> <i class="far fa-comment-alt" id="message-icon" title='Login in to post!'></i></a>
				</c:if>
				<c:if test="${not empty name}">
					<a href=${replyURL}> <i class="far fa-comment-alt" id="message-icon" title='Click here to reply.'></i></a>
				</c:if>
				
				<br>
					<c:forEach items="${post.replies}" var="reply">
					<div id="postreply">
					<c:set var="parentID" value="${reply.ID}" scope="session"></c:set>
					Comment by: <i>${reply.userName}
					<fmt:parseDate value = "${reply.date}" pattern="yyyy-MM-dd" var="parsedReplyDate" type="date" />
					<fmt:formatDate type = "date" value = "${parsedReplyDate}" dateStyle="medium" />
					</i>
					<p>
					${reply.message}
					</p>
					<!-- Link to Reply to 2nd Level Post, reply to a comment -->
					<c:url value="/home" var="replyURL2">
					<c:param name="page" value="post"/>
					<c:param name="parentID" value="${reply.ID}"/>
					<c:param name="topic" value="${reply.topic}"/>
					</c:url>
					<c:if test="${empty name}">
						<a href=${pageContext.request.contextPath}/home?page=login> <i class="far fa-comment-alt" id="message-icon" title='Login in to post!'></i></a>
					</c:if>
					<c:if test="${not empty name}">
						<a href=${replyURL2}> <i class="far fa-comment-alt" id="message-icon" title='Click here to reply.'></i></a>
					</c:if>
					</br>
						<c:forEach items="${reply.replies}" var="reply2">
						<div id="replytwo">
						<c:set var="parentID" value="${post.ID}" scope="session"></c:set>
						Comment by: <i>${reply2.userName}
						<fmt:parseDate value = "${reply2.date}" pattern="yyyy-MM-dd" var="parsedReplyDate2" type="date" />
						<fmt:formatDate type = "date" value = "${parsedReplyDate2}" dateStyle="medium" />
						</i>
						<p>
						${reply2.message}
						</p>
						</div>
						</c:forEach>
					</div>		
					</c:forEach>
				</div>
				</c:forEach>
				
		</section>
		<c:import url="include/footer.jsp"></c:import>
		