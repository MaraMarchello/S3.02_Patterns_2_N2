package tascas302nivel2.observer;

/**
 * Concrete implementation of the StockMarketObserver
 * Represents a stock market agency that receives updates on stock changes
 */
public class StockMarketAgency implements StockMarketObserver {
    private String agencyName;
    
    public StockMarketAgency(String agencyName) {
        this.agencyName = agencyName;
    }
    
    @Override
    public void update(String stockSymbol, double price, boolean isRising) {
        String marketTrend = isRising ? "rising" : "falling";
        System.out.println(agencyName + " received update: Stock " + stockSymbol + 
                           " is " + marketTrend + " to $" + price);
        
        // Agencies can perform their specific actions here
        if (isRising) {
            recommendBuy(stockSymbol, price);
        } else {
            recommendSell(stockSymbol, price);
        }
    }
    
    private void recommendBuy(String stockSymbol, double price) {
        System.out.println(agencyName + " recommends: BUY " + stockSymbol + " at $" + price);
    }
    
    private void recommendSell(String stockSymbol, double price) {
        System.out.println(agencyName + " recommends: SELL " + stockSymbol + " at $" + price);
    }
} 