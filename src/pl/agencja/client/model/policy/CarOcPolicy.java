package pl.agencja.client.model.policy;

import java.util.Date;

public class CarOcPolicy
{
	private int idCarCcPolicy;
	private String carMar;
	private int carValue;
	private Date startPolicyDate;
	private Date finishPolicyDate;

	public int getIdCarCcPolicy()
	{
		return idCarCcPolicy;
	}

	public void setIdCarCcPolicy(int idCarCcPolicy)
	{
		this.idCarCcPolicy = idCarCcPolicy;
	}

	public String getCarMar()
	{
		return carMar;
	}

	public void setCarMar(String carMar)
	{
		this.carMar = carMar;
	}

	public int getCarValue()
	{
		return carValue;
	}

	public void setCarValue(int carValue)
	{
		this.carValue = carValue;
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

	public CarOcPolicy(int idCarCcPolicy, String carMar, int carValue, Date startPolicyDate, Date finishPolicyDate)
	{
		this.idCarCcPolicy = idCarCcPolicy;
		this.carMar = carMar;
		this.carValue = carValue;
		this.startPolicyDate = startPolicyDate;
		this.finishPolicyDate = finishPolicyDate;
	}

}
