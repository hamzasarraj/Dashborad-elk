package com.vermeg.dashboard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pipeline {
    @Id
    private Long id;
    private String status;
    private Date createdAt;
    private Date sartedAt;
    private Date finishedAt;
    private Integer duration;
    @ManyToOne
    private Application application;
    @JsonIgnore
    @OneToMany(mappedBy = "pipeline")
    private List<Job> jobs;
}
