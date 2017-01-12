package pl.agencja.client.model.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersCollection
{
	private static  ObservableList<Customer> customerCollection = FXCollections.observableArrayList();

	public static ObservableList<Customer> getCustomerList()
	{
		return customerCollection;
	}

	public static void addCustomer(Customer customer)
	{
		customerCollection.add(customer);
	}
	
	public static void clear()
	{
		customerCollection.clear();
	}

}
