package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Properties;

public class Address extends BasePage {
    private static final Properties props;
    private static final String BASE_URL;
    String name = "Rest Assured";

    static {
        props = new Properties();
        try (InputStream input = EditOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to  load config.properties: " + e.getMessage(), e);
        }
        BASE_URL = props.getProperty("base.url");
    }

    public Address(WebDriver driver) {

        super(driver);
    }


     public void FindAddress(){

         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Address')]");
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'List')]");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@placeholder='Search by name, email']",name);
         WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search by name, email']"));
         searchInput.sendKeys(Keys.ENTER);

     }
     public void VerifyResult(){
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'List')]");
        List<WebElement> Res = driver.findElements(By.xpath("//table//thead//following-sibling::tbody//tr"));
        for(WebElement Options: Res)
        if(Options.getText().contains(name)){
            System.out.println("Result found");
        }
     }
     public void addAddress () throws InterruptedException {
        String customer = "Rest Assured";
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Address')]");
         // SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'List')]");
         //SeleniumUtils.waitAndClick(driver,"//button[contains(@class,'css-egoe36')]");
         SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Add Address')]");
         //Thread.sleep(2000);
         SeleniumUtils.waitAndSendKeys(driver,"(//input[@type='text'])[1]",customer);
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@Placeholder='First name']","Aafreen");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@Placeholder='Address 1']","Paras trinity");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@Placeholder='Postalcode']","242001");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@Placeholder='Contact No']","9044614227");
         SeleniumUtils.waitAndSendKeys(driver,"//input[@Placeholder='Email']","abc@swageazy.com");
         SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'Select Country')]//parent::div//child::input","India");
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'Select State')]//parent::div//child::input","Haryana");
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'Select City')]//following-sibling::div//child::input","gurgaon");
         SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");
         SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Save Details')]");
         SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Save')]");






     }
}
