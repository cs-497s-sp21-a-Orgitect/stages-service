package edu.umass.orgitect.stages_service.repository;

import edu.umass.orgitect.stages_service.entity.Stage;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StageRepository extends CrudRepository<Stage, Long> {

    Collection<Stage> findAll();
    Stage findFirstByProcessIdOrderByOrderingAsc(Long processId);
    Stage findFirstByProcessIdAndOrderingGreaterThanOrderByOrderingAsc(Long processId, Integer ordering);
}
