package com.vermeg.dashboard.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElkProperties {
    private String url;
    private String kbnVersion;
    private String kbnDashboardPath;
    private String username;
    private String password;
}