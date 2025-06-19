package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;

import java.io.InputStream;
import java.util.Properties;

public class AddCustomer extends BasePage {

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

        public AddCustomer(WebDriver driver) {
            super(driver);
        }

        public void Customer(){
            driver.navigate().refresh();
            SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Customer')]");
            SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Add Customer')]");
             String Customer = "Amisha";
             SeleniumUtils.waitAndSendKeys(driver,"//label[contains(text(),'Name')]//following-sibling::div//input",Customer);
             SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Coupon Redemption')]//parent::div");
             SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'True')]"); ////span[contains(text(),'True')]//parent::div[contains(@class,'css-18zb7o8')]
             SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Email Redemption')]//parent::div");
              SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Inventory')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'GOC')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Sez')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Campaign')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Swagstore')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Shopify')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//label[contains(text(),'Select Type')]//parent::div");
            SeleniumUtils.waitAndClick(driver,"(//div[contains(@class,'css-d1xm6m')])[3]//ul//li[2]");
            SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Save')]");



        }
}
