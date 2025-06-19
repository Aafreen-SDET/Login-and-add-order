package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Base.BasePage;
import org.example.pages.AddOrder;
import org.example.pages.Address;
import org.example.pages.TaskTracker;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAddress {
    private WebDriver driver;
    private Address address;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        address = new Address(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://dev-new-commander.swageazy.com");
        addOrder.login();  // Assuming login is required before adding a product
    }

    @Test
    public void TestAddress() throws InterruptedException {
        //address.FindAddress();
        address.addAddress();
        SeleniumUtils.waitForBackdropToDisappear(driver);
        address.VerifyResult();
//        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
//        Assert.assertEquals(confirmationText, "Okay", "t");
        System.out.println("Data is matched succesfully");

    }

    @AfterTest
    public void Quit(){
        if (driver != null) {
            driver.quit();
        }
    }




}

