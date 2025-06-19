package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Base.BasePage;
import org.example.pages.AddCustomer;
import org.example.pages.AddOrder;
import org.example.pages.AddUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddCustomerTest   {

        private WebDriver driver;
        private AddCustomer addcust;
        private AddOrder addOrder;

        @BeforeClass
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            addcust= new AddCustomer(driver);
            addOrder = new AddOrder(driver);
            driver.get("https://dev-new-commander.swageazy.com");
            addOrder.login();

        }
        @Test
        public void testUser() throws InterruptedException {
            addcust.Customer();
//        SeleniumUtils.waitForBackdropToDisappear(driver);
//        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
//        Assert.assertEquals(confirmationText, "Okay", "User added  confirmation text mismatch");
            System.out.println("customer Added succesfully ");
        }

    @AfterTest
    public void Quit() {
        if (driver != null) {
            driver.quit();
        }

    }
    }

