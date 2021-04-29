package edu.umass.orgitect.stages_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    @ManyToOne
    @JoinColumn(name = "current_stage_id")
    private Stage currentStage;

    private Long customerId;

    private Long actorId;


}
