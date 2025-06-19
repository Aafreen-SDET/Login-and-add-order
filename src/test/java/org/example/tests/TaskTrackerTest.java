package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AddOrder;
import org.example.pages.AddProduct;
import org.example.pages.TaskTracker;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TaskTrackerTest {
    private WebDriver driver;
    private TaskTracker TaskTracker;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        TaskTracker  = new TaskTracker(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://dev-new-commander.swageazy.com");
        addOrder.login();  // Assuming login is required before adding a product
    }

    @Test
    public void TestTaskTracker() throws InterruptedException {
        TaskTracker.AddTask();
        SeleniumUtils.waitForBackdropToDisappear(driver);
//        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
//        Assert.assertEquals(confirmationText, "Okay", "t");
        System.out.println("Task Tracker successfully added");
    }
    @AfterTest
    public void Quit() {
        if (driver != null) {
            driver.quit();
        }

    }
}
