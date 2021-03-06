package edu.umass.orgitect.stages_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Stage {

    @Id
    @GeneratedValue
    private Long id;

    private String action;

    private Integer ordering;

    private Long duration;

    @ManyToOne
    @JoinColumn (name = "process_id", nullable = false)
    @JsonIgnore
    private Process process;

}
