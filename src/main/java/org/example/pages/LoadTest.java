package org.example.pages;import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoadTest {



        private static final String API_URL = "https://dev-api.swageazy.com/admin/login"; // Replace with your API URL
        private static final int REQUEST_COUNT = 1000;
        private static final int THREAD_COUNT = 50; // Number of concurrent threads

        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            HttpClient client = HttpClient.newHttpClient();

            for (int i = 0; i < REQUEST_COUNT; i++) {
                executor.submit(() -> {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(API_URL))
                            .GET() // Use POST() for POST requests
                            .build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        System.out.println("Response Code: " + response.statusCode());
                    } catch (Exception e) {
                        System.err.println("Request failed: " + e.getMessage());
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
        }
    }


