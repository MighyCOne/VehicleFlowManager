package com.eintern.business;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//JavaMail – GMail via TLS
//Added javax.mail.jar to make this work
public class Emailer {

	public void sendHTMLMail(String status, String recipientEmail, String senderEmail, int reqId, int vId, String vName,
			int locId, String shippingLocName, int shippingLocId, String locName, String lastUpdateDate) {

		final String username = senderEmail;
		final String password = "Winggundam0!!!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recipientEmail));
			message.setSubject("Vehicle Flow Manager Request Update: A Vehicle's Status Has Changed");
			message.setText("Request " + reqId + ": On" + lastUpdateDate + ", vehicle with id " + vId
					+ " shipping from location " + shippingLocId + ": " + shippingLocName + "to " + locId
					+ ": " + locName + " was changed to <b>'" + status + "'</b>.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
	

/*//Set Subject: header field
			message.setSubject("Vehicle Flow Manager Request Update: A Vehicle's Status Has Changed");

			// Send the actual HTML message, as big as you like
			message.setText("Request " + reqId + ": On" + lastUpdateDate + ", <h1>vhehicle with id</h1> " + vId
					+ " <h1>shipping from location </h1>" + shippingLocId + ": " + shippingLocName + "to " + locId
					+ ": " + locName + " was changed to <b>'" + status + "'</b>.");

*/