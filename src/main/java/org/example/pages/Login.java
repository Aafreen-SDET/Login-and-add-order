package org.example.pages;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.example.Base.BasePage;
import org.example.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;

public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver);
    }

    // Method to login using dynamic credentials (from Excel)
    public void login(String username, String password) {
        try {
            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@class,'css-1pk1fka')]", username);
            SeleniumUtils.waitAndSendKeys(driver, "//input[contains(@class,'css-qwdxx6')]", password);
            SeleniumUtils.waitAndClick(driver, "//button[contains(@class,'css-1m5l81m')]");
            Thread.sleep(2000);  // Consider replacing with WebDriverWait if needed

            System.out.println("Login successful and dashboard loaded");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    // Read login data from Excel file (returns as Object[][] for DataProvider)
    public static Object[][] getLoginDataFromExcel() throws InvalidFormatException {
        String filePath = "src/main/resources/login.xlsx";
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
