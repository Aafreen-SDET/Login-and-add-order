package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AddOrder;
import org.example.pages.AddProduct;
import org.example.pages.Employee;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployessTest {
    private WebDriver driver;
    private Employee emp;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        emp = new Employee(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://dev-new-commander.swageazy.com");
        addOrder.login();  // Assuming login is required before adding a product
    }
@Test
    public void testEmployee() throws InterruptedException {
        emp.AddEmployee();
        // SeleniumUtils.waitForBackdropToDisappear(driver);
//        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
//        Assert.assertEquals(confirmationText, "Okay", "Product creation confirmation text mismatch");
        System.out.println("employee added successfully added");
       // emp.verifTheAdd();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



