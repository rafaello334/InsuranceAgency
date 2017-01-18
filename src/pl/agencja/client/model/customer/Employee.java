package pl.agencja.client.model.customer;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
public class Employee
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EMPLOYEEID", nullable = false)
	private int idEmployee;

	@Column(name = "ISADMIN", nullable = false)
	private boolean isAdmin;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
	private String lastName;

	@Column(name = "EMAILADDRESS", nullable = false)
	private String emailAddress;

	@Column(name = "JOINDATE", nullable = false)
	private LocalDate joinDate;

	@Column(name = "BIRTHDATE", nullable = false)
	private LocalDate birthDate;

	@Column(name = "USERNAME", nullable = false)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name ="ADMINISTRATOR")
	private String administrator;

	public boolean isAdmin()
	{
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	public int getIdEmployee()
	{
		return idEmployee;
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

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
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

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAdministrator()
	{
		return administrator;
	}

	public void setAdministrator(String administrator)
	{
		this.administrator = administrator;
	}

}
