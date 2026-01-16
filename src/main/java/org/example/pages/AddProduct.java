package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Properties;

public class AddProduct extends BasePage {
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

    public AddProduct(WebDriver driver) {
        super(driver);
    }

    public  void Addproductnew() throws InterruptedException {
        driver.navigate().refresh();
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Products')]");  // Navigate to Products
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Add Product')]");  // Click Add Product

        // Enter product details
        SeleniumUtils.waitAndSendKeys(driver, "//input[@placeholder='Enter product name']", "Water Bottle");
        SeleniumUtils.waitAndSendKeys(driver, "(//div[@class='ql-container ql-snow'])//div[contains(@data-placeholder,'Please write')]", "Test");
        SeleniumUtils.waitAndClick(driver, "//div[normalize-space(text())='Status']/following::span[text()='Select'][1]");  // Open Active dropdown
        SeleniumUtils.waitAndClick(driver, "(//span[normalize-space(text()='Active')]/parent::div[contains(@class,'css-18zb7o8')])[4]");  // Select Active status
        //SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Fast Procurement*')]//following-sibling::div");  // Open Fast Procurement dropdown
        // SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'True')]//parent::div");  // Select True for Fast Procurement
        Thread.sleep(2000);
        SeleniumUtils.waitAndClick(driver, "//div[normalize-space(text())='Visibility']/following::span[contains(@class,'MuiSwitch-root')][1]//span");  // Open Display dropdown
        SeleniumUtils.waitAndSendKeys(driver, "(//input[contains(@class,'css-15v65ck')])[2]", "90");
        SeleniumUtils.waitAndSendKeys(driver, "(//input[contains(@class,'css-15v65ck')])[3]", "100");


        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Select Category')]//following-sibling::div");  // Open Category dropdown

        SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-2')]");  // Select Category

        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Save')]");
    }




    public void addProducts() throws InterruptedException {
        driver.navigate().refresh();
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Products')]");  // Navigate to Products
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Add Product')]");  // Click Add Product

        // Enter product details
        SeleniumUtils.waitAndSendKeys(driver, "//input[@placeholder='Enter product name']", "Water Bottle");
        SeleniumUtils.waitAndSendKeys(driver, "(//div[@class='ql-container ql-snow'])//div[contains(@data-placeholder,'Please write')]", "Test");

        //******upload image
        // Locate the file input element
        WebElement uploadElement = driver.findElement(By.xpath("//label[@for='raised-button-file']"));
        uploadElement.click();// use correct locator

        // Provide the absolute path of the file
        uploadElement.sendKeys("C:\\Users\\aafre\\OneDrive\\Pictures\\Screenshots\\image.png");
                  // *****//


        // Click submit (if required)
       // driver.findElement(By.id("submitBtn")).click();

        SeleniumUtils.waitAndClick(driver, "//div[normalize-space(text())='Status']/following::span[text()='Select'][1]");  // Open Active dropdown
        SeleniumUtils.waitAndClick(driver, "(//span[normalize-space(text()='Active')]/parent::div[contains(@class,'css-18zb7o8')])[3]");  // Select Active status
        //SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Fast Procurement*')]//following-sibling::div");  // Open Fast Procurement dropdown
       // SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'True')]//parent::div");  // Select True for Fast Procurement
        SeleniumUtils.waitAndClick(driver, "//input[@type='checkbox']");  // Open Display dropdown
        SeleniumUtils.waitAndClick(driver, "(//span[contains(text(),'True')]//parent::div)[2]");  // Select True for Display
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Cost Price')]//following-sibling::div//child::input", "90");
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Selling Price')]//following-sibling::div//child::input", "100");
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'HSN')]//following-sibling::div//child::input", "22324567");
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Select *')]");  // Open GST dropdown
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'12')]");  // Select 12% GST
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Zoho Id')]//following-sibling::div//child::input", "254574");

        // Scroll and select category
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[contains(text(),'Active*')]//following-sibling::div")));
        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Select Category')]//following-sibling::div");  // Open Category dropdown
        SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-2')]");  // Select Category

        // Save the product
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Save')]");

        // Confirm the product is saved
        WebElement save = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]");
        String saveText = save.getText();
        save.click();
        System.out.println(saveText);
        System.out.println("Product successfully created");
    }
}


//package org.example.pages;
//
//import org.example.Base.BasePage;
//import org.example.utils.SeleniumUtils;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.InputStream;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
//public class AddProduct extends BasePage {
//    private static final Properties props;
//    private static final String BASE_URL;
//
//    static {
//        props = new Properties();
//        try (InputStream input = AddOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
//            props.load(input);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
//        }
//        BASE_URL = props.getProperty("base.url");
//    }
//
//    public AddProduct(WebDriver driver) {
//        super(driver);
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//        AddProduct addPro = new AddProduct(driver);
//
//        AddOrder Ord = new AddOrder(driver);
//        Ord.login();
//        addPro.addProducts();
//    }
//
//    public void addProducts() throws InterruptedException {
//        driver.navigate().refresh();
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Products')]");  // Navigate to Products
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Add Product')]");  // Click Add Product
//
//        // Enter product details
//        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Name')]//following-sibling::div//child::input", "Water Bottle");  // Set Product Name
//        SeleniumUtils.waitAndSendKeys(driver, "(//label[contains(text(),'Add product description')]//following-sibling::div//child::textarea)[1]", "Test");  // Set Description
//        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Active*')]//following-sibling::div");  // Open Active dropdown
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Active')]//parent::div");  // Select Active status
//        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Fast Procurement*')]//following-sibling::div");  // Open Fast Procurement dropdown
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'True')]//parent::div");  // Select True for Fast Procurement
//        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Display*')]//following-sibling::div");  // Open Display dropdown
//        SeleniumUtils.waitAndClick(driver, "(//span[contains(text(),'True')]//parent::div)[2]");  // Select True for Display
//        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Cost Price')]//following-sibling::div//child::input", "90");  // Set Cost Price
//        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Selling Price')]//following-sibling::div//child::input", "100");  // Set Selling Price
//        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'HSN')]//following-sibling::div//child::input", "1234567");  // Set HSN Code
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Select *')]");  // Open GST dropdown
//        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'12')]");  // Select 12% GST
//        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Zoho Id')]//following-sibling::div//child::input", "254574");  // Set Zoho ID
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//      //js.executeScript("window.scrollBy(0, -500);");
//        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[contains(text(),'Active*')]//following-sibling::div")));
//        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Select Category')]//following-sibling::div");  // Open Category dropdown
//        SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-2')]");  // Select Category
//        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Save')]" );  // Save the product
//
//        // Confirm the product is saved
//        WebElement save = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]");
//        String saveText = save.getText();
//        save.click();
//        System.out.println(saveText);
//        System.out.println("Product successfully created");
//    }
//}
//
////package org.example.pages;
////
////
////import dev.failsafe.internal.util.Assert;
////import org.example.Base.BasePage;
////import org.example.utils.SeleniumUtils;
////import io.github.bonigarcia.wdm.WebDriverManager;
////import org.openqa.selenium.By;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.*;
////import org.openqa.selenium.chrome.ChromeDriver;
////
////import java.io.InputStream;
////import java.util.Properties;
////import java.util.concurrent.TimeUnit;
////
////public class AddProduct extends BasePage {
////    //public static WebDriver driver;
////    private static final Properties props;
////    private static final String BASE_URL;
////
////    static {
////        props = new Properties();
////        try (InputStream input = AddOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
////            props.load(input);
////        } catch (Exception e) {
////            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
////        }
////       BASE_URL = props.getProperty("base.url");
////    }
////
////    public AddProduct(WebDriver driver) {
////        super(driver);
////    }
////
////
////
////    public static void main(String[] args) throws InterruptedException {
////        WebDriverManager.chromedriver().setup();
////        WebDriver driver = new ChromeDriver();
////        driver.manage().window().maximize();
////        driver.get(BASE_URL); // optional, if needed
////        AddProduct addPro = new AddProduct(driver);
////
////        AddOrder Ord = new AddOrder(driver);
////        Ord.login();
////        addPro.AddProducts();
////       // AddProducts();
////    }
////
