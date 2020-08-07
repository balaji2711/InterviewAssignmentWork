package com.report;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment
{
	public static void mailTrigger()
	{	
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");		
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");		
		//This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication("rfpio@gmail.com", "assignment");
			}
		});

		try
		{
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress("balaji.november@gmail.com"));
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("balaji.g@harman.com"));           
            // Add the subject link
			message.setSubject("Honeywell Total Connect - Automation Test ExecutionReport");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("Dear Team,"+'\n'+""+'\n'+"Your test suite has just finished its execution. "+'\n'+"Failure case's will be analyzed by QA team and will post out the observation. Please find the attachment for the test run report."+'\n'+""+'\n'+"This email was sent automatically by Harman. Please do not reply."+'\n'+""+'\n'+"Thanks,"+'\n'+"Balaji.");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String workingDir=System.getProperty("user.dir");
			String filename = workingDir+"\\ExtentReports\\TestReport.html";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName("Assignment"+""+" Test"+""+" Report.html");

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
