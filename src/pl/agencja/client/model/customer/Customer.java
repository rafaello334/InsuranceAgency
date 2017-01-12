package pl.agencja.client.model.customer;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer
{
	private IntegerProperty idClient;
	private StringProperty firstName;
	private StringProperty lastName;
	private IntegerProperty age;
	private StringProperty country;
	private StringProperty address;
	private LongProperty phoneNumber;
	private ObjectProperty<LocalDate> joinDate;
	private ObjectProperty<LocalDate> birthDate;

	public IntegerProperty idClientProperty()
	{
		return idClient;
	}

	public int getIdClient()
	{
		return idClient.get();
	}

	public void setIdClient(IntegerProperty idClient)
	{
		this.idClient = idClient;
	}

	public StringProperty firstNameProperty()
	{
		return firstName;
	}

	public String getFirstName()
	{
		return firstName.get();
	}

	public void setFirstName(StringProperty firstName)
	{
		this.firstName = firstName;
	}

	public StringProperty lastNameProperty()
	{
		return lastName;
	}

	public void setLastName(StringProperty lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName.get();
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

	public StringProperty countryProperty()
	{
		return country;
	}

	public void setCountry(StringProperty country)
	{
		this.country = country;
	}

	public String getCountry()
	{
		return country.get();
	}

	public String getAddress()
	{
		return address.get();
	}

	public void setAdress(StringProperty adress)
	{
		this.address = adress;
	}

	public StringProperty addressProperty()
	{
		return address;
	}

	public Long getPhoneNumber()
	{
		return phoneNumber.get();
	}

	public void setPhoneNumber(LongProperty phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public LongProperty phoneNumberProperty()
	{
		return phoneNumber;
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

	public Customer(String firstName, String lastName, int age, String country, String adress, long phoneNumber,
			LocalDate birthDate)
	{
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.age = new SimpleIntegerProperty(age);
		this.country = new SimpleStringProperty(country);
		this.address = new SimpleStringProperty(adress);
		this.phoneNumber = new SimpleLongProperty(phoneNumber);
		this.birthDate = new SimpleObjectProperty<LocalDate>(birthDate);
		this.joinDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}

	@Override
	public String toString()
	{
		return "Imiê: " + firstName + "\nNazwisko:" + lastName + "\nWiek: " + age + "\nKraj: " + country + "\nAdres: "
				+ address + "\nNumer Telefonu: " + phoneNumber + "\nData do³¹czenia: " + joinDate + "\nData urodzenia: "
				+ birthDate + "]";
	}

}
