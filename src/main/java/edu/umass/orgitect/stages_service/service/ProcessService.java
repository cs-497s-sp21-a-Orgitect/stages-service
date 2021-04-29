package edu.umass.orgitect.stages_service.service;

import edu.umass.orgitect.stages_service.dto.ProcessDto;
import edu.umass.orgitect.stages_service.dto.ProcessDto;
import edu.umass.orgitect.stages_service.dto.StageDto;
import edu.umass.orgitect.stages_service.entity.Process;
import edu.umass.orgitect.stages_service.entity.Stage;
import edu.umass.orgitect.stages_service.repository.ProcessRepository;
import edu.umass.orgitect.stages_service.entity.Process;

import edu.umass.orgitect.stages_service.repository.StageRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProcessService {


    @Autowired
    ProcessRepository processRepository;


    public ProcessDto fetchOneProcess(Long id) throws NotFoundException {
        Optional<Process> processOptional = processRepository.findById(id);
        if (processOptional.isEmpty()) {
            throw new NotFoundException("Process not found");
        }
        return ProcessDto.fromProcess(processOptional.get());
    }

    public Collection<ProcessDto> fetchAll() {
        return ProcessDto.toProcessDtos(processRepository.findAll());
    }

    public ProcessDto createOne(ProcessDto processDto) {

        Process process = ProcessDto.toProcess(processDto);
        process.addProcessToStages(process.getStages());
        Process savedProcesses = processRepository.save(process);
        return ProcessDto.fromProcess(savedProcesses);
    }

    public ProcessDto updateOne(Long id, ProcessDto processDto) throws NotFoundException {
        Optional<Process> processOptional = processRepository.findById(id);
        if (processOptional.isEmpty()) {
            throw new NotFoundException("Process not found");
        }
        processDto.setId(id);

        Process process = ProcessDto.toProcess(processDto);
        process.addProcessToStages(process.getStages());

        Process savedProcesses = processRepository.save(process);
        return ProcessDto.fromProcess(savedProcesses);
    }

    public void deleteOne(Long id) {
        processRepository.deleteById(id);
    }

}
