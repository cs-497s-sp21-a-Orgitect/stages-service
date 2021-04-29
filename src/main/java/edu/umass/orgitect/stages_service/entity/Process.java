package edu.umass.orgitect.stages_service.entity;


import edu.umass.orgitect.stages_service.dto.ProcessDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Process {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Stage> stages;

    public void addProcessToStages(List<Stage> stages) {
        for (Stage stage : stages) {
            stage.setProcess(this);
        }
    }


    public Stage removeStage(Stage stage ) {
        getStages().remove(stage);
        stage.setProcess(null);

        return stage;
    }

}
