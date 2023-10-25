package com.vermeg.dashboard.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElkLoginParamsRequestDTO {
    private String username;
    private String password;
}