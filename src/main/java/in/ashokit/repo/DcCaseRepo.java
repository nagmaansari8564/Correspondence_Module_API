package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.DcCaseEntity;

public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Serializable>{

	public DcCaseEntity findByAppId(Integer AppId);
	
	public DcCaseEntity findByPlanId(Integer appId);
}
