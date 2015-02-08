public class Bookshop {
    private Store store;
    private Cashier cashier;
    private OrderingSystem orderingSystem;
    private ReplySystem replySystem;
    private CustomerBase customerBase;
    

    public Bookshop(Store store, Cashier cashier,
	    OrderingSystem orderingSystem, ReplySystem replySystem, CustomerBase customerBase) {
	this.store = store;
	this.cashier = cashier;
	this.orderingSystem = orderingSystem;
	this.replySystem = replySystem;
	this.customerBase = customerBase;
    }

    public Store getStore() {
	return store;
    }

    public Cashier getCashier() {
	return cashier;
    }

    public OrderingSystem getOrderingSystem() {
	return orderingSystem;
    }

    public ReplySystem getReplySystem() {
	return replySystem;
    }

    public CustomerBase getCustomerBase() {
	return customerBase;
    }

    

}
