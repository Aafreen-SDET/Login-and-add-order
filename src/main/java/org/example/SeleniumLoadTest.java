package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SeleniumLoadTest {
    private static final int REQUEST_COUNT = 1000;  // Total number of logins
    private static final int THREAD_COUNT = 5;     // Number of concurrent browsers
    private static final String URL = "https://example.com/login"; // Replace with your login URL

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Long> responseTimes = new ArrayList<>();

        for (int i = 0; i < REQUEST_COUNT; i++) {
            executor.submit(() -> {
                Instant start = Instant.now();
                WebDriver driver = getDriver();
                try {
                    driver.get(URL);
                    login(driver);
                    Instant end = Instant.now();
                    long responseTime = Duration.between(start, end).toMillis();
                    responseTimes.add(responseTime);
                    System.out.println("Login Successful | Response Time: " + responseTime + " ms");
                    // Pause to observe the UI (optional)
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.err.println("Login Failed: " + e.getMessage());
                } finally {
                    driver.quit();
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // Summary
        System.out.println("\nTotal Requests: " + REQUEST_COUNT);
        System.out.println("Total Successful Requests: " + responseTimes.size());
        System.out.println("Average Response Time: " + responseTimes.stream().mapToLong(Long::longValue).average().orElse(0) + " ms");
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Replace with your ChromeDriver path
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    public static void login(WebDriver driver) {
        WebElement username = driver.findElement(By.xpath("//*[contains(@class,'css-9jacqo')]"));
        username.sendKeys("afreen.khan@swageazy.com");

        WebElement password = driver.findElement(By.xpath("//*[contains(@class,'css-1qs43bf')]"));
        password.sendKeys("Dania@123");

        WebElement next = driver.findElement(By.xpath("//*[contains(@class,'css-olgitz')]"));
        next.click();
    }
}
