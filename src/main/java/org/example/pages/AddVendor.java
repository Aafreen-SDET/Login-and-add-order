package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AddVendor extends BasePage {
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

    public AddVendor(WebDriver driver) {
        super(driver);
    }



    public void vendor() throws InterruptedException, AWTException {
        String vendor_name ="Dania";
        String vendor_email1="test@swageazy.com";
        String GSTIN="8786639GSTIN";
        String PAN="12345633";
        String Address = "Paras Trnity";
        String Poc_Name = "Aafreen";
        String Phone = "9087867674";
        //String email_poc= "Aafreenkhanzee@gmail.com";
        //driver.findElement(By.xpath("//span[contains(text(),'Vendors')]")).click();
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Vendors')]");
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Add Vendor')]");
        Thread.sleep(2000);
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='name']",vendor_name);
        SeleniumUtils.waitAndSendKeys(driver,"(//input[@id='email'])[1]",vendor_email1);
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='gst_number']",GSTIN);
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='pan_number']",PAN);
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='address_line_1']",Address);
        SeleniumUtils.waitAndClick(driver,"//div[@id='demo-simple-select']");
        SeleniumUtils.waitAndClick(driver,"//li[contains(text(),'2')]");
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='first_name']",Poc_Name);
        SeleniumUtils.waitAndSendKeys(driver,"//input[@id='phone']",Phone);
        //SeleniumUtils.waitAndSendKeys(driver,"//input[@id='first_name']",email_poc);
        SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Add POC')]");


        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Printer')]//preceding-sibling::span");
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Logistics')]//preceding-sibling::span");
        SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Product Supplier')]//preceding-sibling::span");
        //for(int i =0 ; i<=3; i++) {
       // SeleniumUtils.waitAndClick(driver, "svg.svg-inline--fa.fa-upload");
//       List<WebElement> fil = driver.findElements(By.cssSelector("svg.svg-inline--fa.fa-upload"));
//        fil.sendKeys("\"C:\\Users\\aafre\\OneDrive\\Pictures\\Screenshots\\2684 - Copy.png\"");
        String[] filePaths = {
                "C:\\Users\\aafre\\OneDrive\\Pictures\\Screenshots\\2684 - Copy.png",
                "C:\\Users\\aafre\\OneDrive\\Pictures\\Screenshots\\PersonalizedInsert_1666782505855_1738759469170.jpg",
                "C:\\Users\\aafre\\OneDrive\\Pictures\\Screenshots\\2684 - Copy.png"
        };
        List<WebElement> svgButtons = driver.findElements(By.cssSelector("svg.svg-inline--fa.fa-upload"));

        // Initialize Robot outside the loop for better performance
        Robot robot = new Robot();

        for (int i = 0; i < svgButtons.size() && i < filePaths.length; i++) {
            // 1. Click the SVG icon to open the file upload dialog
            // Wait for the dialog to be hidden
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("MuiDialog-container")));

            svgButtons.get(i).click();

            // Wait for the file upload button to appear
            WebElement uploadTextElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='upload-text'])[2]")));
            uploadTextElement.click();

            // 2. Use Robot class to handle the OS file picker
            StringSelection filePathSelection = new StringSelection(filePaths[i]);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);

            // Paste the file path (Ctrl+V)
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Press Enter to confirm
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            // Click the "Upload" button
            WebElement uploadButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Upload')]")));
            uploadButton.click();

            // Click "Okay" after the file is uploaded
            WebElement okayButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Okay')]")));
            okayButton.click();
        }
        //SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Okay')]");

        // Click the "Add Vendor" button after all uploads are done
        WebElement addVendorButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Vendor')]")));
        addVendorButton.click();
        System.out.println("All files uploaded successfully, Vendor added!");


    }


}
