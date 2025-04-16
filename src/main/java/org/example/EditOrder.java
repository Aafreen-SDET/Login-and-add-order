package org.example;

import com.github.dockerjava.core.dockerfile.DockerfileStatement;
import com.github.dockerjava.transport.DockerHttpClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
public class EditOrder {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        AddOrder.setup(driver);           // opens the URL
        AddOrder.login(driver);           // logs in to the system
        //RaiseStockCheckRequest();         // raises SCR directly
        RaiseProjectRequest();            // raised project request
    }

    public static void RaiseStockCheckRequest() throws InterruptedException {
        String orderid = "1942"; // Existing order with added products
        driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId=" + orderid);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Raise Stock Check Request')]")).click();

        List<WebElement> textareas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//label[contains(text(), 'Write Product Notes')]/following-sibling::div[contains(@class, 'MuiOutlinedInput-root')]//textarea")));

        System.out.println("Number of textareas found: " + textareas.size());

        for (WebElement textarea : textareas) {
            if (textarea.isDisplayed() && textarea.isEnabled()) {
                textarea.clear();
                textarea.sendKeys("NA");
                System.out.println("Typed 'NA' into textarea");
                driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
            }
        }
    }

    public static void RaiseProjectRequest() throws InterruptedException {
        String orderid = "1942"; // Existing order with added products
        driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId=" + orderid);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        Thread.sleep(2000);
        //click on RPR button
        driver.findElement(By.xpath("//button[contains(text(),'Raise Project Request')]")).click();
        Thread.sleep(2000);
        // yes to POP-up
        driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
        //fill the form fields
        driver.findElement(By.id("company_name")).sendKeys("swageazy");         // company name
        driver.findElement(By.id("brand_name")).sendKeys("swag");                //brand name
        driver.findElement(By.id("client_poc")).sendKeys("Aafreen khan");
        driver.findElement(By.id("client_poc_contact_no")).sendKeys("9044717377");
        //Delivery date
        driver.findElement(By.xpath("//button[@aria-label='Choose date']")).click();   //   click on date icon
        driver.findElement(By.xpath(" //button[text()='26']")).click();   // click on date

        driver.findElement(By.xpath("//div[contains(@class, 'css-12k1dgj')][1]")).click();
        //driver.findElement(By.xpath("//button[text()='OK']")).click();  // click on OK button
        //js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
        driver.findElement(By.xpath("(//div[@role='combobox'])[3]")).click();     // open dropdown
        driver.findElement(By.xpath("//span[normalize-space()='No']")).click();    // click on "NO" Option
        driver.findElement(By.xpath("(//div[@role='combobox'])[4]")).click();      // mockup upload
        driver.findElement(By.xpath("//span[normalize-space()='Approved']")).click();     // click on Approved
        driver.findElement(By.xpath("(//div[@role='combobox'])[5]")).click();             // click on DIY redemption
        driver.findElement(By.xpath("(//span[normalize-space()='No'])[2]")).click();           //click on NO
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();            //click on order type
        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-2')]")).click();
        Thread.sleep(2000);// select the 2nd option
        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]"))  ;   //click on mode of shipemnt
        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]"));




    }


}






