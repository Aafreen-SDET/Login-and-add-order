package org.example.tests;

import org.example.pages.AddOrder;
import org.example.pages.EditOrder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditOrderTest extends BaseTest {
    //invocationCount = 2
    @Test(groups = {"editOrder"})
    public void testEditOrderFlow() throws InterruptedException {
        String orderId;
        try {
            orderId = new String(Files.readAllBytes(Paths.get("C:\\Users\\aafre\\FreshSwageazy\\orderId.txt"))).trim();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read Order ID from file: " + e.getMessage(), e);
        }

        EditOrder editOrder = new EditOrder(driver);
        AddOrder Add = new AddOrder(driver);
        Add.login();
        editOrder.raiseStockCheckRequest(orderId);
        editOrder.raiseProjectRequest(orderId);
        editOrder.scrAction(orderId);
        Thread.sleep(2000);
        editOrder.addService(orderId);
    }
}



