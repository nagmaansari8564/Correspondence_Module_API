package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.DcEducation;

public interface DcEducationRepo extends JpaRepository<DcEducation, Serializable>{

	public DcEducation findByCaseNum(Long caseNum);
}
