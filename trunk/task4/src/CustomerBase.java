import java.util.ArrayList;

public class CustomerBase {
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
	return customers;
    }
    
    public CustomerBase() {

    }

    public CustomerBase(ArrayList<Customer> customers) {
	this.customers = customers;
    }

    public void addCustomer(Customer customer) {
	customers.add(customer);
    }

    public void deleteCustomer(Customer customer) {
	customers.remove(customer);
    }

}
