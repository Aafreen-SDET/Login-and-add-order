package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.InputStream;
import java.util.Properties;

public class Employee extends BasePage {
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

    public void AddEmployee() {
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Employee')]");
        driver.navigate().refresh();
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Add Employee')]");
        SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@name,'name')]", "Sam");
        SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@name,'email')]", "Afreen.khan@swageazy.com");
        SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@name,'calling_code')]", "+91");
        SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@name,'phone_number')]", "9044614227");
        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Teams')]//following-sibling::div//input");
        SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-0')]");
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Save')]");
    }

//    public void verifTheAdd(){
//        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='standard-search']","Sam");
//        WebElement res = driver.findElement(By.cssSelector("css-1hwbu71"));
//        String results = res.getText();
//        if(results.contains("Sam")){
//            System.out.println("Succesfully added and data has been fetched");
//        }
//}



    public Employee(WebDriver driver) {
        super(driver);
    }
}




