package org.example.tests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.example.pages.Login;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws InvalidFormatException {
        return Login.getLoginDataFromExcel();  // Assuming ExcelReader method is inside Login class
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String type) {
        WebDriver driver = new ChromeDriver();
        // Initialize WebDriver (use WebDriver setup from your project)
        driver.get("https://dev-new-commander.swageazy.com");

        try {
            Login loginPage = new Login(driver);
            loginPage.login(username, password);  // Pass dynamic credentials from Excel

            boolean loginSuccessful = "Positive".equals(type);
            if (loginSuccessful) {
                Assert.assertTrue(true, "Login should be successful for: " + username);
            } else {
                String error = driver.findElement(By.xpath("//div[contains(@class,'css-zhjptw')]")).getText();
                if (error.contains("An error occurred during login, please check your email or password.")) {
                    System.out.println("fail");

                    Assert.assertFalse(false, "Login should fail for: " + username);
                }}
            } finally{
                driver.quit();  // Ensure browser closes after each test
            }


        }
    }
