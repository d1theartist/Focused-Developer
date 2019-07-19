package com.focuseddeveloper.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.focuseddeveloper.beans.Post;
import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.servedata.CreateProjectTechs;
import com.focuseddeveloper.servedata.FetchPost;
import com.mysql.cj.util.StringUtils;

/**
 * Servlet implementation class ServeHome
 */
@WebServlet("/home")
public class ServeHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Post> posts;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeHome() {
        super();
        // TODO Auto-generated constructor stub
        
        posts = new ArrayList<Post>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		System.out.println("Testing");
		
		
		
	
		
		HttpSession session = request.getSession(); 
	

		switch (page) {
		
		case "home":
			//FetchPost myPost = new FetchPost();
			selectProjectHighlight( request);
			getAllPost(request);
			request.setAttribute("title", "Homepage");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "about":
			request.setAttribute("title", "About");
			request.getRequestDispatcher("about.jsp").forward(request, response);
			break;
		case "contact":
			request.setAttribute("title", "Contact");
			request.getRequestDispatcher("contact.jsp").forward(request, response);
			break;
		case "login":
			request.setAttribute("title", "Login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			break;
		case "logout":
			request.setAttribute("title", "Logout");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "signup":
			request.setAttribute("title", "Signup");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			break;
		case "post":
			int parentID = Integer.parseInt(request.getParameter("parentID"));
			Users currentUser = (Users) session.getAttribute("currentUser");
			
			// if a user is logged in
			if(currentUser != null) {
				//if this post will be a reply
				if(parentID != 0) {
					Post parentPost = getPost(parentID);
					if(parentPost != null) {
						if(parentPost.isReplyEligible()) {
							request.setAttribute("parentPost", parentPost);
							request.setAttribute("title", "Post");
							request.getRequestDispatcher("post.jsp").forward(request, response);
						}else {
							System.out.println("Reply is Ineligible for this post.");
						}
					}else {
						System.out.println("Parent Post could not be found. ID: "+parentID);
					}
				// if this post is not a reply, is the user an admin?
				}else if(currentUser.getAccess().equals(Users.ACCESS_ADMIN)){
					request.setAttribute("title", "Post");
					request.getRequestDispatcher("post.jsp").forward(request, response);
				}else {
					// no access
					System.out.println("Access Denied.  User: "+currentUser.getName() + " is: "+ currentUser.getAccess() + " cannot create topics.");
				}
				
			}else {
				//Not Logged In
			}
			break;
		default: 
			request.setAttribute("title", "Not Found");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void selectProjectHighlight(HttpServletRequest request) {
		ArrayList<Project> projects = new ArrayList<Project>();
		Project pjHighlight;
		try {
			CreateProjectTechs pjs = new CreateProjectTechs();
			projects = pjs.getProjList();
			
			Random rand = new Random();
			int pjID = rand.nextInt(projects.size() );
			
			pjHighlight = projects.get(pjID);
			
			request.setAttribute("project", pjHighlight);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void getAllPost(HttpServletRequest request) {
		
		FetchPost fp = new FetchPost();
		posts = (ArrayList<Post>) fp.getPosts();
		
		request.setAttribute("postList", posts);
		
		for( Post post: posts) {
			System.out.println("ID: " +post.getID());
			System.out.println("User ID: " +post.getUserID());
			System.out.println("Topic: " +post.getTopic());
			System.out.println("Message: " +post.getMessage());
		}
		
	}
	
private Post getPost(int postID) {
	
		if(posts.isEmpty()) {
		FetchPost fp = new FetchPost();
		posts = (ArrayList<Post>) fp.getPosts();
		}
		
		
		for( Post post: posts) {
			if(post.getID() == postID)
				return post;
		}
		return null;
	}

}
