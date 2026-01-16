package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.Properties;

public class TaskTracker extends BasePage {

        private static final Properties props;
        private static final String BASE_URL;

        static {
            props = new Properties();
            try (InputStream input = EditOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
                props.load(input);
            } catch (Exception e) {
                throw new RuntimeException("Failed to  load config.properties: " + e.getMessage(), e);
            }
            BASE_URL = props.getProperty("base.url");
        }


    public TaskTracker(WebDriver driver) {
        super(driver);
    }
     public void AddTask(){
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Task Tracker')]");
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Add Task')]");
         SeleniumUtils.waitAndSendKeys(driver,"//input[contains(@class,'css-15v65ck') and (@id ='title')]","yeah , finally last few steps are left for completing the automation");
         //SeleniumUtils.waitAndClick(driver,"(//input[contains(@class,'css-s43tfo') and (@type ='text')])[1]");
         SeleniumUtils.waitAndSendKeys(driver,"(//input[contains(@class,'css-s43tfo') and (@type ='text')])[1]","Aaaf");
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         //SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'To Do')]");
         SeleniumUtils.waitAndSendKeys(driver,"(//input[contains(@class,'css-s43tfo') and (@type ='text')])[1]","02027");
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         SeleniumUtils.waitAndClick(driver,"//input[@id  ='due_date']");
         SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Select priority *')]//following-sibling::div//child::div");
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'HIGH')]");
         SeleniumUtils.scrollToBottom(driver);
         SeleniumUtils.waitAndSendKeys(driver,"//div[contains(@class,'ql-container ql-snow')]//div","abaa dabba jabba");
         SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Create Task')]");
         SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Okay')]");

     }
}


