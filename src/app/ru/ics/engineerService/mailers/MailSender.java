package ru.ics.engineerService.mailers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import ru.ics.engineerService.util.PropertiesUtil;



public class MailSender {

	private static final String MAIL_PROTOCOL = "smtp";
	private static Properties props;
	private final static Logger logger = Logger.getLogger(MailSender.class);
	Session session=null;
	Transport bus = null;
	
	static{
		props= PropertiesUtil.getInstance().getProp("mail.properties");
	}
	
	protected volatile static MailSender instance;
	
	private MailSender() {
		logger.info("Initialize MailSender");
		String host = props.getProperty("Server.SMTP");
		
		logger.info(host);
		Properties session_properties = new Properties();
		
		//Host specification
		session_properties.put("mail.smtp.host", host);
		// debug mode
		session_properties.put("mail.debug", "false");
		session = Session.getInstance(session_properties);
		try {
			bus = session.getTransport(MAIL_PROTOCOL);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		logger.info("Success");
	}
	
	public String sendMailHTMLContent(List<String> mailTo, String htmlContent, String subject ){
		
		logger.info("Sending HTML content");
		Message msg = new MimeMessage(session);
		String serverAddress=props.getProperty("Server.address");
		try {
			InternetAddress[] addressTo= new InternetAddress[mailTo.size()];
			for (int i = 0; i < addressTo.length; i++) {
				addressTo[i]=new InternetAddress(mailTo.get(i));
			}
			msg.setFrom(new InternetAddress(serverAddress));
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			bus.connect();
			setHTMLContent(msg, htmlContent);
			msg.saveChanges();
			bus.sendMessage(msg, addressTo);

			bus.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return "Error. Address exception.";	
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			
		}
		return "Success";
	}
	
	public String sendMailWithAttachment(List<String> mailTo, String content, String subject, File attachment){
		
		Message msg = new MimeMessage(session);
		String serverAddress=props.getProperty("Server.address");
		try {
			InternetAddress[] addressTo= new InternetAddress[mailTo.size()];
			for (int i = 0; i < addressTo.length; i++) {
				addressTo[i]=new InternetAddress(mailTo.get(i));
			}
			msg.setFrom(new InternetAddress(serverAddress));
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			bus.connect();
			msg.setContent(content, "text/plain");
			//test attachment
			setFileAsAttachment(msg, attachment);
			msg.saveChanges();
			bus.sendMessage(msg, addressTo);

			bus.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			return "Error. Address exception.";	
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			
		}
		
		return "Success";
	}
	


	// Сообщение с типом контента multipart/mixed. Обе части имеют тип контента
	// text/plain.
//	public static void setMultipartContent(Message msg)
//			throws MessagingException {
//		// Создание и заполнение первой части
//		MimeBodyPart p1 = new MimeBodyPart();
//		p1.setText("This is part one of a test multipart e-mail.");
//
//		// Создание и заполнение второй части
//		MimeBodyPart p2 = new MimeBodyPart();
//		// Here is how to set a charset on textual content
//		p2.setText("This is the second part", "us-ascii");
//
//		// Создание экземпляра класса Multipart. Добавление частей сообщения в
//		// него.
//		Multipart mp = new MimeMultipart();
//		mp.addBodyPart(p1);
//		mp.addBodyPart(p2);
//
//		// Установка объекта класса Multipart в качестве контента сообщения
//		msg.setContent(mp);
//	}s

	// File attachment. Using JAF FileDataSource.
	public static void setFileAsAttachment(Message msg, File file)
			throws MessagingException {

		// Create and fill first message part
		MimeBodyPart p1 = new MimeBodyPart();
		// Create second part
		MimeBodyPart p2 = new MimeBodyPart();

		// Adding file to the second part
		FileDataSource fds = new FileDataSource(file);
		p2.setDataHandler(new DataHandler(fds));
		p2.setFileName(fds.getName());

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(p1);
		mp.addBodyPart(p2);

		// Setting content
		msg.setContent(mp);
	}

	//Adding HTML content
	public static void setHTMLContent(Message msg,String content) throws MessagingException {

		// HTMLDataSource является внутренним классом
		msg.setDataHandler(new DataHandler(new HTMLDataSource(content)));
	}

	/*
	 * Inner class look like  JAF datasource. Adding HTML content
	 */
	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("Null HTML");
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}
	
	public static MailSender getInstance(){
		if (instance==null) {
			synchronized (MailSender.class) {
				if (instance==null) {
					instance=new MailSender();
				}
			}
		}
		return instance;
	}
	
}
