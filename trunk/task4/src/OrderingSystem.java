import java.util.ArrayList;

public class OrderingSystem {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public OrderingSystem(ArrayList<Order> orders) {
	this.orders = orders;
    }

    public void executeOrder(String orderId, Cashier cashier) {
	for (Order order : orders) {
	    if (order.getId().equals(orderId)) {
		order.setExecuted(true);
		for (BookExemplar bookExemplar : order.getBooks()) {
		    cashier.addSum(bookExemplar.getPrice());
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
    
    public String toString(){
	
    }
    }

}
