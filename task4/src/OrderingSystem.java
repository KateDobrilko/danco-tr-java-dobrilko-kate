import java.util.ArrayList;

public class OrderingSystem {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public OrderingSystem() {
	
    }
    public OrderingSystem(ArrayList<Order> orders) {
	this.orders = orders;
    }

    public void executeOrder(int orderId, Cashier cashier, Store store) {
	for (Order order : orders) {
	    
	    
	    
	    if (order.getId()==orderId) {
		order.setExecuted(true);
		for (BookType bookType: order.getBookOrder()) {
		    cashier.addSum(bookType.getPrice());
		}
	    }
	}
    }

    public void addOrder(Order order) {
	orders.add(order);
    }

    public ArrayList<Order> getOrders() {
	return orders;
    }

    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Order order : orders) {
	    sb.append(order.toString() + System.lineSeparator());
	}
	return sb.toString();

    }

}
