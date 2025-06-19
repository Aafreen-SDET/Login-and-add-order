package org.example.tests;

import org.example.pages.AddOrder;
import org.example.pages.AddVendor;
import org.example.utils.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddVendorTest {
    private WebDriver driver;
    private AddOrder addOrder;
    private AddVendor addVendor;

    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the base URL
        driver.get("https://dev-new-commander.swageazy.com");

        // Login before accessing the vendor page
        addOrder = new AddOrder(driver);
        addOrder.login();  // Assuming this handles login correctly

        // Initialize AddVendor after successful login
        addVendor = new AddVendor(driver);
    }

    @Test(priority = 1)
    public void testAddVendor() throws InterruptedException {
        try {
            // Add a vendor
            addVendor.vendor();

            // Wait for the backdrop to disappear if necessary
            SeleniumUtils.waitForBackdropToDisappear(driver);

            // Validate the vendor creation (update this as per your actual confirmation message)
            String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
            Assert.assertEquals(confirmationText, "Okay", "Vendor creation confirmation text mismatch");
            System.out.println("Vendor successfully added");

        } catch (Exception e) {
            Assert.fail("Failed to add vendor: " + e.getMessage(), e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
