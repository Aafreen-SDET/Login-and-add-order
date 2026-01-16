package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProjectManagement extends BasePage {
    private static final Properties props;
    private static final String BASE_URL;
    //String ProjectId = "2085"; // use this when test this specific class  else else the order id that u got from  txt file
   private String ProjectId;
//
    public void readOrderIdFromFile() {
        try {
            // Read all content from file
            ProjectId= new String(Files.readAllBytes(Paths.get("orderId.txt"))).trim();
            System.out.println("Order ID read from file: " + ProjectId);
        } catch (IOException e) {
            System.out.println("Error reading order ID file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static {
        props = new Properties();
        try (InputStream input = EditOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to  load config.properties: " + e.getMessage(), e);
        }
        BASE_URL = props.getProperty("base.url");
    }

    public ProjectManagement(WebDriver driver) {
        super(driver);
    }


    public void OpenProjectMgt() {
         readOrderIdFromFile();
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Project Mgmt.')]");
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'List')]");
        SeleniumUtils.waitAndSendKeys(driver, "//input[@placeholder='Search by order id or customer name']", ProjectId);
        //press enter
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search by order id or customer name']"));
        searchInput.sendKeys(Keys.ENTER);
        //SeleniumUtils.waitAndClick(driver,"");
        SeleniumUtils.waitAndClick(driver, "(//td[contains(text(),'')]//a[contains(text()," + ProjectId + ")])[1]");
        //SeleniumUtils.waitAndClick(driver, "(//button[contains(@class,'css-5zjugz')])[2]");
    }

    public void switchToLatestTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));  // switches to new tab
    }

    //SOURCING OPS ACTIONS

    public void makeCustomisationOFForON() throws InterruptedException {
        readOrderIdFromFile();

        Thread.sleep(2000);
        SeleniumUtils.waitAndClick(driver, "//div[normalize-space()='Sourcing Ops']");
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='MuiBox-root css-zp0psn']"));
        System.out.println(elements);
        WebElement FirstElement = elements.get(0);
        FirstElement.click();
        for (int i = 0; i <= elements.size() - 1; i++) {
            WebElement customisation = driver.findElement(By.xpath("//span[@class='MuiSwitch-track css-1khxa25']"));
            customisation.click();
            WebElement next = driver.findElement(By.xpath("//button[contains(@class,'css-1xo5q7z')]"));
            if (next.isEnabled()) {
                next.click();
            } else {
                driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
            }

        }


    }

    public void RaisePR() throws InterruptedException {
        readOrderIdFromFile();
        Thread.sleep(2000);

        switchToLatestTab();

        SeleniumUtils.waitAndClick(driver, "//div[normalize-space()='Sourcing Ops']");
        //Thread.sleep(2000);
        // click on  select all
        SeleniumUtils.waitAndClick(driver, "//span[@class='MuiButtonBase-root MuiCheckbox-root MuiCheckbox-colorPrimary MuiCheckbox-sizeMedium PrivateSwitchBase-root MuiCheckbox-root MuiCheckbox-colorPrimary MuiCheckbox-sizeMedium MuiCheckbox-root MuiCheckbox-colorPrimary MuiCheckbox-sizeMedium css-w7w8ff']//*[name()='svg']");
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Create PR')]");
        SeleniumUtils.waitAndClick(driver, "//button[@aria-label='Choose date']"); // click on calender icon and choose cur date
        String day = "29";
        SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='" + day + "']"); //choose date
        SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@class,'css-s43tfo')]", "Foxtale");  //select vendor
        SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-0')]"); //select 1st matched option
        SeleniumUtils.waitAndSendKeys(driver, "//div[contains(@class,'css-xihpsw')]//input", "1000"); //other charges
        List<WebElement> qtyFields = driver.findElements(By.xpath("//input[@placeholder='Enter quantity']"));

        //Example dynamic list of quantities (size must match number of fields)
        List<String> qtyValues = Arrays.asList("2", "3", "1", "6", "10", "8");

        for (int i = 0; i < qtyFields.size(); i++) {
            qtyFields.get(i).clear();
            qtyFields.get(i).sendKeys(qtyValues.get(i));
        }

        SeleniumUtils.waitAndClick(driver, "(//button[text()='Create PR'])[2]");
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Got it')]");
    }



    //PR PO ACTIONS
    public void approvePR() throws InterruptedException {
        // Read order ID from file
        readOrderIdFromFile();

        // Open PR & PO Listing
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'PR & PO')]");
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Purchase Requests')]");
        Thread.sleep(2000);

        // Search order ID
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@placeholder,'Order Id')]"));
        searchField.sendKeys(ProjectId);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        try {
            // Get the 1st row
            WebElement firstRow = driver.findElement(By.xpath("(//tr[@class='MuiTableRow-root css-y2tn7z'])[1]"));
            // Find all approve buttons within the 1st row only
            List<WebElement> approveButtons = firstRow.findElements(By.xpath(".//button[normalize-space()='Approve']"));
            if (approveButtons.isEmpty()) {
                System.out.println("No Approve buttons found in 1st row. Passing...");
                return;
            }
            System.out.println("Found " + approveButtons.size() + " Approve button(s) in 1st row");

            // Click each approve button in the 1st row
            for (int i = 0; i < approveButtons.size(); i++) {
                try {
                    // Re-find buttons after each click
                    WebElement row = driver.findElement(By.xpath("(//tr[@class='MuiTableRow-root css-y2tn7z'])[1]"));
                    List<WebElement> buttons = row.findElements(By.xpath(".//button[normalize-space()='Approve']"));

                    if (buttons.isEmpty()) {
                        System.out.println("No more Approve buttons in 1st row.");
                        break;
                    }

                    WebElement approveButton = buttons.get(0); // Always click first available

                    if (!approveButton.isDisplayed()) {
                        System.out.println("Approve button not visible. Skipping...");
                        break;
                    }
                    System.out.println("Clicking Approve button " + (i + 1) + " from 1st row");
                    approveButton.click();
                    // Confirmation and success popups
                    SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'YES')]");
                    SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Got it')]");
                    Thread.sleep(800);

                } catch (Exception e) {
                    System.out.println("Error with button " + (i + 1) + ": " + e.getMessage());
                    break;
                }
            }

            System.out.println("Approval process completed.");

        } catch (NoSuchElementException e) {
            System.out.println("1st row not found. Passing scenario...");
        } catch (Exception e) {
            System.out.println("Error during approval: " + e.getMessage());
        }

        SeleniumUtils.waitAndClick(driver, "(//button[@type='button'][normalize-space()='Generate PO'])[1]");
        SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Got it')]");
        WebElement text = driver.findElement(By.xpath("//div[@class='MuiDialogContent-root css-ba27kg']"));
        System.out.println(text.getText());
    }


//Inventory OPS

    public void InventoryOps() {
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Project Mgmt.')]");
        SeleniumUtils.waitAndClick(driver,"//div[normalize-space()='Inventory Ops']");
    }
}