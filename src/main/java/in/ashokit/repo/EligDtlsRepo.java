package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.EligDtlsEntity;

public interface EligDtlsRepo extends JpaRepository<EligDtlsEntity, Serializable>{

	public EligDtlsEntity findByCaseNum(Long caseNum);
}
