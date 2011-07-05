package com.indus;

/*
 A class which uses this file to send an email : 
*/
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
* Simple use case for the javax.mail API.
*/
public final class Emailer {
	private static Properties fMailServerConfig = new Properties();
  /**
  * Send a single email.
  */
  public void sendEmail(String aSubject, String aBody, String emailTO) throws MessagingException{
	 // String aToCCEmailAddr;
	  System.out.println("========= Sending Email =========");
    //Authenticators are used to prompt the user for user name and password (required to send mails outside the mailserver domain(yahoo, hotmail etc.)
	  boolean authenticationRequired = true;
	    String aFromEmailAddr = fMailServerConfig.getProperty("mail.from");
	    String aToCCEmailAddr = fMailServerConfig.getProperty("mail.to.cc");	
	    String aToBccEmailAddr = fMailServerConfig.getProperty("mail.to.bcc");	
        Session session = this.getSession(authenticationRequired);
        MimeMessage message = new MimeMessage(getSession(true));
		message.addRecipient(RecipientType.TO, new InternetAddress(emailTO));
		message.addFrom(new InternetAddress[] { new InternetAddress(aFromEmailAddr) });
		message.setSubject(aSubject);
		message.setContent(aBody, "text/plain");
    try {
        if(aToCCEmailAddr!=null && aToCCEmailAddr!="")
            message.addRecipients(Message.RecipientType.CC, aToCCEmailAddr);
        if(aToBccEmailAddr!=null && aToBccEmailAddr!="")
            message.addRecipients(Message.RecipientType.BCC, aToBccEmailAddr);
        message.setSubject( aSubject );
        message.setText( aBody );
        
       Transport.send( message );
      System.out.println("Mail sent successfully!!!");
    }
    catch (MessagingException ex){
      System.err.println("Cannot send email. " + ex);
    }
  }
  //////////////////////////////
  
	private Session getSession(boolean authenticationRequired) {
		MailAuthenticator authenticator = new MailAuthenticator();
		fMailServerConfig.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		if(authenticationRequired)
			return Session.getInstance(fMailServerConfig, authenticator);
		else
			return Session.getInstance(fMailServerConfig, null);
	}
  /**
  * Allows the config to be refreshed at runtime, instead of
  * requiring a restart.
  */
  public static void refreshConfig() {
    fMailServerConfig.clear();
    fetchConfig();
  }

  static {
    fetchConfig();
  }

  public void sendGmail() {
	String host = "smtp.gmail.com";
	int port = 587;
	String username = "vikazsinha@gmail.com";
	String password = "poloexqu";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");

	Session session = Session.getInstance(props);

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from@no-spam.com"));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("vikazsinha@gmail.com"));
		message.setSubject("Testing Subject");
		message.setText("Dear Mail Crawler," +
				"\n\n No spam to my email, please!");

		Transport transport = session.getTransport("smtp");
		transport.connect(host, port, username, password);

		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
}

  private static void fetchConfig() {
      //fMailServerConfig = IndusUtility.loadProperties("MyMailServer.properties");
	  PropertiesReader pr = new PropertiesReader("MyMailServer.properties");
	  fMailServerConfig = pr.loadPropertiesFile();
  }
  
  private InternetAddress[] convertMultipleEmailIDsToInternetAddresses(String emailids){
	  List<String> strings = IndusUtility.convertStringToList(emailids, ",");
	  InternetAddress[] inetAddresses = new InternetAddress[strings.size()];
	  int ii = 0;
	  for (Iterator iterator = strings.iterator(); iterator.hasNext();) {
		String string = (String) iterator.next();
		try {
			inetAddresses[ii++] = new InternetAddress(string);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return inetAddresses;
  }
  

  private class MailAuthenticator extends Authenticator {
	  protected  PasswordAuthentication getPasswordAuthentication() {
		  //return new PasswordAuthentication("admin@indusaura.com", "admin2836");
	      return new PasswordAuthentication(fMailServerConfig.getProperty("mail.smtp.user"), fMailServerConfig.getProperty("mail.smtp.password"));
      }
  }
}





