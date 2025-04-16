package org.example;
//import org.example.AddOrder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Customer {
    static WebDriver driver; // Declare WebDriver as static

    public static void main(String[] args) throws InterruptedException {
        setup();  // Initializes driver and logs in
        login();
        AddProduct();

    }
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://dev-new-commander.swageazy.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

    }
    public static void login() {
        WebElement username = driver.findElement(By.xpath("//*[contains(@class,'css-9jacqo')]"));
        username.sendKeys("afreen.khan@swageazy.com");
        WebElement password = driver.findElement(By.xpath("//*[contains(@class,'css-1qs43bf')] "));
        password.sendKeys("Dania@123");
        WebElement next = driver.findElement(By.xpath("//*[contains(@class,'css-olgitz')]"));
        next.click();
    }

    public static void AddProduct() throws InterruptedException {
        String productName= "Water Bottle";
        String Desc = "Test";
        String Cp = "90";
        String Sp = "100";
        String HSN_code = "1234567";
        String Zoho_Id = "254574";
        //driver.navigate().refresh();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[contains(text(),'Products')]")));
        WebElement Product =driver.findElement(By.xpath("//span[contains(text(),'Products')]"));
        Product.click();
        driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
        Thread.sleep(2000);
        //WebElement product_name = driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[1]"));
        driver.navigate().refresh();
        //Enter product name
        WebElement product_name =driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[1]"));
        product_name.click();
        product_name.sendKeys(productName);
        //Enter desc
        driver.findElement(By.xpath("(//textarea[contains(@class,'css-u189mq')])[1]")).sendKeys(Desc);
        //Enter SP
        driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[2]")).sendKeys(Cp);
        //Enter Cp
        driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[3]")).sendKeys(Sp);
        //Enter HSN
        driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[6]")).sendKeys(HSN_code);
        //Enter Zoho-ID
        driver.findElement(By.xpath("(//input[contains(@class,'css-1ruewdp')])[7]")).sendKeys(Zoho_Id);
        //select the status
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//span[contains(text(),'Select')]//parent::div)[2]")));
        driver.findElement(By.xpath("(//span[contains(text(),'Select')]//parent::div)[2]")).click();
        driver.findElement(By.xpath("//li[contains(@role,'option')]//span[contains(@class,'MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-6swbdb')][normalize-space()='Active']")).click();
        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
    }
    ////li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters Mui-selected MuiMenuItem-root MuiMenuItem-gutters Mui-selected css-1rfhbw0')]
    }


