package pl.agencja.client.model.policy;

import java.util.Date;

import pl.agencja.client.model.customer.Customer;

public class LifePolicy
{
	private Customer customer;
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

	public LifePolicy(Customer customer, Date startPolicyDate, Date finishPolicyDate)
	{
		this.customer = customer;
		this.startPolicyDate = startPolicyDate;
		this.finishPolicyDate = finishPolicyDate;
	}

}
