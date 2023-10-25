package com.vermeg.dashboard.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Resource {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;

    @ManyToOne
    private Application application;
}
