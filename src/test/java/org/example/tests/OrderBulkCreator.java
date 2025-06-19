//package org.example.tests;
//import org.example.pages.AddOrder;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//public class OrderBulkCreator {
//    @Test
//        @Parameters("orderCount")
//        public void createMultipleOrders(int orderCount) {
//            for (int i = 1; i <= orderCount; i++) {
//                System.out.println("=== Creating Order #" + i + " ===");
//                createOrder();
//            }
//        }
//
//        public void createOrder() {
//            // Assuming AddOrder contains your actual Selenium flow
//            AddOrder order = new AddOrder();
//            try {
//                order.setup();         // If AddOrder requires setup
//                order.testAddOrder();  // Your existing method that adds an order
//            } catch (Exception e) {
//                System.out.println("Error while creating order: " + e.getMessage());
//            } finally {
//                order.tearDown();      // Always cleanup WebDriver session
//            }
//        }
//    }
//
//}
