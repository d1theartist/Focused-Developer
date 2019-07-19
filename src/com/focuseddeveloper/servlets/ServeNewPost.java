package com.focuseddeveloper.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.focuseddeveloper.beans.Post;
import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.servedata.FetchPost;

/**
 * Servlet implementation class ServeNewPost
 */
@WebServlet("/ServeNewPost")
public class ServeNewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeNewPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Users currentUser;		
		
		System.out.println("Serve New Post");
		
		currentUser = getUser(request);
		Post newPost;
		int parentID;
		
		String parentIDString = request.getParameter("parentID");
		
		
		String message = request.getParameter("message");
		String topic = request.getParameter("topic");
		
		if(parentIDString!= null) {
			System.out.println("ParentID is NOT null: "+parentIDString);
			parentID = Integer.parseInt(parentIDString);
			System.out.println("ParentID: " + parentID);
			System.out.println("Topic: " + topic);
		}else {
			System.out.println("ParentID is null");
			System.out.println("Topic: " + topic);
			parentID = 0;
		}
		
		if(currentUser != null) {
			newPost = new Post(0, currentUser.getID(), parentID, currentUser.getName(), topic, message ,LocalDate.now() );
			if(addPost(newPost)) {
				// post added
				response.sendRedirect("index.jsp");
			}else {
				// post not added to database
				response.sendRedirect("login.jsp");
			}
			
		}else {
			// not logged in
			response.sendRedirect("login.jsp");
		}
	}

	private Users getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users currentUser;
		currentUser = (Users) session.getAttribute("currentUser");
		
		return currentUser;
	}
	
	private boolean addPost(Post newPost) {
		FetchPost fp = new FetchPost();
		if(fp.addPost(newPost))
		{
			return true;
		}
		return false;
	}

}
