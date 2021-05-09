package edu.umass.orgitect.stages_service.repository;

import edu.umass.orgitect.stages_service.entity.Request;
import edu.umass.orgitect.stages_service.entity.Stage;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;


public interface RequestRepository extends CrudRepository<Request, Long> {

    Collection<Request> findAll();

    Collection<Request> findAllByCustomerId(Long customerId);

    Collection<Request> findAllByCustomerIdAndActorId(Long customerId, Long actorId);

    Collection<Request> findAllByActorIdIsNull();

}