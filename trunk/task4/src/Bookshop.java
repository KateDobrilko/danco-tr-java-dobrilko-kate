public class Bookshop {
    private Store store;
    private Cashier cashier;
    private OrderingSystem orderingSystem;
    private ReplySystem replySystem;

    public Bookshop(Store store, Cashier cashier,
	    OrderingSystem orderingSystem, ReplySystem replySystem) {
	this.store = store;
	this.cashier = cashier;
	this.orderingSystem = orderingSystem;
	this.replySystem = replySystem;
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

}
