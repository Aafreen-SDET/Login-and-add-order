package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
//import org.bouncycastle.util.Store;
//import org.eclipse.jetty.server.session.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.FlagTerm;

import java.io.IOException;


import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import javax.mail.*;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static WebDriver driver;

    public static void main(String[] args) throws Exception {
        setup();
        login();
       // String otp = fetchOTPFromEmail(); // Fetch OTP and store in a variable
        //enterOTP(otp); // Enter OTP into the field and verify
        click();
        //driver.findElement(By.partialLinkText("Commander")).click();
    }

    // Setup WebDriver
    public static WebDriver setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://stage-commander.swageazy.com/");
        }
        return driver;
    }

    // Perform login action
    public static void login() throws InterruptedException {
        WebElement username = driver.findElement(By.id("txt-admin-userid"));
        username.sendKeys("afreen.khan@swageazy.com"); // Use your username
        WebElement password = driver.findElement(By.id("txt-admin-pwd"));
        password.sendKeys("Dania@123"); // Use your password
        WebElement next = driver.findElement(By.id("submit-btn-login"));
        next.click();
    }

    // Fetch OTP from email
//    public static String fetchOTPFromEmail() throws Exception {
//        // Email credentials and properties
//        String host = "imap.gmail.com";
//        String username = "afreen.khan@swageazy.com";  // Email
//        String password = "DaniaUsaid@123";  // Email password
//
//        // Set mail properties
//        Properties properties = new Properties();
//        properties.put("mail.store.protocol", "imaps");
//        properties.put("mail.imaps.host", host);
//        properties.put("mail.imaps.port", "993");
//        properties.put("mail.imaps.ssl.enable", "true");
//
//        // Initialize session and store
//        Session session = Session.getInstance(properties);
//        Store store = session.getStore("imaps");
//        store. connect(host, username, password);
//
//        // Open the inbox folder
//        Folder inbox = store.getFolder("INBOX");
//        inbox.open(Folder.READ_ONLY); // You can change to READ_WRITE if you want to mark the message as read
//
//        // Fetch unread messages
//        Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
//
//        // Loop through messages to find OTP
//        for (Message message : messages) {
//            if (message.getSubject().contains("Your OTP")) { // Customize based on the subject of the OTP email
//                String content = getTextFromMessage(message);
//                String otp = extractOTP(content);
//                if (otp != null) {
//                    System.out.println(otp);
//                    return otp;
//
//                }
//            }
//        }
//
//        // Close connections
//        inbox.close(false);
//        store.close();
//
//        throw new Exception("OTP not found in unread emails.");
//    }

    // Extract OTP using a regex pattern
    private static String extractOTP(String content) {
        String otpRegex = "\\b\\d{6}\\b"; // Regex for a 6-digit OTP
        Pattern pattern = Pattern.compile(otpRegex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            content  =  matcher.group(0);  // Return the first matched 6-digit OTP
        }
        return content ;
    }

    // Extract email content
//    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
//        String result = "";
//        if (message.isMimeType("text/plain")) {
//            result = message.getContent().toString();
//        } else if (message.isMimeType("multipart/*")) {
//            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//            result = getTextFromMimeMultipart(mimeMultipart);
//        }
//        return result;
//    }
//
//    // Get text from a multipart email
//    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
//        StringBuilder result = new StringBuilder();
//        int count = mimeMultipart.getCount();
//        for (int i = 0; i < count; i++) {
//            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//            if (bodyPart.isMimeType("text/plain")) {
//                result.append(bodyPart.getContent());
//            }
//        }
//        return result.toString();
//    }

    // Enter OTP and click verify
    public static void enterOTP(String otp) throws InterruptedException {
        WebElement otpField = driver.findElement(By.id("otp-input"));
        otpField.sendKeys(otp);  // Enter the fetched OTP
        WebElement verifyButton = driver.findElement(By.id("verify-otp-btn"));
        verifyButton.click();  // Click on the verify button
    }
     public static void click(){
         driver.findElement(By.partialLinkText("Commander")).click();
     }

}


