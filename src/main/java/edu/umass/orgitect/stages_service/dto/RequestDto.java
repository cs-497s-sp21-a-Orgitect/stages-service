package edu.umass.orgitect.stages_service.dto;

import edu.umass.orgitect.stages_service.entity.Process;
import edu.umass.orgitect.stages_service.entity.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {

    private Long id;
    private ProcessDto process;
    private Long customerId;
    private Long actorId;
    private StageDto currentStage;


    public static RequestDto fromRequest(Request request) {
        return RequestDto.builder()
                .id(request.getId())
                .actorId(request.getActorId())
                .customerId(request.getCustomerId())
                .currentStage(request.getCurrentStage() != null ? StageDto.fromStage(request.getCurrentStage()) : null)
                .process(ProcessDto.fromProcess(request.getProcess()))
                .build();
    }

    public static Request toRequest(RequestDto requestDto) {
        return Request.builder()
                .id(requestDto.getId())
                .actorId(requestDto.getActorId())
                .customerId(requestDto.getCustomerId())
                .currentStage(requestDto.getCurrentStage() != null ? StageDto.toStage(requestDto.getCurrentStage()) : null)
                .process(ProcessDto.toProcess(requestDto.getProcess()))
                .build();
    }

    public static ArrayList<RequestDto> toRequestDto(Collection<Request> requests) {
        ArrayList<RequestDto> requestDtos = new ArrayList<>();

        for (Request request : requests) {
            requestDtos.add(fromRequest(request));
        }
        return requestDtos;
    }
}
