package tascas302nivel2.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of StockBroker (Observable)
 * Maintains a list of observers and notifies them of stock changes
 */
public class ConcreteStockBroker implements StockBroker {
    private List<StockMarketObserver> observers;
    private String stockSymbol;
    private double price;
    private boolean isRising;
    
    public ConcreteStockBroker() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(StockMarketObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void removeObserver(StockMarketObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (StockMarketObserver observer : observers) {
            observer.update(stockSymbol, price, isRising);
        }
    }
    
    /**
     * Updates the stock price and notifies observers
     * @param stockSymbol The stock symbol
     * @param newPrice The new price
     */
    public void setStockPrice(String stockSymbol, double newPrice) {
        double oldPrice = this.price;
        this.stockSymbol = stockSymbol;
        this.price = newPrice;
        this.isRising = newPrice > oldPrice;
        
        notifyObservers();
    }
    
    /**
     * Get the list of registered observers
     * @return List of registered observers
     */
    public List<StockMarketObserver> getObservers() {
        return observers;
    }
} 