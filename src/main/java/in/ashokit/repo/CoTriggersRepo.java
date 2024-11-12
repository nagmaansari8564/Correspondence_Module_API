package in.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CoTriggersEntity;

public interface CoTriggersRepo extends JpaRepository<CoTriggersEntity, Serializable>{

	public List<CoTriggersEntity> findByTrgStatus(String status);
}
