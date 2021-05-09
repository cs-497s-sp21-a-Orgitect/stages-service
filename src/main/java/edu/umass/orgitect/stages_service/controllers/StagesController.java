package edu.umass.orgitect.stages_service.controllers;

import edu.umass.orgitect.stages_service.beans.CollectionResponse;
import edu.umass.orgitect.stages_service.beans.Response;
import edu.umass.orgitect.stages_service.dto.StageDto;
import edu.umass.orgitect.stages_service.service.StageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/stages")
public class StagesController {

    @Autowired
    StageService stageService;

    @PostMapping(value = "")
    public Response<StageDto> createStage(@RequestBody StageDto stageDto) {
        return new Response<>(stageService.createOne(stageDto));
    }

    @GetMapping(value = "")
    public CollectionResponse<StageDto> getAllStages() {
        return new CollectionResponse<>(stageService.fetchAll());
    }

    @GetMapping(value = "/{stageId}")
    public Response<StageDto> fetchOneStage(@PathVariable(value = "stageId") Long stageId) throws NotFoundException {
        return new Response<>(stageService.fetchOne(stageId));
    }

    @PutMapping(value = "/{stageId}")
    public Response<StageDto> updateOneStage(@PathVariable(value = "stageId") Long stageId, @RequestBody StageDto stageDto) throws NotFoundException {
        return new Response<>(stageService.updateOne(stageId, stageDto));
    }

    @DeleteMapping(value = "/{stageId}")
    public void deleteOneStage(@PathVariable(value ="stageId") Long stageId) {
        stageService.deleteOne(stageId);
    }
}
