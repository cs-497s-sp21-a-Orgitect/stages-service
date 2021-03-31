package edu.umass.orgitect.stages_service.service;

import edu.umass.orgitect.stages_service.dto.StageDto;
import edu.umass.orgitect.stages_service.entity.Stage;
import edu.umass.orgitect.stages_service.repository.StageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    StageRepository stageRepository;

    public StageDto fetchOne(Long id) throws NotFoundException {
        Optional<Stage> stageOptional = stageRepository.findById(id);
        if (stageOptional.isEmpty()) {
           throw new NotFoundException("Stage not found");
        }
        return StageDto.fromStage(stageOptional.get());
    }

    public Collection<StageDto> fetchAll() {
        return StageDto.toStageDtos(stageRepository.findAll());
    }

    public StageDto createOne(StageDto stageDto) {
        Stage savedStage = stageRepository.save(StageDto.toStage(stageDto));
        return StageDto.fromStage(savedStage);
    }

    public StageDto updateOne(Long id, StageDto stageDto) throws NotFoundException {
        Optional<Stage> stageOptional = stageRepository.findById(id);
        if (stageOptional.isEmpty()) {
            throw new NotFoundException("Stage not found");
        }
        stageDto.setId(id);

        Stage savedStage = stageRepository.save(StageDto.toStage(stageDto));
        return StageDto.fromStage(savedStage);
    }

    public void deleteOne(Long id) {
        stageRepository.deleteById(id);
    }
}
