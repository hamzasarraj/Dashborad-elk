package com.vermeg.dashboard.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Application {
    @Id
    private Long id;
    private String name;
    private String status;
    private String url;
    @JsonIgnore
    @OneToMany(mappedBy = "application")
    private List<Pipeline> pipelines;
    @JsonIgnore
    @OneToMany(mappedBy = "application")
    private List<Resource> resources;
    @JsonIgnore
    @ManyToMany(mappedBy = "applications")
    private List<Users> users;
}
