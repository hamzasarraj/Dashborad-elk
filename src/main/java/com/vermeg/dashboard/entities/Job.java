package com.vermeg.dashboard.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Job {

    @Id
    private Long id;
    private String name;
    private Date createdAt;
    private Date startedAt;
    private Date finishedAt;
    private Float duration;
    private  String status;
    private String stage;

    @ManyToOne
    private Pipeline pipeline;
}
