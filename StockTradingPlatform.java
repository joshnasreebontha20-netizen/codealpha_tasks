import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

class User {
    private String name;
    private double balance;
    private Map<String, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;

        if (cost <= balance) {
            balance -= cost;
            portfolio.put(
                    stock.getSymbol(),
                    portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
            );

            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        String symbol = stock.getSymbol();

        if (portfolio.containsKey(symbol) && portfolio.get(symbol) >= quantity) {
            balance += stock.getPrice() * quantity;
            portfolio.put(symbol, portfolio.get(symbol) - quantity);

            if (portfolio.get(symbol) == 0) {
                portfolio.remove(symbol);
            }

            System.out.println("Sold " + quantity + " shares of " + symbol);
        } else {
            System.out.println("Not enough shares!");
        }
    }

    public void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n----- Portfolio -----");

        double totalValue = 0;

        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            double value = qty * market.get(symbol).getPrice();

            totalValue += value;

            System.out.println(
                    symbol + " | Shares: " + qty +
                    " | Value: $" + value
            );
        }

        System.out.println("Balance: $" + balance);
        System.out.println("Portfolio Value: $" + totalValue);
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Stock> market = new HashMap<>();

        market.put("AAPL", new Stock("AAPL", 180));
        market.put("TSLA", new Stock("TSLA", 220));
        market.put("GOOG", new Stock("GOOG", 2500));
        market.put("AMZN", new Stock("AMZN", 3300));

        User user = new User("Investor", 10000);

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks:");
                    for (Stock stock : market.values()) {
                        System.out.println(
                                stock.getSymbol() +
                                " - $" +
                                stock.getPrice()
                        );
                    }
                    break;

                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next().toUpperCase();

                    if (market.containsKey(buySymbol)) {
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        user.buyStock(market.get(buySymbol), qty);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next().toUpperCase();

                    if (market.containsKey(sellSymbol)) {
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        user.sellStock(market.get(sellSymbol), qty);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 4:
                    user.showPortfolio(market);
                    break;

                case 5:
                    System.out.println("Thank you for using the platform!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
