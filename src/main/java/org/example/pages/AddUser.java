package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class AddUser extends BasePage {
    private static final Properties props;
    private static final String BASE_URL;

    static {
        props = new Properties();
        try (InputStream input = AddProduct.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
        BASE_URL = props.getProperty("base.url");
    }

    public AddUser(WebDriver driver) {
        super(driver);
    }

    public void UserAdd(){
        String SelectCustome="RRohaan";
        String Email= "Shiv@swageazy.com";
        String uniqueID = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Email ="user" + uniqueID + "@swageazy.com";
            String UserName ="shiv";

            String PhoneNumber = "+919044614227";
            String Password =Email;
            String ConFirmPassword=Email;
            driver.navigate().refresh();

        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'User')]");
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Add User')]");

        //SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Select Customer Name')]");
        SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'Select Customer Name')]//following-sibling::div//input",SelectCustome);
        SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
        SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'First')]//following-sibling::div//input",UserName);
        SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'91')]//following-sibling::div//input",PhoneNumber);
        SeleniumUtils.waitAndSendKeys(driver,"(//label[contains(text(),'Password')]//following-sibling::div//input)[1]",Password);
        SeleniumUtils.waitAndSendKeys(driver,"(//label[contains(text(),'Password')]//following-sibling::div//input)[2]",ConFirmPassword);
        SeleniumUtils.waitAndSendKeys(driver,"(//label[contains(text(),'Email')]//following-sibling::div//input)[1]",Email);
         SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Save')]");


    }
}
