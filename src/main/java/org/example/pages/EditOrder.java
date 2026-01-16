package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static org.example.utils.SeleniumUtils.waitForBackdropToDisappear;

public class EditOrder extends BasePage {
    private static final Properties props;
    private static final String BASE_URL;

    static {
        props = new Properties();
        try (InputStream input = EditOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
       } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
        BASE_URL = props.getProperty("base.url");
    }

    public EditOrder(WebDriver driver) {

        super(driver);
    }


    public void raiseStockCheckRequest(String orderId) {
        try {
            driver.get(BASE_URL + "/orders/add-orders?orderId=" + orderId);
            SeleniumUtils.scrollToBottom(driver);
            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Raise Stock Check Request')]");

            List<WebElement> textareas =driver.findElements(By.xpath(
                    "//label[contains(text(), 'Write Product Notes')]/following-sibling::div[contains(@class, 'MuiOutlinedInput-root')]//textarea"));
            //List<WebElement> textareas = SeleniumUtils.waitUntilVisibleAll(driver,
                  //  "//label[contains(text(), 'Write Product Notes')]/following-sibling::div[contains(@class, 'MuiOutlinedInput-root')]//textarea");
            for (WebElement textarea : textareas) {
                if (textarea.isDisplayed() && textarea.isEnabled()) {
                    textarea.clear();
                    textarea.sendKeys("NA");
                    SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Confirm')]");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to raise stock check request: " + e.getMessage(), e);
        }
    }

    public void raiseProjectRequest(String orderId) {
        try {
            driver.get(BASE_URL + "/orders/add-orders?orderId=" + orderId);
            SeleniumUtils.scrollToBottom(driver);
            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Raise Project Request')]");
            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'YES')]");

            SeleniumUtils.waitAndSendKeys(driver, "//input[@id='company_name']", "swageazy");
            SeleniumUtils.waitAndSendKeys(driver, "//input[@id='brand_name']", "swag");
            SeleniumUtils.waitAndSendKeys(driver, "//input[@id='client_poc']", "Aafreen khan");
            SeleniumUtils.waitAndSendKeys(driver, "//input[@id='client_poc_contact_no']", "9044717377");

            SeleniumUtils.waitAndClick(driver, "//button[@aria-label='Choose date']");
            SeleniumUtils.waitAndClick(driver, "//button[text()='26']");
            SeleniumUtils.waitAndClick(driver, "//div[contains(@class, 'css-12k1dgj')][1]");

            selectDropdownOption("//label[contains(text(),'Is the delivery time flexible')]/parent::div", "No");
            selectDropdownOption("//label[contains(text(),'Mockup Status*')]/parent::div", "Approved"); // mockuop status
            driver.findElement(By.xpath("//label[contains(text(),'DIY Redemption*')]/parent::div")).click();             // click on DIY redemption
            driver.findElement(By.xpath("(//span[normalize-space()='No'])[2]")).click();           //click on NO
           // selectDropdownOption("(//div[@role='combobox'])[5]", "No");
            //
            // Click on order type
//            driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();
//            waitForBackdropToDisappear(driver);
//            driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-2')]")).click();

// Type of shipping
            driver.findElement(By.xpath("//label[contains(text(),'Type Of Shipping')]/parent::div")).click();
            waitForBackdropToDisappear(driver);
            driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click();

// Mode of shipment
            driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();
            waitForBackdropToDisappear(driver);
            driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click();

// Payment
            driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();
            waitForBackdropToDisappear(driver);
            driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click();



            //
            Thread.sleep(2000);
//            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@id, 'products_colors')]", "na");
//            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@id, 'link')]", "na");
//            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@id, 'packing_instructions')]", "na");
//            SeleniumUtils.waitAndSendKeys(driver, "//textarea[contains(@id, 'products_additional_info')]", "NA");
            SeleniumUtils.waitAndSendKeys(driver, "//textarea[contains(@id, 'order_additional_info')]", "NA");

            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Submit')]");
            System.out.println("project created succesfully ");
        } catch (Exception e) {
            throw new RuntimeException("Failed to raise project request: " + e.getMessage(), e);
        }
    }

    public void scrAction(String orderId) {
        try {
            driver.get(BASE_URL + "/orders/add-orders?orderId=" + orderId);
            SeleniumUtils.scrollToBottom(driver);
            driver.findElement(By.xpath("//button[contains(text(),'View Products')]")).click();
            List<WebElement> actionButtons = driver.findElements(By.xpath(
                    "(//div[contains(@class,'css-10dzex5')])[7]//div[contains(@class,'css-9vd5ud')]//button"));

            for (WebElement pencilIcon : actionButtons) {
                pencilIcon.click();
                SeleniumUtils.waitAndClick(driver, "//div[contains(@class,'css-mp9f0v')]//child::span[contains(@class,'css-mslaax')]");
                 driver.findElement(By.xpath("//span[contains(text(),'Yes')]")).click(); // click on yes
                WebElement stockQtyField = SeleniumUtils.waitUntilVisible(driver, "//div[contains(text(),'Stock Quantity')]//following-sibling::div//child::div//input");
                WebElement vendorName = SeleniumUtils.waitUntilVisible(driver, "//label[contains(text(),'Vendor')]//following-sibling::div//child::input");
                WebElement timelineField = SeleniumUtils.waitUntilVisible(driver, "//label[contains(text(),'Timeline')]//following-sibling::div//child::input");

                WebElement saveButton = SeleniumUtils.waitUntilVisible(driver, "//button[text()='Confirm']");

                stockQtyField.clear();
                Thread.sleep(2000);
                stockQtyField.sendKeys("100");
                wait.until(ExpectedConditions.elementToBeClickable(vendorName));
               // vendorName.clear();
                System.out.println("done");
                vendorName.click();
                vendorName.sendKeys("Surya");
                SeleniumUtils.waitAndClick(driver, "//*[starts-with(@id,':') and contains(@id,'option-0')]");
                timelineField.clear();
                timelineField.sendKeys("3");

                saveButton.click();
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(saveButton));
            }
            SeleniumUtils.waitAndClick(driver,"//div[contains(@class,'css-j729ga')]//div//div//span[contains(text(),'Open')]");
            SeleniumUtils.waitAndClick(driver,"//span[contains(text(),'Closed')]");
            //selectDropdownOption("(//div[contains(@class,'css-j729ga')])[2]", "Closed");
            SeleniumUtils.waitAndClick(driver, "(//button[contains(text(),'Save')])[2]");

            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -300);");
            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Approve')]");
            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'YES')]");
        } catch (Exception e) {
            throw new RuntimeException("Failed to perform SCR action: " + e.getMessage(), e);
        }
    }

    public void addService(String orderId) {
        try {
            driver.get(BASE_URL + "/orders/add-orders?orderId=" + orderId);
            String[] services = {"Rail", "road", "air"};

            for (String service : services) {
                // Wait for any potential backdrop to disappear
                SeleniumUtils.waitForBackdropToDisappear(driver);

                // Click the "Add Service" button
                SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Add Service')]");

                // Wait for the search box to be fully visible
                WebElement search = SeleniumUtils.waitUntilVisible(driver, "//input[contains(@placeholder,'Search')]");
                search.clear();
                search.sendKeys(service);

                // Wait for search results to load
                SeleniumUtils.waitForBackdropToDisappear(driver);  // Ensure the backdrop is gone before interacting
                List<WebElement> checkboxes = SeleniumUtils.waitUntilVisibleAll(driver, "//div[contains(@class, 'MuiBox-root css-1w7u3vg')]");
                if (!checkboxes.isEmpty()) {
                    // Select the first available product
                    checkboxes.get(0).click();

                    // Enter location
                    WebElement locationField = SeleniumUtils.waitUntilVisible(driver, "//input[@type='name']");
                    locationField.clear();
                    locationField.sendKeys("Pune");

                    // Confirm service selection
                    SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='Confirm Services']");
                    System.out.println("Service added successfully: " + service);
                } else {
                    System.out.println("No products found for: " + service);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to add service: " + e.getMessage(), e);
        }
    }


    private void selectDropdownOption(String comboboxXpath, String optionText) {
        SeleniumUtils.waitAndClick(driver, comboboxXpath);
        SeleniumUtils.waitAndClick(driver, "//span[normalize-space()='" + optionText + "']");
    }
}



//public class EditOrder {
//        static WebDriver driver;
//         public static String id;
//        public static void main(String[] args) throws InterruptedException {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            AddOrder.setup(driver);
//            AddOrder.login(driver);
//            AddOrder.add_order (driver);
//            AddOrder.Selectuser(driver);
//            AddOrder.selectAddress(driver);
//            AddOrder.Addproducts(driver);
//
//             // Set ID after Addproducts runs
//            id = AddOrder.Orderid;
//            System.out.println("Captured Order ID: " + id); // Confirm it's captured
//
//            RaiseStockCheckRequest();
//            RaiseProjectRequest();
//            SCR_action();
//            AddService();
//        }
//    public static void RaiseStockCheckRequest() throws InterruptedException {
//       // String orderid = "1949"; // Existing order with added products
//        id = AddOrder.Orderid;
//        System.out.println(id);
//        driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId=" + id);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//button[contains(text(),'Raise Stock Check Request')]")).click();
//
//        List<WebElement> textareas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                By.xpath("//label[contains(text(), 'Write Product Notes')]/following-sibling::div[contains(@class, 'MuiOutlinedInput-root')]//textarea")));
//
//        System.out.println("Number of textareas found: " + textareas.size());
//
//        for (WebElement textarea : textareas) {
//            if (textarea.isDisplayed() && textarea.isEnabled()) {
//                textarea.clear();
//                textarea.sendKeys("NA");
//                System.out.println("Typed 'NA' into textarea");
//                driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
//            }
//        }
//    }
//
//    public static void RaiseProjectRequest() throws InterruptedException {
//
//        id = AddOrder.Orderid;
//        //String orderid = "1949"; // Existing order with added products
//        driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId=" + id);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
//        Thread.sleep(2000);
//        //click on RPR button
//        driver.findElement(By.xpath("//button[contains(text(),'Raise Project Request')]")).click();
//        Thread.sleep(2000);
//        // yes to POP-up
//        driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
//        //fill the form fields
//        driver.findElement(By.id("company_name")).sendKeys("swageazy");         // company name
//        driver.findElement(By.id("brand_name")).sendKeys("swag");                //brand name
//        driver.findElement(By.id("client_poc")).sendKeys("Aafreen khan");
//        driver.findElement(By.id("client_poc_contact_no")).sendKeys("9044717377");
//        //Delivery date
//        driver.findElement(By.xpath("//button[@aria-label='Choose date']")).click();   //   click on date icon
//        Thread.sleep(2000);
//        driver.findElement(By.xpath(" //button[text()='26']")).click();   // click on date
//        driver.findElement(By.xpath("//div[contains(@class, 'css-12k1dgj')][1]")).click();
//        //driver.findElement(By.xpath("//button[text()='OK']")).click();  // click on OK button
//        //js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");
//        driver.findElement(By.xpath("(//div[@role='combobox'])[3]")).click();     // open dropdown
//        driver.findElement(By.xpath("//span[normalize-space()='No']")).click();    // click on "NO" Option
//        driver.findElement(By.xpath("(//div[@role='combobox'])[4]")).click();      // mockup upload
//        driver.findElement(By.xpath("//span[normalize-space()='Approved']")).click();     // click on Approved
//        driver.findElement(By.xpath("(//div[@role='combobox'])[5]")).click();             // click on DIY redemption
//        driver.findElement(By.xpath("(//span[normalize-space()='No'])[2]")).click();           //click on NO
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();            //click on order type
//        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-2')]")).click();
//        Thread.sleep(2000);// select the 2nd option
//        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();  ;   //click on type of shipping
//        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click(); //click on option
//        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();   //click on Mode of shipment
//        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click();  /// click on option
//        driver.findElement(By.xpath("(//div[contains(@class, 'css-1dzbalk')])[2]")).click();   //click on payment
//        driver.findElement(By.xpath("//*[starts-with(@id,':') and contains(@id,'option-0')]")).click();  /// click on option
//        driver.findElement(By.xpath("//input[contains(@id, 'products_colors')]")).sendKeys("na");
//        driver.findElement(By.xpath("//input[contains(@id, 'link')]")).sendKeys("na");
//        driver.findElement(By.xpath("//input[contains(@id, 'packing_instructions')]")).sendKeys("na");
//        driver.findElement(By.xpath("//textarea[contains(@id, 'products_additional_info')]")).sendKeys("NA");
//        driver.findElement(By.xpath("//textarea[contains(@id, 'order_additional_info')]")).sendKeys("NA");
//        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//    }
//    public static void SCR_action() throws InterruptedException {
//        //String orderid = "1949"; // Existing order with added products
//        driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId=" + id);
//         JavascriptExecutor js = (JavascriptExecutor) driver;
//         js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight);");Thread.sleep(2000);
//         List<WebElement> ActionButtons= driver.findElements(By.xpath("(//div[contains(@class,'css-10dzex5')]//following-sibling::div[contains(@class,'css-bfcu2c')])[3]//child::div[contains(@class,' css-1v0er6g')]"));
//         for(int i = 0 ; i<ActionButtons.size();i++){{
//          WebElement pencilIcon = ActionButtons.get(i);
//          pencilIcon.click();
//          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//          // Sample locators, adjust according to actual form field
//             //
//             WebElement inStock = driver.findElement(By.xpath("//div[contains(@class,'css-mp9f0v')]//child::span[contains(@class,'css-mslaax')]"));
//             inStock.click();
//             driver.findElement(By.xpath("//span[contains(text(),'No')]")).click();
//             WebElement stockQtyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@class,'css-15v65c')])[4]")));
//             WebElement vendorName = driver.findElement(By.xpath("(//input[contains(@class,'css-15v65c')])[2]"));
//             WebElement timelineField = driver.findElement(By.xpath("(//input[contains(@class,'css-15v65c')])[3]"));
//             WebElement saveButton = driver.findElement(By.xpath("//button[text()='Confirm']"));  // or adjust based on button
//
//          // Fill the form
//          stockQtyField.clear();
//          stockQtyField.sendKeys("100");
//
//         vendorName.clear();
//         vendorName.sendKeys("Arhaan");
//
//          timelineField.clear();
//          timelineField.sendKeys("3");
//
//          // Save the form
//          saveButton.click();
//
//          // Optionally wait for the form to close before continuing
//          wait.until(ExpectedConditions.invisibilityOf(saveButton));
//      }
//      }
//         driver.findElement(By.xpath("(//div[contains(@class,'css-j729ga')])[2]")).click();    // for closing the stock check request
//         driver.findElement(By.xpath("//span[contains(text(),'Closed')]")).click();
//         driver.findElement(By.xpath("(//button[contains(text(),'Save')])[2]")).click();
//         Thread.sleep(2000);
//         js.executeScript("window.scrollBy(0, -300);");
//         driver.findElement(By.xpath("//button[contains(text(),'Approve')]")).click();  // for approving project request
//         Thread.sleep(2000);
//        driver.findElement(By.xpath("//button[contains(text(),'YES')]")).click();
//
//      }
//
//
//      public static void AddService () throws InterruptedException {
//            driver.get("https://dev-new-commander.swageazy.com/orders/add-orders?orderId="+id);
////          Thread.sleep(2000);
////          JavascriptExecutor js = (JavascriptExecutor) driver;
////          js.executeScript("window.scrollBy(0, -300);");
//
//          String[] Services = {"Rail", "road", "air"};
//
//          for (String service :Services) {
//              WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//              // Click on Add services
//              wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Service')]"))).click();
//
//              // Search for the services
//              WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                      By.xpath("//input[contains(@placeholder,'Search')]")));
//              search.clear();
//              search.sendKeys(service);
//
//              // Wait for search results to appear
//              wait.until(ExpectedConditions.visibilityOfElementLocated(
//                      By.xpath("//div[contains(@class, 'MuiBox-root css-1mp15xc')]")));
//
//              // Select the first service checkbox
//              List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(@class, 'MuiBox-root css-1w7u3vg')]"));
//              if (!checkboxes.isEmpty()) {
//                  checkboxes.get(0).click();
//                  driver.findElement(By.xpath("//input[@type ='name']")).sendKeys("Pune");
//              } else {
//                  System.out.println("No products found for: " + service);
//              }
//
//              // Click Confirm Products
//              wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm Services']"))).click();
//          }
//      }
//    }








