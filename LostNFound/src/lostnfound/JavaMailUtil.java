/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lostnfound;
import java.util.Properties;

import java.util.*;
import java.sql.*;
import javax.mail.Authenticator;
import javax.swing.table.*;
import javax.swing.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author sande
 */
public class JavaMailUtil {
    public static void sendMail(String recepient,String title,String currentUser,int lf) throws Exception {
        System.out.println("Preparing to send email");
        Properties props = new Properties();
        
        
        
        props.put("mail.smtp.user","lostfound.bphc@gmail.com"); 
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.port", "465");   
        props.setProperty("mail.smtp.socketFactory.port", "465"); 
        
        String myAccountEmail = "lostfound.bphc@gmail.com";
        String password = "mynewpass@123";
        
        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        
        Message message = prepareMessage(session,myAccountEmail,recepient,title,currentUser,lf);
        
        Transport.send(message);
        System.out.println("Message sent successfully");
        
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String title,String currentUser,int lf) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Item Found Notification");
            if(lf==0)
                message.setText("Your lost " + title + " has been found by "+currentUser );
            else if(lf==1)
                message.setText(currentUser + " wants to claim the " + title +" you have found" );
            return message;
        }catch(Exception e){
            System.out.println(""+e);
        }
        return null;
    }
}
