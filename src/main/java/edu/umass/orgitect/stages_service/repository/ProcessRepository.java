package edu.umass.orgitect.stages_service.repository;

import edu.umass.orgitect.stages_service.entity.Process;
import edu.umass.orgitect.stages_service.entity.Stage;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface ProcessRepository extends CrudRepository<Process, Long> {

    Collection<Process> findAll();

}
