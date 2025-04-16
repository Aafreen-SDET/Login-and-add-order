package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Iterator;
//import java.util.List;
import java.util.Set;
import java.util.*;
import org.openqa.selenium.interactions.Actions;
//import static java.awt.SystemColor.controlText;
//import static java.awt.SystemColor.window;
import static org.example.Main.click;
import static org.example.Main.driver;
import org.openqa.selenium.Keys;

//import javax.swing.*;

public class DevSwageazy {
    public static void main(String[] args) throws InterruptedException {
        setup();
        login();
        Thread.sleep(2000);
        //logout();
        //AddOrder();
        //addProducts();
        Thread.sleep(2000);
        //CreateProjectRequest();
        //Adduser();
        // GetUserDetails();
        Shipment();
        UniversalShipment();
        searchByName();
        //setdownload();
        //DownloadFileExample();
        //driver.close();
        filter();
        //AutoDownloadCSV();
    }

    public static WebDriver setup() throws InterruptedException {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://dev-commander.swageazy.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }
        return driver;
    }

    // Perform login action
    public static void login() {
        WebElement username = driver.findElement(By.id("txt-admin-userid"));
        username.sendKeys("afreen.khan@swageazy.com");
        WebElement password = driver.findElement(By.id("txt-admin-pwd"));
        password.sendKeys("Dania@123");
        WebElement next = driver.findElement(By.id("submit-btn-login"));
        //Thread.sleep(2000);
        next.click();
    }

    public static void AddOrder() throws InterruptedException {
        WebElement addOrderButton = driver.findElement(By.xpath("//a[normalize-space()='Orders']"));
        addOrderButton.click();
        driver.findElement(By.xpath(" //*[contains(text(),'Add Orders')]")).click();
        WebElement dropdown = driver.findElement(By.id("company-menu"));
        dropdown.click();
        Thread.sleep(2000);
        Select select = new Select(dropdown);
        System.out.println("hey");
        select.selectByIndex(5);
        WebElement userDropdown = driver.findElement(By.id("user-menu"));
        userDropdown.click();
        Select select1 = new Select(userDropdown);
        Thread.sleep(2000);
        select1.selectByVisibleText("Dania khan");
        System.out.println("sky");
        driver.findElement(By.xpath("(//div[@class='card-body order-mockup-card'])[1]")).click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("arguments[0].scrollBy(1000,3000);",driver.findElement(By.id("add-modal-btn")));
        WebElement element = driver.findElement(By.id("add-modal-btn"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
        System.out.println("bye");
    }

    public static void addProducts() throws InterruptedException {
        WebElement search = driver.findElement(By.id("search-products"));
        search.sendKeys("t-shirt");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("200");
        driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Add'])[1]")).click();
        search.clear();
        search.sendKeys("Sipper");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("300");
        driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Add'])[1]")).click();
        driver.findElement(By.xpath("//button[normalize-space()='close']")).click();
        Thread.sleep(2000);
        WebElement CreateOrderButton = driver.findElement(By.id("create-order-btn"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", CreateOrderButton);
        Thread.sleep(2000);
        CreateOrderButton.click();
        Thread.sleep(2000);
        driver.findElement(By.id("modal-button")).click();


    }

    public static void CreateProjectRequest() throws InterruptedException {
        driver.get("https://dev-commander.swageazy.com/orders?pageNo=1");
        WebElement link = driver.findElement(By.xpath("//a[contains(@href, '/orders/order/details?orderId=')]"));
        link.click();
        WebElement CreateProject = driver.findElement(By.id("create-project-btn"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", CreateProject);
        Thread.sleep(2000);
        CreateProject.click();
        Thread.sleep(2000);
        String ErrorMessage = driver.findElement(By.xpath("//p[@id='modal-content3']")).getText();
        Thread.sleep(2000);
        //negative testcase if user tries to Raise project request without raising the Stock check request
        System.out.println(ErrorMessage);
        Assert.assertTrue(true, ErrorMessage);
        //For Raising the Stock Check request
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='modal-button']")).click();
        driver.findElement(By.xpath("//button[@id='stock-check-btn']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("txt-notes")).sendKeys("NA");
        driver.findElement(By.id("stock-check-btn-processed")).click();

    }

    public static void Adduser() throws InterruptedException {
        driver.get("https://dev-commander.swageazy.com/users/add");
        //driver.findElement(By.linkText("Add Users")).click();
        driver.findElement(By.id("txt-firstname")).sendKeys("Skybags");
        driver.findElement(By.id("txt-lastname")).sendKeys("Positive");
        driver.findElement(By.id("txt-contactno")).sendKeys("9044614228");
        driver.findElement(By.id("txt-email")).sendKeys("afreen.khan@swageazyyy.com");
        driver.findElement(By.id("txt-password")).sendKeys("Dania@123");

        WebElement dropdown = driver.findElement(By.id("company-menu"));
        dropdown.click();
        Select sel = new Select(dropdown);
        sel.selectByVisibleText("11sept");
        WebElement addUser = driver.findElement(By.id("add-user-btn"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addUser);
        Thread.sleep(2000);
        addUser.click();
        //Catch Error if user is already existed
//        String ErrorMessage = driver.findElement(By.xpath("//div[@id='custom-modal-body']//child::p")).getText();
//        Thread.sleep(2000);
//        System.out.println(ErrorMessage+"hey");
//        Assert.assertTrue(true,ErrorMessage);
        //for fresh user with diffrent email
//        driver.findElement(By.id("txt-email")).sendKeys("afreen.khan@swageazyyy.com");
//        driver.findElement(By.id("txt-password")).sendKeys("Dania@123");

    }

    public static void GetUserDetails() throws InterruptedException {
        driver.get("https://dev-commander.swageazy.com/users");
        driver.findElement(By.id("search-user-input-box")).sendKeys("skybags");
        Thread.sleep(2000);
        String str = driver.findElement(By.xpath("//tbody//tr")).getText();
        System.out.println(str);
    }

    public static void Shipment() {
        //driver.get("https://dev-commander.swageazy.com/dashboard");
        driver.findElement(By.xpath("(//a[normalize-space()='Shipments'])[1]")).click();
        driver.findElement(By.xpath("//a[@id='universalShipmentsLink']")).click();
        driver.getWindowHandles();
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();


        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                /*"need to Login which is not requirement*/
                //driver.findElement(By.id("email")).sendKeys("afreen.khan@swageazy.com");
                //driver.findElement(By.id("password")).sendKeys("Dania@123");
                //driver.findElement(By.tagName("button")).click();
                //driver.close();
            }
        }

        //driver.switchTo().window(parent);
    }

    public static void Commander() {
        String orderID = "1508";
        driver.findElement(By.xpath("(//a[normalize-space()='Commander'])[1]")).click();
        driver.findElement(By.xpath("search-project")).sendKeys(orderID);
        driver.findElement(By.xpath("(//input[@id='search-project'])[1]")).click();


    }

    public static void UniversalShipment() {
        driver.findElement(By.xpath("//span[contains(text(),'Inventory')]")).click();
        driver.findElement(By.xpath("(//*[name()='svg'][@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-10vyf6v'])[1]")).click();
    }

    public static void logout() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollBy(1000, 0);", driver.findElement(By.xpath("//button[@id='logout-admin']")));


        // Alternatively, scroll back to the left
        //js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);
        WebElement logout = driver.findElement(By.xpath("//button[@id='logout-admin']"));
        logout.click();
    }

    public static void searchByName() throws InterruptedException {
         WebElement searchbar= driver.findElement(By.id("standard-search"));
         searchbar.sendKeys("jasleen");
        WebElement search= driver.findElement(By.xpath("//button[normalize-space()='Search']"));
        search.click();
        // Find all table row elements that match the XPath
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='MuiTableRow-root css-1egcazv']"));
        // Create a list to hold the text data
        List<String> data = new ArrayList<>();
        // Loop through each row and get the text
        for (WebElement row : rows) {
            data.add(row.getText()); // Add the text of each row to the list
        }
        // Print the list
        System.out.println(data);
       // driver.findElement(By.xpath("//button[text()='Clear']")).click(); //clicking on clear button
        Thread.sleep(2000);
        searchbar.click();
        Actions actions = new Actions(driver);
        actions.click(searchbar)             // Focus on the field
                .keyDown(Keys.CONTROL)       // Hold CTRL (CMD for Mac)
                .sendKeys("a")               // Select all text
                .keyUp(Keys.CONTROL)         // Release CTRL
                .sendKeys(Keys.BACK_SPACE)   // Press Backspace to delete
                .perform();

        searchbar.clear();
        //Thread.sleep(5000);
        //driver.navigate().refresh();


        //search.click();//for clearing the  search bar
        // driver.findElement(By.xpath("(//button[normalize-space()='Download CSV'])[1]")).click();
    }
    public static void filter() throws InterruptedException {
        /*
        shipmemt = 'manual'
         shipment status = 'new'
         delivery partner = 'Swageazy'
*/
        Actions ac = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shipmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'css-1n4e4j6')])[1]")));
        shipmentDropdown.click();
        driver.findElement(By.xpath("(//span[contains(@class, 'css-1lbx1w')])[1]")).click();
        Thread.sleep(2000);
        WebElement body = driver.findElement(By.tagName("body"));
        body.click(); // Clicks anywhere outside the dropdown

        //driver.findElement(By.xpath("(//div[contains(@class,'css-1n4e4j6')])[1]")).click();

        WebElement shipmentStatusDropdown = driver.findElement(By.xpath("(//div[contains(@class,'css-117jitv')])[2]"));
        shipmentStatusDropdown.click();
        ac.moveToElement(shipmentStatusDropdown);
        driver.findElement(By.xpath("(//span[contains(@class, 'css-1lbx1wf')])[2]")).click();
        body.click();
        //Delivery Partner
        WebElement DeliveryPartnerDropdown =driver.findElement(By.xpath("(//div[contains(@class,'css-117jitv')])[3]"));
        DeliveryPartnerDropdown.click();
        driver.findElement(By.xpath("//span[contains(@class,'css-1lbx1wf')]")).click();
        //driver.switchTo().defaultContent();
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", DeliveryPartnerDropdown);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.pointerEvents='none';");


        //driver.findElement(By.tagName("body")).click();
        Thread.sleep(4000);
        //driver.findElement(By.xpath("(//span[contains(@class, 'css-1lbx1wf')])[3]")).click();
        WebElement apply = driver.findElement(By.xpath("//button[normalize-space()='Apply']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", apply);
           apply.click();
        

    }

    public static  void  AutoDownloadCSV (){
        {
            String downloadFilePath = "C:\\Users\\aafreen\\Documents";
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadFilePath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("profile.default_content_settings.popups", 0);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://yourwebsite.com");
            driver.findElement(By.xpath("//button[text()='DOWNLOAD CSV']")).click();  // Adjust locator as per the webpage

        }
    }



}
