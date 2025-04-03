package tascas302nivel2.observer;

/**
 * Observer interface for stock market agencies
 * These observers will be notified of stock market changes
 */
public interface StockMarketObserver {
    /**
     * Method called by the observable when stock market changes
     * @param stockSymbol The stock symbol that changed
     * @param price The new price
     * @param isRising True if the stock is rising, false if falling
     */
    void update(String stockSymbol, double price, boolean isRising);
} 