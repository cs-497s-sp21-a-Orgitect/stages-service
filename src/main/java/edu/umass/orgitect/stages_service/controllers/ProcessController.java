package edu.umass.orgitect.stages_service.controllers;

import edu.umass.orgitect.stages_service.beans.CollectionResponse;
import edu.umass.orgitect.stages_service.beans.Response;
import edu.umass.orgitect.stages_service.dto.ProcessDto;
import edu.umass.orgitect.stages_service.service.ProcessService;
import edu.umass.orgitect.stages_service.entity.Process;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @PostMapping(value = "")
    public Response<ProcessDto> createProcess(@RequestBody ProcessDto processDto) {
        return new Response<>(processService.createOne(processDto));
    }

    @GetMapping(value = "")
    public CollectionResponse<ProcessDto> getAllProcesses() {
        return new CollectionResponse<>(processService.fetchAll());
    }

    @GetMapping(value = "/{processId}")
    public Response<ProcessDto> fetchOneProcess(@PathVariable(value = "processId") Long processId) throws NotFoundException {
        return new Response<>(processService.fetchOneProcess(processId));
    }


    @PutMapping(value = "/{processId}")
    public Response<ProcessDto> updateOneProcess(@PathVariable(value = "processId") Long processId, @RequestBody ProcessDto processDto) throws NotFoundException {
        return new Response<>(processService.updateOne(processId, processDto));
    }

    @DeleteMapping(value = "/{processId}")
    public void deleteOneProcess(@PathVariable(value ="processId") Long processId) {
        processService.deleteOne(processId);
    }

}
