package org.example.tests;

import org.example.pages.AddProduct;
import org.example.pages.AddOrder;
import org.example.utils.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAddProduct {
    private WebDriver driver;
    private AddProduct addProduct;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        addProduct = new AddProduct(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://stage-new-commander.swageazy.com");
        addOrder.login();  // Assuming login is required before adding a product
    }

    @Test
    public void testAddProduct() throws InterruptedException {
        addProduct.Addproductnew();
        //addProduct.addProducts();
        SeleniumUtils.waitForBackdropToDisappear(driver);
        String confirmationText = SeleniumUtils.waitUntilVisible(driver, "//button[contains(text(),'Okay')]").getText();
        Assert.assertEquals(confirmationText, "Okay", "Product creation confirmation text mismatch");
        System.out.println("Product successfully added");

    }

//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}

