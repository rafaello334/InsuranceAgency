package pl.agencja.client.model.policy;

import java.util.Date;

import pl.agencja.client.model.customer.Customer;

public class TravelPolicy
{
	private Customer customer;
	private String countryName;
	private Date startPolicyDate;
	private Date finishPolicyDate;

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public String getCountryName()
	{
		return countryName;
	}

	public void setCountryName(String countryName)
	{
		this.countryName = countryName;
	}

	public Date getStartPolicyDate()
	{
		return startPolicyDate;
	}

	public void setStartPolicyDate(Date startPolicyDate)
	{
		this.startPolicyDate = startPolicyDate;
	}

	public Date getFinishPolicyDate()
	{
		return finishPolicyDate;
	}

	public void setFinishPolicyDate(Date finishPolicyDate)
	{
		this.finishPolicyDate = finishPolicyDate;
	}

	public TravelPolicy(Customer customer, String countryName, Date startPolicyDate, Date finishPolicyDate)
	{
		this.customer = customer;
		this.countryName = countryName;
		this.startPolicyDate = startPolicyDate;
		this.finishPolicyDate = finishPolicyDate;
	}

}
