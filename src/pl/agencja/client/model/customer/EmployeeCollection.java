package pl.agencja.client.model.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeCollection
{
	private static ObservableList<Employee> employeeCollection = FXCollections.observableArrayList();

	public static ObservableList<Employee> getEmployeeList()
	{
		return employeeCollection;
	}

	public static void addEmployee(Employee employee)
	{
		employeeCollection.add(employee);
	}

	public static void clear()
	{
		employeeCollection.clear();
	}

}
