package com.vermeg.dashboard.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElkLoginRequestDTO {
    private String providerType = "basic";
    private String providerName = "basic";
    private String currentURL = "/";
    private ElkLoginParamsRequestDTO params;
}