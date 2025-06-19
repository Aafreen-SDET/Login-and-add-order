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

    //private WebDriver driver;

//   // @BeforeMethod
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get(BASE_URL);
//    }

//    //@AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

//    //@Test
//    public void testAddProduct() throws InterruptedException {
//        AddOrder ord = new AddOrder(driver);
//        ord.login();  // Assuming the login method is defined in AddOrder class
//
//        addProducts();
//    }

    public void addProducts() throws InterruptedException {
        driver.navigate().refresh();
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Products')]");  // Navigate to Products
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Add Product')]");  // Click Add Product

        // Enter product details
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Name')]//following-sibling::div//child::input", "Water Bottle");
        SeleniumUtils.waitAndSendKeys(driver, "(//label[contains(text(),'Add product description')]//following-sibling::div//child::textarea)[1]", "Test");
        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Active*')]//following-sibling::div");  // Open Active dropdown
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Active')]//parent::div");  // Select Active status
        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Fast Procurement*')]//following-sibling::div");  // Open Fast Procurement dropdown
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'True')]//parent::div");  // Select True for Fast Procurement
        SeleniumUtils.waitAndClick(driver, "//label[contains(text(),'Display*')]//following-sibling::div");  // Open Display dropdown
        SeleniumUtils.waitAndClick(driver, "(//span[contains(text(),'True')]//parent::div)[2]");  // Select True for Display
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Cost Price')]//following-sibling::div//child::input", "90");
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'Selling Price')]//following-sibling::div//child::input", "100");
        SeleniumUtils.waitAndSendKeys(driver, "//label[contains(text(),'HSN')]//following-sibling::div//child::input", "1234567");
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
////    public static void AddProducts() throws InterruptedException {
////       // driver.get("https://dev-new-commander.swageazy.com/");
////        driver.navigate().refresh();
////        String productName= "Water Bottle";
////        String Desc = "Test";
////        String Cp = "90";
////        String Sp = "100";
////        String HSN_code = "1234567";
////        String Zoho_Id = "254574";
////        //driver.navigate().refresh();
//////        JavascriptExecutor js = (JavascriptExecutor) driver;
//////        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(text(),'Products')]")));
////        Thread.sleep(2000);
////        WebElement Product =driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
////        Product.click();
////        Thread.sleep(2000);
////        driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
////        Thread.sleep(2000);
////        //WebElement product_name = driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[1]"));
////        //driver.navigate().refresh();
////        //Enter product name
////        WebElement product_name =driver.findElement(By.xpath("//label[contains(text(),'Name')]//following-sibling::div//child::input"));
////       // product_name.click();
////        product_name.sendKeys(productName);
////        //Enter desc
////        driver.findElement(By.xpath("(//label[contains(text(),'Add product description')]//following-sibling::div//child::textarea)[1]")).sendKeys(Desc);
////      //select active
////        driver.findElement(By.xpath("//label[contains(text(),'Active*')]//following-sibling::div")).click();
////        driver.findElement(By.xpath("//span[contains(text(),'Active')]//parent::div")).click();
////        //select fast procurement
////        driver.findElement(By.xpath("//label[contains(text(),'Fast Procurement*')]//following-sibling::div")).click();
////        driver.findElement(By.xpath("//span[contains(text(),'True')]//parent::div")).click();
////        //
////        driver.findElement(By.xpath("//label[contains(text(),'Display*')]//following-sibling::div")).click();
////        Thread.sleep(2000);
////        driver.findElement(By.xpath("(//span[contains(text(),'True')]//parent::div)[2]")).click();
////        Thread.sleep(2000);
////        //Enter SP
////        driver.findElement(By.xpath("//label[contains(text(),'Cost Price')]//following-sibling::div//child::input")).sendKeys(Cp);
////        //Enter Cp
////        driver.findElement(By.xpath("//label[contains(text(),'Selling Price')]//following-sibling::div//child::input")).sendKeys(Sp);
////        //Enter HSN
////        driver.findElement(By.xpath("//label[contains(text(),'HSN')]//following-sibling::div//child::input")).sendKeys(HSN_code);
////        //Enter Zoho-ID
////        //Gst
////        driver.findElement(By.xpath("//span[contains(text(),'Select *')]")).click();
////        Thread.sleep(2000);
////        driver.findElement(By.xpath("//span[contains(text(),'12')]")).click();
////        driver.findElement(By.xpath("//label[contains(text(),'Zoho Id')]//following-sibling::div//child::input")).sendKeys(Zoho_Id);
////        //select the status
////        Thread.sleep(3000);
////        JavascriptExecutor js = (JavascriptExecutor) driver;
////        //js.executeScript("window.scrollBy(0, -500);");
////         js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[contains(text(),'Active*')]//following-sibling::div")));
////        driver.findElement(By.xpath("//label[contains(text(),'Select Category')]//following-sibling::div")).click();
////        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-2')]")).click();
////        WebElement body = driver.findElement(By.tagName("body"));
////        body.click();
////        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
////        Thread.sleep(2000);
////        WebElement save = driver.findElement(By.xpath("//button[contains(text(),'Okay')]"));
////        String SAVETEXT= save.getText();
////        save.click();
////        System.out.println(SAVETEXT);
////        System.out.println("Product succesfully created");
////    }
////    ////li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters Mui-selected MuiMenuItem-root MuiMenuItem-gutters Mui-selected css-1rfhbw0')]
////    }
////
////
