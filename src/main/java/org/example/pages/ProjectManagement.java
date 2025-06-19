package org.example.pages;

import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ProjectManagement extends BasePage {
    private static final Properties props;
    private static final String BASE_URL;
    String ProjectId = "1910";

    static {
        props = new Properties();
        try (InputStream input = EditOrder.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to  load config.properties: " + e.getMessage(), e);
        }
        BASE_URL = props.getProperty("base.url");
    }

    public ProjectManagement(WebDriver driver) {
        super(driver);
    }


    public void OpenProjectMgt() {
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'Project Mgmt.')]");
        SeleniumUtils.waitAndClick(driver, "//span[contains(text(),'List')]");
        SeleniumUtils.waitAndSendKeys(driver, "//input[@placeholder='Search by order id or customer name']", ProjectId);
        //press enter
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search by order id or customer name']"));
        searchInput.sendKeys(Keys.ENTER);
        SeleniumUtils.waitAndClick(driver, "//td[contains(text(),"+ProjectId+")]");
        SeleniumUtils.waitAndClick(driver, "(//button[contains(@class,'css-5zjugz')])[2]");
    }

        public void makeCustomisationOFForON() throws InterruptedException {
        Thread.sleep(2000);
            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='MuiBox-root css-zp0psn']"));
            WebElement FirstElement = elements.get(0);
            FirstElement.click();
            for( int i = 0 ; i<=elements.size()-1;i++){
                WebElement customisation = driver.findElement(By.xpath("//span[@class='MuiSwitch-track css-1khxa25']"));
                customisation.click();
                 WebElement next=driver.findElement(By.xpath("//button[contains(@class,'css-1xo5q7z')]"));
                 if(next.isEnabled()){
                     next.click();
                 }else{
                     driver.findElement(By.xpath("//span[contains(text(),'Back')]")).click();
                 }

             }


        }
        public void RaisePR(){
        driver.findElement(By.xpath("//span[contains(@class,'css-w7w8ff')]"));
        SeleniumUtils.waitAndClick(driver,"//button[contains(text(),'Create PR')]");
        SeleniumUtils.waitAndSendKeys(driver,"//input[contains(@class,'css-s43tfo')]","Foxtale");
        SeleniumUtils.waitAndClick(driver,"//*[starts-with(@id,':') and contains(@id,'option-0')]");



        }

    }

