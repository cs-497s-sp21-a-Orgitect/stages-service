package edu.umass.orgitect.stages_service.dto;


import edu.umass.orgitect.stages_service.entity.Process;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDto {

    private Long id;
    private String name;
    private String description;
    private List<StageDto> stages;


    public static ProcessDto fromProcess(Process process) {
        return ProcessDto.builder()
                .id(process.getId())
                .name(process.getName())
                .description(process.getDescription())
                .stages(
                        process.getStages().stream()
                                .map(StageDto::fromStage)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static Process toProcess(ProcessDto processDto) {
        return Process.builder()
                .id(processDto.getId())
                .name(processDto.getName())
                .description(processDto.getDescription())
                .stages(
                        Optional.ofNullable(processDto.getStages())
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .map(StageDto::toStage)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static ArrayList<ProcessDto> toProcessDtos(Collection<Process> processes) {
        ArrayList<ProcessDto> processDtos = new ArrayList<>();

        for (Process stage : processes) {
            processDtos.add(fromProcess(stage));
        }
        return processDtos;
    }
}
