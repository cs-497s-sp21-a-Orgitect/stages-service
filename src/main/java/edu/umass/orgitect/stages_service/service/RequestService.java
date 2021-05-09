package edu.umass.orgitect.stages_service.service;

import edu.umass.orgitect.stages_service.dto.ProcessDto;
import edu.umass.orgitect.stages_service.dto.RequestDto;
import edu.umass.orgitect.stages_service.dto.RequestDto;
import edu.umass.orgitect.stages_service.dto.StageDto;
import edu.umass.orgitect.stages_service.entity.Request;
import edu.umass.orgitect.stages_service.entity.Request;
import edu.umass.orgitect.stages_service.entity.Stage;
import edu.umass.orgitect.stages_service.repository.RequestRepository;
import edu.umass.orgitect.stages_service.repository.StageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    StageRepository stageRepository;

    public RequestDto fetchOneRequest(Long requestId) throws NotFoundException {

        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Request not found");
        }
        return RequestDto.fromRequest(requestOptional.get());

    }

    public Collection<RequestDto> fetchAll() {
        return RequestDto.toRequestDto(requestRepository.findAll());
    }

    public RequestDto createOne(RequestDto requestDto) {

        Stage stage = stageRepository.findFirstByProcessIdOrderByOrderingAsc(requestDto.getProcess().getId());
        requestDto.setCurrentStage(StageDto.fromStage(stage));
        Request savedRequest = requestRepository.save(RequestDto.toRequest(requestDto));
        return RequestDto.fromRequest(savedRequest);
    }

    public RequestDto updateOne(Long id, RequestDto requestDto) throws NotFoundException {
        Optional<Request> requestOptional = requestRepository.findById(id);
        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Request not found");
        }
        requestDto.setId(id);

        Request savedRequest = requestRepository.save(RequestDto.toRequest(requestDto));
        return RequestDto.fromRequest(savedRequest);
    }

    public void deleteOne(Long id) {
        requestRepository.deleteById(id);
    }


    public RequestDto moveRequestToNextStage(Long id) throws NotFoundException {

        Optional<Request> requestOptional = requestRepository.findById(id);

        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Request not found");
        }

        Request request = requestOptional.get();

        Stage stage;
        if (request.getCurrentStage() != null) {
             stage = stageRepository.findFirstByProcessIdAndOrderingGreaterThanOrderByOrderingAsc(
                    request.getProcess().getId(), request.getCurrentStage().getOrdering());
        } else {
            stage = stageRepository.findFirstByProcessIdOrderByOrderingAsc(request.getProcess().getId());

        }
        request.setCurrentStage(stage);
        Request savedRequest = requestRepository.save(request);
        return RequestDto.fromRequest(savedRequest);
    }

    public Collection<RequestDto> fetchRequestsForCustomer(Long customerId) {

        return RequestDto.toRequestDto(requestRepository.findAllByCustomerId(customerId));
    }

    public Collection<RequestDto> fetchRequestsForCustomerActor(Long customerId, Long actorId) {

        return RequestDto.toRequestDto(requestRepository.findAllByCustomerIdAndActorId(customerId, actorId));

    }

    public RequestDto setActorIdForRequest(Long requestId, Long actorId) throws NotFoundException {
        Optional<Request> requestOptional = requestRepository.findById(requestId);

        if (requestOptional.isEmpty()) {
            throw new NotFoundException("Request not found");
        }

        Request request = requestOptional.get();

        request.setActorId(actorId);

        Request savedRequest = requestRepository.save(request);

        return RequestDto.fromRequest(savedRequest);
    }

    public Collection<RequestDto> fetchUnassignedRequests() {
        return RequestDto.toRequestDto(requestRepository.findAllByActorIdIsNull());
    }
}
