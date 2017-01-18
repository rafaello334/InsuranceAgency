package pl.agencja.client.model.customer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMERID", nullable = false)
	private int idCustomer;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	@Column(name = "COUNTRY", nullable = false)
	private String country;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "PHONENUMBER", nullable = false)
	private Long phoneNumber;

	@Column(name = "JOINDATE", nullable = false)
	private LocalDate joinDate;

	@Column(name = "BIRTHDATE", nullable = false)
	private LocalDate birthDate;

	public int getIdCustomer()
	{
		return idCustomer;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Long getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getJoinDate()
	{
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate)
	{
		this.joinDate = joinDate;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

}
