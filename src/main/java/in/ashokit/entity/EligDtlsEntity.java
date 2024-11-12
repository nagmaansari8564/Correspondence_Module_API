package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "ED_ELIG_DTLS")
@Data
@Entity
public class EligDtlsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer EdTraceID ;
	public Long caseNum;
	public String holderName;
	public Long holderSsn;
	public String planName;
	public String PlanStatus;
	public LocalDate planStartDate;
	public LocalDate planEndDate;
	public Double benefiteAmt;
	public String denialReasion;

	

}
