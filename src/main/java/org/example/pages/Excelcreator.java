package org.example.pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excelcreator {

    public static void main(String[] args) {
        String[] headers = {"Username", "Password", "Type"};
        String[][] data = {
                {"Afreen.khan@swageazy.com", "Sania@123", "Positive"},
                {"user2@swageazy.com", "wrong1", "Negative"},
                {"Aafreenkhanzee@gmail.com", "Dania@123", "Positive"},
                {"user4@swageazy.com", "wrong4", "Negative"}
        };

        String filePath = "src/main/resources/login.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Add data rows
            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    row.createCell(j).setCellValue(data[i][j]);
                }
            }

            // Autosize columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                System.out.println("Excel file created successfully at: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
