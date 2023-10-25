package com.vermeg.dashboard.config;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class GitlabConfig {

    @Value("${gitlab.url}")
    private String url;
    @Value("${gitlab.username}")
    private String username;
    @Value("${gitlab.password}")
    private String password;


    @Bean
    public GitLabApi gitLabApi() throws GitLabApiException {
        return GitLabApi.oauth2Login(url, username, password, true);
    }


}
