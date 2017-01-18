package pl.agencja.client.model.policy;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LIFEPOLICY")
public class LifePolicy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIFEPOLICYID", nullable = false)
	private int idLifePolicy;

	@Column(name = "AGE", nullable = false)
	private int age;

	@Column(name = "INSURANCEPRICE", nullable = false)
	private double insurancePrice;

	@Column(name = "STARTPOLICY", nullable = false)
	private LocalDate startPolicyDate;

	@Column(name = "FINISHPOLICY", nullable = false)
	private LocalDate finishPolicyDate;

	public int getIdLifePolicy()
	{
		return idLifePolicy;
	}

	public LocalDate getStartPolicyDate()
	{
		return startPolicyDate;
	}

	public void setStartPolicyDate(LocalDate startPolicyDate)
	{
		this.startPolicyDate = startPolicyDate;
	}

	public LocalDate getFinishPolicyDate()
	{
		return finishPolicyDate;
	}

	public void setFinishPolicyDate(LocalDate finishPolicyDate)
	{
		this.finishPolicyDate = finishPolicyDate;
	}

	public double getInsurancePrice()
	{
		return insurancePrice;
	}

	public void setInsurancePrice(double price)
	{
		this.insurancePrice = price;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

}
