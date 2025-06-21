package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AddOrder extends BasePage {
    public static String Orderid;
    private static final Properties props;

    static {
        props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            props.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
        }
    }

    private static final String EMAIL = props.getProperty("email");
    private static final String PASSWORD = props.getProperty("password");

    public AddOrder(WebDriver driver) {

        super(driver);
    }

    public void login() {
        try {
            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@class,'css-1pk1fka')]", EMAIL);
            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@class,'css-qwdxx6')]", PASSWORD);
            SeleniumUtils.waitAndClick(driver, "//button[contains(@class,'css-1m5l81m')]");
            Thread.sleep(2000);
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='dashboard']")));

            System.out.println("Login successful and dashboard loaded");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    public void addOrder() {
        try {
            driver.navigate().refresh();
            SeleniumUtils.waitAndClick(driver, "//span[text()='Orders']/parent::div");
            SeleniumUtils.waitAndClick(driver, "//span[text()='List']");
            driver.navigate().refresh();
            SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='Add Order']");

            // Type input and wait
            WebElement inputField = SeleniumUtils.waitUntilVisible(driver, "//input[@type='text' and contains(@class, 'MuiInputBase-input')]");
            inputField.sendKeys("RRest Assured");

            // Wait until dropdown is visible
            List<WebElement> options = SeleniumUtils.waitUntilVisibleAll(driver, "//li[contains(@id,'-option-')]");

            // Optional: add a short pause to allow UI to render completely
            Thread.sleep(1000);

            if (!options.isEmpty()) {
                inputField.sendKeys(Keys.ARROW_DOWN);
                inputField.sendKeys(Keys.ENTER);
            } else {
                throw new RuntimeException("No autocomplete options appeared.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public void selectUser() {
        try {
            WebElement userInput = SeleniumUtils.waitUntilVisible(driver, "//label[contains(text(),'Select user')]//following-sibling::div//child::input");
            userInput.click();
            userInput.sendKeys("Selenium");

            // Wait for dropdown options to appear
            List<WebElement> options = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]/li")));

            for (WebElement option : options) {
                if (option.getText().contains("Selenium")) {
                    option.click();
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to select user: " + e.getMessage(), e);
        }
    }


    public  void selectAddress() {
        try {
            SeleniumUtils.waitAndClick(driver, "(//button[normalize-space()='Select'])[1]");
            SeleniumUtils.waitAndClick(driver, "(//div[contains(@class,'css-j3317l')])[2]");
            SeleniumUtils.waitAndClick(driver, "(//button[normalize-space()='Done'])[1]");
            SeleniumUtils.waitAndClick(driver, "(//button[contains(@class,'css-2pn8vf')])[2]");
            SeleniumUtils.waitAndClick(driver, "(//div[contains(@class,'css-j3317l')])[1]");
            SeleniumUtils.waitAndClick(driver, "(//button[normalize-space()='Done'])[1]");
        } catch (Exception e) {
            throw new RuntimeException("Failed to select address: " + e.getMessage(), e);
        }
    }

    public  void addProducts() {
        try {
            String[] productNames = {"adidas", "Mail", "insert"};
            for (String product : productNames) {
                SeleniumUtils.waitAndClick(driver, "(//button[normalize-space()='Add Products'])[1]");
                WebElement search = SeleniumUtils.waitUntilVisible(driver, "//input[@placeholder='Search...']");
                search.clear();
                search.sendKeys(product);

                SeleniumUtils.waitUntilVisible(driver, "//div[contains(@class, 'MuiBox-root css-1mp15xc')]");

                List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(@class, 'MuiBox-root css-1w7u3vg')]"));
                if (!checkboxes.isEmpty()) {
                    checkboxes.get(0).click();
                } else {
                    System.out.println("No product found for: " + product);
                }

                SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='Confirm Products']");
            }

            SeleniumUtils.waitAndClick(driver, "//button[contains(text(),'Update All Quantities')]");
            WebElement qtyInput = SeleniumUtils.waitUntilVisible(driver, "(//input[contains(@class ,'css-1pk1fka')])[3]");
            qtyInput.click();
            qtyInput.sendKeys("20");

            SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='Submit']");
            SeleniumUtils.waitAndClick(driver, "//button[normalize-space()='Create Order']");

            WebElement confirmation = SeleniumUtils.waitUntilVisible(driver, "//div[contains(text(),'Order Details -')]");
            String orderIdText = confirmation.getText();
            if (orderIdText.matches("Order Details - \\d+")) {
                Orderid = orderIdText.split("-")[1].trim();
                System.out.println("Order ID: " + Orderid);
            } else {
                throw new RuntimeException("Invalid order ID format: " + orderIdText);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to add products: " + e.getMessage(), e);
        }

    }
}



//public class AddOrder {
//    static WebDriver driver;
//    public static String Orderid;
//
//    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        setup(driver);
//        login(driver);
//        add_order(driver);
//        Selectuser(driver);
//        selectAddress(driver);
//        Addproducts(driver);
//        teardown(driver);
//    }
//
//    public static void setup(WebDriver driver) {
//        driver.get("https://dev-new-commander.swageazy.com/");
//        driver.manage().window().maximize();
//    }
//
//    public static void teardown(WebDriver driver) {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public static void login(WebDriver driver) throws InterruptedException {
//        Thread.sleep(2000);
//        WebElement username = driver.findElement(By.xpath("//input[contains(@class,'css-1pk1fka')]"));
//        username.sendKeys("afreen.khan@swageazy.com");
//        WebElement password = driver.findElement(By.xpath("//input[contains(@class,'css-qwdxx6')] "));
//        password.sendKeys("Sania@123");
//        WebElement next = driver.findElement(By.xpath("//button[contains(@class,'css-1m5l81m')]"));
//        next.click();
//        Thread.sleep(2000);
//
//    }
//
//    public static void add_order(WebDriver driver) throws InterruptedException {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        driver.navigate().refresh();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Thread.sleep(2000);
//        //Click on orders in nav bar
//        driver.findElement(By.xpath("//span[contains(text(),'Orders')]//parent::div")).click();
//        //click on list in nav bar
//        driver.findElement(By.xpath("//span[contains(text(),'List')]")).click();
//        //neeed to refresh bcz permission has issues
//        driver.navigate().refresh();
//        Thread.sleep(2000);
//        //click on add order button
//        driver.findElement(By.xpath("//button[normalize-space()='Add Order']")).click();
//
//
//        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//input[@type='text' and contains(@class, 'MuiInputBase-input')]")
//        ));
//        inputField.sendKeys("aaafreen");
//        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                By.xpath("//ul[contains(@class, 'MuiAutocomplete-listbox')]//li")
//        ));
//        for (WebElement option : dropdownOptions) {
//            String optionText = option.getText();
//            System.out.println("Option:" + optionText);
//            if (optionText.equals("Aafreen zee")) {
//                option.click();
//                break;
//            }
//        }
//    }
//
//    public static void Selectuser(WebDriver driver) throws InterruptedException {
//        WebElement username = driver.findElement(By.xpath("//input[@id=':rn:']"));
//        Thread.sleep(2000);
//        username.click();
////        username.sendKeys("grace");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        //driver.findElement(By.xpath("//input[contains(@class,'css-gzuv0n')]")).click();
//
////        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
////                By.xpath("//input[contains(@class,'css-gzuv0n')]")
////        ));
//        //inputField.sendKeys("grace");
//        //Thread.sleep(3000);
//        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]//li")
//        ));
//        for (WebElement option : dropdownOptions) {
//            String optionText = option.getText();
//            System.out.println("Option:" + optionText);
//            if (optionText.equals("grace")) {
//                option.click();
//                break;
//            }
//
//        }
//    }
//
//    public static void selectAddress(WebDriver driver) {
//        driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Select'])[1]")).click();
//        driver.findElement(By.xpath("(//div[contains(@class,'css-j3317l')])[2]")).click();
//        driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
//        driver.findElement(By.xpath("(//button[contains(@class,'css-2pn8vf')])[2]")).click();
//        driver.findElement(By.xpath("(//div[contains(@class,'css-j3317l')])[1]")).click();
//        driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
//    }
//
//    public static void Addproducts(WebDriver driver) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // List of products to search and add
//        String[] productNames = {"adidas", "Mail", "insert"};
//
//        for (String product : productNames) {
//            // Click on Add Products
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add Products'])[1]"))).click();
//
//            // Search for the product
//            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//input[@placeholder ='Search...']")));
//            search.clear();
//            search.sendKeys(product);
//
//            // Wait for search results to appear
//            wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//div[contains(@class, 'MuiBox-root css-1mp15xc')]")));
//
//            // Select the first product checkbox
//            List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(@class, 'MuiBox-root css-1w7u3vg')]"));
//            if (!checkboxes.isEmpty()) {
//                checkboxes.get(0).click();
//            } else {
//                System.out.println("No products found for: " + product);
//            }
//
//            // Click Confirm Products
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm Products']"))).click();
//        }
//
//        // Update All Quantities
//        driver.findElement(By.xpath("//button[contains(text(),'Update All Quantities')]")).click();
//        Thread.sleep(2000);
//        WebElement editQty = driver.findElement(By.xpath("(//input[contains(@class ,'css-1pk1fka')])[3]"));
//        editQty.click();
//        editQty.sendKeys("20");
//
//        // Submit and Create Order
//        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
//        driver.findElement(By.xpath("//button[normalize-space()='Create Order']")).click();
//        Thread.sleep(2000);
//
//
//            String fullText = driver.findElement(By.xpath("//div[contains(text(),'Order Details -')]")).getText();
//            Orderid = fullText.split("-")[1].trim();  // This gives you "1953"
//            System.out.println("Order ID: " + Orderid);
//
//
//
//
//    }
//}


