package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AddOrder;
import org.example.pages.Address;
import org.example.pages.ProjectManagement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectMangementTest {
    private WebDriver driver;
    private ProjectManagement PM;
    private AddOrder addOrder;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        PM = new ProjectManagement(driver);
        addOrder = new AddOrder(driver);
        driver.get("https://dev-new-commander.swageazy.com");
        addOrder.login();  // Assuming login is required before adding a product
    }
    @Test
    public void testPM() throws InterruptedException {
        PM.OpenProjectMgt();
        PM.makeCustomisationOFForON();
        PM.RaisePR();

    }

}
