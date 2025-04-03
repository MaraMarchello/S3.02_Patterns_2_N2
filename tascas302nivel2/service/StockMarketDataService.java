package tascas302nivel2.service;

import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import tascas302nivel2.observer.ConcreteStockBroker;

/**
 * Service to connect to Alpha Vantage API for real-time stock data
 */
public class StockMarketDataService {
    private final OkHttpClient client;
    private final String apiKey;
    private final ConcreteStockBroker broker;
    
    // Polling interval in milliseconds
    private final int POLLING_INTERVAL = 60000; // Default to 1 minute
    private volatile boolean running = false;
    
    public StockMarketDataService(String apiKey, ConcreteStockBroker broker) {
        this.client = new OkHttpClient();
        this.apiKey = apiKey;
        this.broker = broker;
    }
    
    /**
     * Start monitoring specific stocks in real-time
     * @param symbols Array of stock symbols to monitor
     */
    public void startRealTimeMonitoring(String... symbols) {
        if (running) {
            System.out.println("Monitoring already in progress");
            return;
        }
        
        running = true;
        
        // Create a separate thread for polling to avoid blocking the main thread
        Thread monitoringThread = new Thread(() -> {
            System.out.println("Started real-time stock monitoring");
            
            while (running) {
                for (String symbol : symbols) {
                    try {
                        double price = fetchLatestStockPrice(symbol);
                        broker.setStockPrice(symbol, price);
                        
                        // Sleep a bit between API calls to avoid hitting rate limits
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.err.println("Error fetching data for " + symbol + ": " + e.getMessage());
                    }
                }
                
                try {
                    // Wait for the polling interval before checking again
                    Thread.sleep(POLLING_INTERVAL);
                } catch (InterruptedException e) {
                    running = false;
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        monitoringThread.setDaemon(true);
        monitoringThread.start();
    }
    
    /**
     * Stop the real-time monitoring
     */
    public void stopRealTimeMonitoring() {
        running = false;
        System.out.println("Stopped real-time stock monitoring");
    }
    
    /**
     * Fetch the latest stock price from Alpha Vantage API
     * @param symbol Stock symbol
     * @return Latest stock price
     * @throws IOException If API request fails
     */
    private double fetchLatestStockPrice(String symbol) throws IOException {
        // Documentation: https://www.alphavantage.co/documentation/
        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
        
        Request request = new Request.Builder()
            .url(url)
            .build();
        
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            
            String responseBody = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            
            // Extract the price from the response
            JsonObject globalQuote = jsonObject.getAsJsonObject("Global Quote");
            if (globalQuote != null && !globalQuote.isJsonNull()) {
                String price = globalQuote.get("05. price").getAsString();
                return Double.parseDouble(price);
            } else {
                throw new IOException("Invalid API response or data not found for symbol: " + symbol);
            }
        }
    }
} 