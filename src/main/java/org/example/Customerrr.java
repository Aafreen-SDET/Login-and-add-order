package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Customerrr {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        setup();
        customer();
        //ModularizeSwageazy.login();

    }
    public static WebDriver setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://dev-new-commander-cuctc4ffhadaexfx.westindia-01.azurewebsites.net/");
        driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        return driver;
    }

    public static void customer () throws InterruptedException {
        driver.get("https://dev-new-commander-cuctc4ffhadaexfx.westindia-01.azurewebsites.net/");
        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
        //driver.get("https://dev-new-commander-cuctc4ffhadaexfx.westindia-01.azurewebsites.net/");
        driver.findElement(By.xpath("(//input[@id='email'])[1]")).sendKeys("afreen.khan@swageazy.com");
        driver.findElement(By.id("password")).sendKeys("Dania@123");
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
        //Thread.sleep(3000);
        driver.navigate().refresh();

        //click on side panel(customer)
        driver.findElement(By.xpath("(//div[contains(@class,'css-12e8yo5')])[8]")).click();
        //Click on Add Customer button
        driver.findElement(By.xpath("//*[contains(text(),'Add Customer')]")).click();
        //name
        driver.findElement(By.xpath("//input[@id=':r4:']")).sendKeys("Syed farhan");
        //Thread.sleep(2000);
        //RedemptionURl
        driver.findElement(By.xpath("//input[@id=':rn:']")).sendKeys("https://redemption.swageazy.com");
        //Thread.sleep(2000);
        //RedemptionEmail
        driver.findElement(By.xpath("//textarea[@id=':ro:']")).sendKeys("team@swageazy.com");
        //RedemptionEmailFrom(name)
        driver.findElement(By.xpath("//textarea[@id=':rm:']")).sendKeys("sagar bothra");
        //*ConfigSelection*//
        driver.findElement(By.xpath("//div[contains(@class,'css-nb4t3y')]")).click();
        driver.findElement(By.xpath ("(//*[contains(text(),'True')])[2]")).click();
        body.click();
        //Email Redemption
        driver.findElement(By.xpath("(//span[contains(@class,'css-5990k7')])[2]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'True')])[2]")).click();
        body.click();
        //Shopify
        driver.findElement(By.xpath("(//span[contains(@class,'css-5990k7')])[3]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'True')])[2]")).click();
        body.click();
        //RedmeptionEmail
        driver.findElement(By.xpath("(//span[contains(@class,'css-5990k7')])[4]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'True')])[2]")).click();
        body.click();
        //
        driver.findElement(By.xpath("(//span[contains(@class,'css-5990k7')])[5]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'True')])[2]")).click();
        body.click();
        //
        driver.findElement(By.xpath("(//span[contains(@class,'css-5990k7')])[6]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'True')])[2]")).click();
        body.click();

        //
    }
}

