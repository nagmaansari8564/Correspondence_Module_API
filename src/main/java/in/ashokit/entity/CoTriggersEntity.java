package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CO_TRIGGERS")
public class CoTriggersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer trgID;
	public Long caseNum;
	@Lob
	public byte[] coPdf;
	public String TrgStatus;

}
