package com.focuseddeveloper.service;

import java.util.Properties;



import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailService {
		
	public static final String emailHost = "smtp.gmail.com";
	public static final String emailPort = "587";
	private static final String hostUser = Email_UserData.USER;
	private static final String hostUserEmailPassword = Email_UserData.PASS;
	private static final String[] toEmails = { "charles_d_johnson@aol.com", "charles@focuseddeveloper.com" };
	

	public static void sendEmail(String contactName, String contactEmail, String contactSubject, String contactMessage) throws MessagingException{
		
		Properties emailProperties;
		Session mailSession;
		MimeMessage emailMessage;
		
		emailProperties = setMailServerProperties();
		mailSession = Session.getDefaultInstance(emailProperties,  null );
		
		String emailBody = new String("<h1>D.D. Contact Form: "+contactSubject+"</h1> <br/>");
		emailBody = emailBody.concat("<h2>From: "+contactName + ", ");
		emailBody = emailBody.concat("Email: "+contactEmail + "</ h2><br/>");
		emailBody = emailBody.concat(contactMessage);
				
		try {
			emailMessage = createEmailMessage(contactSubject, emailBody, mailSession);
			System.out.println("Email Subject: "+emailMessage.getSubject());
			
			mailSession.setPasswordAuthentication(new URLName("smtp", emailHost, Integer.parseInt(emailPort), null, hostUser, hostUserEmailPassword), new PasswordAuthentication(hostUser, hostUserEmailPassword));
			
			System.out.println("Here");
			
			transportEmail(mailSession, emailMessage);
			

	         System.out.println("End of Try Statement");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Properties setMailServerProperties() {
		Properties emailProperties;
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		return emailProperties;
	}

	private static MimeMessage createEmailMessage(String emailSubject, String emailBody, Session mailSession)
			throws AddressException, MessagingException {
		
		 MimeMessage emailMessage;
		emailMessage = new MimeMessage(mailSession);
		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmails[i]));
		}
		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");// for a html email
		emailMessage.setFrom(new InternetAddress(hostUser));
		
		
		return emailMessage;
	}

	private static void transportEmail(Session mailSession,Message emailMessage) throws AddressException, MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, hostUser, hostUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}


}
