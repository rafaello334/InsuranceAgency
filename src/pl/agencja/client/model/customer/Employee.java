package pl.agencja.client.model.customer;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee
{
	private BooleanProperty isAdmin;
	private IntegerProperty idEmployee;
	private StringProperty firstName;
	private StringProperty lastName;
	private IntegerProperty age;
	private StringProperty emailAddress;
	private ObjectProperty<LocalDate> joinDate;
	private ObjectProperty<LocalDate> birthDate;
	private StringProperty userName;
	private StringProperty password;
	private String administrator;

	public String getAdministrator()
	{
		return administrator;
	}

	public void setAdministrator(String administrator)
	{
		this.administrator = administrator;
	}

	public String getFirstName()
	{
		return firstName.get();
	}

	public StringProperty firstNameProperty()
	{
		return firstName;
	}

	public void setFirstName(StringProperty firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName.get();
	}

	public void setLastName(StringProperty lastName)
	{
		this.lastName = lastName;
	}

	public StringProperty lastNameProperty()
	{
		return lastName;
	}

	public int getAge()
	{
		return age.get();
	}

	public void setAge(IntegerProperty age)
	{
		this.age = age;
	}

	public IntegerProperty ageProperty()
	{
		return age;
	}

	public LocalDate getJoinDate()
	{
		return joinDate.get();
	}

	public void setJoinDate(ObjectProperty<LocalDate> joinDate)
	{
		this.joinDate = joinDate;
	}

	public ObjectProperty<LocalDate> joinDateProperty()
	{
		return joinDate;
	}

	public String getUserName()
	{
		return userName.get();
	}

	public void setUserName(StringProperty userName)
	{
		this.userName = userName;
	}

	public StringProperty userNameProperty()
	{
		return userName;
	}

	public String getPassword()
	{
		return password.get();
	}

	public void setPassword(StringProperty password)
	{
		this.password = password;
	}

	public StringProperty passwordProperty()
	{
		return password;
	}

	public String getEmailAdress()
	{
		return emailAddress.get();
	}

	public void setEmailAdress(StringProperty emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public StringProperty emailAddressProperty()
	{
		return userName;
	}

	public LocalDate getBirthDate()
	{
		return birthDate.get();
	}

	public void setBirthDate(ObjectProperty<LocalDate> birthDate)
	{
		this.birthDate = birthDate;
	}

	public ObjectProperty<LocalDate> birthDateProperty()
	{
		return birthDate;
	}

	public int getIdEmployee()
	{
		return idEmployee.get();
	}

	public void setIdEmployee(IntegerProperty idEmployee)
	{
		this.idEmployee = idEmployee;
	}

	public IntegerProperty idEmployeeProperty()
	{
		return idEmployee;
	}

	public boolean isAdmin()
	{
		return isAdmin.get();
	}

	public void setAdmin(BooleanProperty isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	public BooleanProperty isAdminProperty()
	{
		return isAdmin;
	}

	public Employee(String firstName, String lastName, int age, String emailAddress, LocalDate birthDate,
			String userName, String password, boolean isAdmin)
	{
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.age = new SimpleIntegerProperty(age);
		this.emailAddress = new SimpleStringProperty(emailAddress);
		this.joinDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
		this.userName = new SimpleStringProperty(userName);
		this.password = new SimpleStringProperty(password);
		this.isAdmin = new SimpleBooleanProperty(isAdmin);

		if (isAdmin)
		{
			administrator = "Posiada";
		} else
		{
			administrator = "Nie posiada";
		}
	}

	@Override
	public String toString()
	{
		return "Employee [idEmployee=" + idEmployee + ", firstName=" + firstName + ", lastName=" + lastName + ", age="
				+ age + ", emailAdress=" + emailAddress + ", joinDate=" + joinDate + ", birthDate=" + birthDate
				+ " administrator=" + administrator + "]";
	}

}
