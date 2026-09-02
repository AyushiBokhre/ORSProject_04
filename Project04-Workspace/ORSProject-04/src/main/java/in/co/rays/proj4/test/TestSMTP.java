package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestSMTP {
	public static void main(String[] args) {
//		testUserRegistrationMail();
		testForgetPasswordMail();
	}
	
	public static void testUserRegistrationMail() {
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg=new EmailMessage();
		
		map.put("login", "tanisharambadia@gmail.com");
		map.put("password","tanisha1234");
		map.put("firstName", "Tanisha");
		map.put("lastName", "Rambadia");
		
		msg.setTo(map.get("login"));
		msg.setSubject("User Registration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully.");
	}
	public static void testForgetPasswordMail() {
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg=new EmailMessage();
		
		map.put("login", "tanisharambadia@gmail.com");
		map.put("password","tanisha1234");
		map.put("firstName", "Tanisha");
		map.put("lastName", "Rambadia");
		
		msg.setTo(map.get("login"));
		msg.setSubject("Information related to Forget Password");
		msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully.");
	}

}
