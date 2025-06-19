package org.example.tests;

import org.example.pages.AddOrder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

//import java.io.FileWriter;

public class AddOrderTest extends BaseTest {
    private AddOrder addOrderPage;

//invocationCount = 2
    @Test(groups = {"login", "addOrder"})
    public void testAddOrder() {
        addOrderPage = new AddOrder(driver);
        addOrderPage.login();
        addOrderPage.addOrder();
        addOrderPage.selectUser();
        addOrderPage.selectAddress();
        addOrderPage.addProducts();

        Assert.assertNotNull(AddOrder.Orderid, "Order ID should not be null after adding order");
        try (FileWriter writer = new FileWriter("orderId.txt")) {
            writer.write(AddOrder.Orderid);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save Order ID to file: " + e.getMessage(), e);
        }
    }
    }

