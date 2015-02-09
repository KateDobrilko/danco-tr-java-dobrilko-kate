package com.danco.training.dobrilko.task4.model;
import java.util.ArrayList;

public class CustomerBase {
    private ArrayList<Customer> customers = new ArrayList<Customer>();

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
    
    public String toString(){
      StringBuilder sb = new StringBuilder();
      sb.append("CustomerBase "+System.lineSeparator()+customers.size()+System.lineSeparator());
      for(Customer customer: customers){
      sb.append(customer.toString() + System.lineSeparator());
      
      }
      return sb.toString();
    }

}
