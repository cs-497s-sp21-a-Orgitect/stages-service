package edu.umass.orgitect.stages_service.dto;

import edu.umass.orgitect.stages_service.entity.Stage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageDto {
    private Long id;
    private String action;
    private Long duration;


    public static StageDto fromStage(Stage stage) {
        return StageDto.builder()
                .id(stage.getId())
                .action(stage.getAction())
                .duration(stage.getDuration())
                .build();
    }

    public static Stage toStage(StageDto stageDto) {
        return Stage.builder()
                .id(stageDto.getId())
                .action(stageDto.getAction())
                .duration(stageDto.getDuration())
                .build();
    }

    public static ArrayList<StageDto> toStageDtos(Collection<Stage> stages) {
        ArrayList<StageDto> stageDtos = new ArrayList<>();

        for (Stage stage : stages) {
            stageDtos.add(fromStage(stage));
        }
        return stageDtos;
    }


}
