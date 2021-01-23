import org.graalvm.compiler.java.BciBlockMapping.BciBlock;

// This is a behaviour pattern. 
// Converts requests or simple operations into objects.

// In this example, Stock is the command, Broker is the invoker which will send the command
// to the concrete objects which handles the command: BuyStock and SellStock (both implements Order).

import java.util.ArrayList;
import java.util.List;

interface Order {
    void execute();
}

class Stock {
    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock: " + name + " Quantity: " + quantity + " bought");
    }

    public void sell() {
        System.out.println("Stock: " + name + " Quantity: " + quantity + " sold");
    }
}

class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}

class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}

class broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order)
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}

public class Command {
    public static void main(String[] args) {
        Stock abcStock = new Stock();

        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);

        broker.placeOrders();
    }
}