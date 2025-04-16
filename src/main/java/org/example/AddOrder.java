package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


import static org.example.Main.driver;

public class AddOrder {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        setup(driver);
        login(driver);
        add_order(driver);
        Selectuser();
        selectAddress();
        Addproducts();
        teardown();
    }
    public static void setup(WebDriver driver) {
        driver.get("https://dev-new-commander.swageazy.com/");
        driver.manage().window().maximize();
    }

    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void login(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.xpath("//input[contains(@class,'css-1pk1fka')]"));
        username.sendKeys("afreen.khan@swageazy.com");
        WebElement password = driver.findElement(By.xpath("//input[contains(@class,'css-qwdxx6')] "));
        password.sendKeys("Sania@123");
        WebElement next = driver.findElement(By.xpath("//button[contains(@class,'css-1m5l81m')]"));
        next.click();
        Thread.sleep(2000);

    }

    public static void add_order(WebDriver driver) throws InterruptedException {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Click on orders in nav bar
        driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
        //click on list in nav bar
        driver.findElement(By.xpath("//span[contains(text(),'List')]")).click();
        //neeed to refresh bcz permission has issues
        driver.navigate().refresh();
        //click on add order button
        driver.findElement(By.xpath("//button[normalize-space()='Add Order']")).click();


        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='text' and contains(@class, 'MuiInputBase-input')]")
        ));
        inputField.sendKeys("aaafreen");
        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[contains(@class, 'MuiAutocomplete-listbox')]//li")
        ));
        for (WebElement option : dropdownOptions) {
            String optionText = option.getText();
            System.out.println("Option:" + optionText);
            if (optionText.equals("Aafreen zee")) {
                option.click();
                break;
            }
        }
    }

    public static void Selectuser() throws InterruptedException {
        WebElement username = driver.findElement(By.xpath("//input[@id=':rn:']"));
        Thread.sleep(2000);
        username.click();
//        username.sendKeys("grace");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       //driver.findElement(By.xpath("//input[contains(@class,'css-gzuv0n')]")).click();

//        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//input[contains(@class,'css-gzuv0n')]")
//        ));
        //inputField.sendKeys("grace");
        //Thread.sleep(3000);
        List<WebElement> dropdownOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//ul[contains(@class,'MuiAutocomplete-listbox')]//li")
        ));
        for (WebElement option : dropdownOptions) {
            String optionText = option.getText();
            System.out.println("Option:" + optionText);
            if (optionText.equals("grace")) {
                option.click();
                break;
            }

        }
    }
     public static void selectAddress(){
        driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Select'])[1]")).click();
         driver.findElement(By.xpath("(//div[contains(@class,'css-j3317l')])[2]")).click();
         driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
         driver.findElement(By.xpath("(//button[contains(@class,'css-2pn8vf')])[2]")).click();
         driver.findElement(By.xpath("(//div[contains(@class,'css-j3317l')])[1]")).click();
         driver.findElement(By.xpath("(//button[normalize-space()='Done'])[1]")).click();
    }
    public static void Addproducts() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // List of products to search and add
        String[] productNames = {"adidas", "Mail", "insert"};

        for (String product : productNames) {
            // Click on Add Products
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add Products'])[1]"))).click();

            // Search for the product
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder ='Search...']")));
            search.clear();
            search.sendKeys(product);

            // Wait for search results to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'MuiBox-root css-1mp15xc')]")));

            // Select the first product checkbox
            List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(@class, 'MuiBox-root css-1w7u3vg')]"));
            if (!checkboxes.isEmpty()) {
                checkboxes.get(0).click();
            } else {
                System.out.println("No products found for: " + product);
            }

            // Click Confirm Products
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm Products']"))).click();
        }

        // Update All Quantities
        driver.findElement(By.xpath("//button[contains(text(),'Update All Quantities')]")).click();
        Thread.sleep(2000);
        WebElement editQty = driver.findElement(By.xpath("(//input[contains(@class ,'css-1pk1fka')])[3]"));
        Thread.sleep(2000);
        editQty.click();
        editQty.sendKeys("20");

        // Submit and Create Order
        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Create Order']")).click();

        // Scroll to bottom and click Raise Stock Check Request
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        driver.findElement(By.xpath("//button[contains(text(),'Raise Stock Check Request')]")).click();
//        List<WebElement> textareas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                By.xpath("//label[contains(text(), 'Write Product Notes')]/following-sibling::div[contains(@class, 'MuiOutlinedInput-root')]//textarea[1]")));
//
//        System.out.println("Number of textareas found: " + textareas.size());
//        for (WebElement textarea : textareas) {
//            if (textarea.isDisplayed() && textarea.isEnabled()) {
//                textarea.clear();
//                textarea.sendKeys("NA");
//                System.out.println("Typed 'NA' into textarea");
//            } else {
//                System.out.println("Textarea is not interactable");
//            }
//            driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
//
//        }
    }


}


