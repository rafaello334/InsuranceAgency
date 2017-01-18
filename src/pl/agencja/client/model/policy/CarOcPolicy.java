package pl.agencja.client.model.policy;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OCPOLICY")
public class CarOcPolicy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OCPOLICYID", nullable = false)
	private int idCarOcPolicy;

	@Column(name = "MARK", nullable = false)
	private String carMark;

	@Column(name = "VALUE", nullable = false)
	private int carValue;

	@Column(name = "PRODUCTIONYEAR", nullable = false)
	private int productionYear;

	@Column(name = "STARTPOLICY", nullable = false)
	private LocalDate startPolicyDate;

	@Column(name = "FINISHPOLICY", nullable = false)
	private LocalDate finishPolicyDate;

	public int getIdCarCcPolicy()
	{
		return idCarOcPolicy;
	}

	public String getCarMark()
	{
		return carMark;
	}

	public void setCarMark(String carMar)
	{
		this.carMark = carMar;
	}

	public int getCarValue()
	{
		return carValue;
	}

	public void setCarValue(int carValue)
	{
		this.carValue = carValue;
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

	public int getProductionYear()
	{
		return productionYear;
	}

	public void setProductionYear(int productionYear)
	{
		this.productionYear = productionYear;
	}

}
