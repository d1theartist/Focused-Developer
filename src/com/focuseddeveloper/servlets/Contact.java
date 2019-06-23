package com.focuseddeveloper.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.focuseddeveloper.service.EmailService;


/**
 * Servlet implementation class Contact
 */
@WebServlet("/contact_form")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: Contact").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		PrintWriter out = response.getWriter();
		
		String from = request.getParameter("name");
		String address = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		try {
			EmailService.sendEmail(from, address, subject, message);
			messageSent(out);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void messageSent(PrintWriter out) {		
		String title = "Send Email";
        String res = "Sent message successfully....";
        String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        out.println(docType +
           "<html>\n" +
              "<head><title>" + title + "</title></head>\n" +
              "<body bgcolor = \"#f0f0f0\">\n" +
                 "<h1 align = \"center\">" + title + "</h1>\n" +
                 "<p align = \"center\">" + res + "</p>\n" +
              "</body> </html>"
        );
	}
	
	public Properties setMailServerProperties(String emailPort) {
		Properties emailProperties;
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		return emailProperties;
	}

	
	public MimeMessage createEmailMessage(String fromUser, String emailSubject, String emailBody, Session mailSession,String[] toEmails)
			throws AddressException, MessagingException {
		
		 MimeMessage emailMessage;
		emailMessage = new MimeMessage(mailSession);
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmails[i]));
		}
		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");// for a html email
		emailMessage.setFrom(new InternetAddress(fromUser));
		
		return emailMessage;
	}

	public void sendEmail(Session mailSession, String emailHost, String fromUser, String fromUserEmailPassword, Message emailMessage) throws AddressException, MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}



}
