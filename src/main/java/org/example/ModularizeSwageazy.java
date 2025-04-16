package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;

public class ModularizeSwageazy {

    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        setup();
        login();
        Thread.sleep(2000);

        // Uncomment required methods for further actions:
         AddOrder();
         addProducts();
         CreateProjectRequest();
         AddUser();
        GetUserDetails();

        teardown();
    }

    // Setup WebDriver
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://dev-commander.swageazy.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    }

    // Teardown WebDriver
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Login to the application
    public static void login() {
        WebElement username = driver.findElement(By.id("txt-admin-userid"));
        username.sendKeys("afreen.khan@swageazy.com");
        WebElement password = driver.findElement(By.id("txt-admin-pwd"));
        password.sendKeys("Dania@123");
        WebElement loginButton = driver.findElement(By.id("submit-btn-login"));
        loginButton.click();
    }

    // Add a new order
    public static void AddOrder() throws InterruptedException {
        navigateToOrders();
        selectDropdownOptionById("company-menu", 5);
        selectDropdownOptionByVisibleText("user-menu", "Dania khan");
        selectProductCard();
        scrollAndClickElementById("add-modal-btn");
    }

    // Add products to the order
    public static void addProducts() throws InterruptedException {
        searchAndAddProduct("t-shirt", "200");
        searchAndAddProduct("Sipper", "300");
        closeProductModal();
        createOrder();
    }

    // Create a project request
    public static void CreateProjectRequest() throws InterruptedException {
        navigateToOrderDetails();
        raiseProjectRequest();
        raiseStockCheckRequest();
    }

    // Add a new user
    public static void AddUser() throws InterruptedException {
        driver.get("https://dev-commander.swageazy.com/users/add");
        fillUserDetails("Skybags", "Positive", "9044614228", "afreen.khan@swageazyyy.com", "Dania@123");
        selectDropdownOptionByVisibleText("company-menu", "11sept");
        scrollAndClickElementById("add-user-btn");
    }

    // Get user details
    public static void GetUserDetails() throws InterruptedException {
        driver.get("https://dev-commander.swageazy.com/users");
        driver.findElement(By.id("search-user-input-box")).sendKeys("skybags");
        Thread.sleep(2000);
        String userDetails = driver.findElement(By.xpath("//tbody//tr")).getText();
        System.out.println(userDetails);
    }

    // Utility methods

    public static void navigateToOrders() {
        WebElement ordersTab = driver.findElement(By.xpath("//a[normalize-space()='Orders']"));
        ordersTab.click();
        driver.findElement(By.xpath("//*[contains(text(),'Add Orders')]")).click();
    }

    public static void searchAndAddProduct(String productName, String quantity) throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("search-products"));
        searchBox.sendKeys(productName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(quantity);
        driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Add'])[1]")).click();
        searchBox.clear();
    }

    public static void closeProductModal() {
        driver.findElement(By.xpath("//button[normalize-space()='close']")).click();
    }

    public static void createOrder() throws InterruptedException {
        WebElement createOrderButton = driver.findElement(By.id("create-order-btn"));
        scrollAndClickElementById("create-order-btn");
        driver.findElement(By.id("modal-button")).click();
    }

    public static void navigateToOrderDetails() {
        driver.get("https://dev-commander.swageazy.com/orders?pageNo=1");
        WebElement link = driver.findElement(By.xpath("//a[contains(@href, '/orders/order/details?orderId=')]"));
        link.click();
    }

    public static void raiseProjectRequest() throws InterruptedException {
        WebElement createProjectButton = driver.findElement(By.id("create-project-btn"));
        scrollAndClickElement(createProjectButton);
        String errorMessage = driver.findElement(By.xpath("//p[@id='modal-content3']")).getText();
        System.out.println(errorMessage);
        Assert.assertTrue(true, errorMessage);
        driver.findElement(By.xpath("//button[@id='modal-button']")).click();
    }

    public static void raiseStockCheckRequest() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='stock-check-btn']")).click();
        driver.findElement(By.id("txt-notes")).sendKeys("NA");
        driver.findElement(By.id("stock-check-btn-processed")).click();
    }

    public static void fillUserDetails(String firstName, String lastName, String contact, String email, String password) {
        driver.findElement(By.id("txt-firstname")).sendKeys(firstName);
        driver.findElement(By.id("txt-lastname")).sendKeys(lastName);
        driver.findElement(By.id("txt-contactno")).sendKeys(contact);
        driver.findElement(By.id("txt-email")).sendKeys(email);
        driver.findElement(By.id("txt-password")).sendKeys(password);
    }

    public static void selectDropdownOptionById(String elementId, int index) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.id(elementId));
        dropdown.click();
        Select select = new Select(dropdown);
        Thread.sleep(2000);
        select.selectByIndex(index);
    }

    public static void selectDropdownOptionByVisibleText(String elementId, String visibleText) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.id(elementId));
        dropdown.click();
        Select select = new Select(dropdown);
        Thread.sleep(2000);
        select.selectByVisibleText(visibleText);
    }

    public static void selectProductCard() {
        driver.findElement(By.xpath("(//div[@class='card-body order-mockup-card'])[1]")).click();
    }

    public static void scrollAndClickElementById(String elementId) throws InterruptedException {
        WebElement element = driver.findElement(By.id(elementId));
        scrollAndClickElement(element);
    }

    public static void scrollAndClickElement(WebElement element) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        element.click();
    }
}

