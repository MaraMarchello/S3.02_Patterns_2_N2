package tascas302nivel2.observer;

/**
 * Observable interface for stock brokers
 * Maintains references to observers and notifies them of stock changes
 */
public interface StockBroker {
    /**
     * Register a new observer
     * @param observer The observer to register
     */
    void registerObserver(StockMarketObserver observer);
    
    /**
     * Remove an observer
     * @param observer The observer to remove
     */
    void removeObserver(StockMarketObserver observer);
    
    /**
     * Notify all registered observers of a stock change
     */
    void notifyObservers();
} 