package edu.umass.orgitect.stages_service.controllers;

import edu.umass.orgitect.stages_service.beans.CollectionResponse;
import edu.umass.orgitect.stages_service.beans.Response;
import edu.umass.orgitect.stages_service.dto.RequestDto;
import edu.umass.orgitect.stages_service.dto.RequestDto;
import edu.umass.orgitect.stages_service.service.RequestService;
import edu.umass.orgitect.stages_service.service.StageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping(value = "")
    public Response<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
        return new Response<>(requestService.createOne(requestDto));
    }

    @GetMapping(value = "")
    public CollectionResponse<RequestDto> getAllRequests() {
        return new CollectionResponse<>(requestService.fetchAll());
    }

    @GetMapping(value = "/{requestId}")
    public Response<RequestDto> fetchOneRequest(@PathVariable(value = "requestId") Long requestId) throws NotFoundException {
        return new Response<>(requestService.fetchOneRequest(requestId));
    }


    @PutMapping(value = "/{requestId}")
    public Response<RequestDto> updateOneRequest(@PathVariable(value = "requestId") Long requestId, @RequestBody RequestDto requestDto) throws NotFoundException {
        return new Response<>(requestService.updateOne(requestId, requestDto));
    }

    @DeleteMapping(value = "/{requestId}")
    public void deleteOneRequest(@PathVariable(value ="requestId") Long requestId) {
        requestService.deleteOne(requestId);
    }

    @PostMapping(value = "/{requestId}/nextStage")
    public Response<RequestDto> nextStage(@PathVariable(value ="requestId") Long requestId) throws NotFoundException {
        return new Response<>(requestService.moveRequestToNextStage(requestId));
    }

    @GetMapping("/customer/{customerId}")
    CollectionResponse<RequestDto> fetchAllRequestsForCustomer(@PathVariable(value = "customerId") Long customerId ) {
        return new CollectionResponse<>(requestService.fetchRequestsForCustomer(customerId));

    }
}
