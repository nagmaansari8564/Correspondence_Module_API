package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PLAN_MASTER")
public class Plan {

	@Id
	@GeneratedValue
	@Column(name = "PLAN_ID")
	private Integer planId	;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate	;	
	
	@Column(name = "PLAN_CATEGORY_ID")
	private Integer planCategoryId;		
	
	@Column(name = "ACTIVE_SW")
    private String activeSw;
	
	@Column(name = "CREATED_BY")
    private String createdBy;
	
	@Column(name = "UPDATED_BY")
    private String updatedBy;		
	
	@Column(name = "CREATE_DATE", updatable = false)
	@CreationTimestamp()
    private LocalDate createDate;
	
	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
    private LocalDate updateDate;

}
