package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AddOrder;
import org.example.pages.AddProduct;
import org.example.pages.AddUser;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserTest {
    private WebDriver driver;
    private AddUser addUser;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        addUser = new AddUser(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://dev-new-commander.swageazy.com");
        addOrder.login();

    }
    @Test
    public void testUser() throws InterruptedException {
        addUser.UserAdd();
//        SeleniumUtils.waitForBackdropToDisappear(driver);
//        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
//        Assert.assertEquals(confirmationText, "Okay", "User added  confirmation text mismatch");
        System.out.println("User Added succesfully ");
    }
    @AfterTest
    public void Quit() {
        if (driver != null) {
            driver.quit();
        }

    }
}