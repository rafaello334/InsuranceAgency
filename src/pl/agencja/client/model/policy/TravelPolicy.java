package pl.agencja.client.model.policy;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVELPOLICY")
public class TravelPolicy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAVELPOLICYID", nullable = false)
	private int idTravelPolicy;

	@Column(name = "PRICE", nullable = false)
	private double price;

	@Column(name = "DESTINATION", nullable = false)
	private String destination;

	@Column(name = "STARTPOLICY", nullable = false)
	private LocalDate startPolicyDate;

	@Column(name = "FINISHPOLICY", nullable = false)
	private LocalDate finishPolicyDate;

	public int getIdTravelPolicy()
	{
		return idTravelPolicy;
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

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

}
