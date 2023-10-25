package com.vermeg.dashboard.response;

import com.vermeg.dashboard.entities.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private Users user;

}
