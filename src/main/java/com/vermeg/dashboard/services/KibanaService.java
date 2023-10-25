package com.vermeg.dashboard.services;



import com.vermeg.dashboard.requests.ElkLoginParamsRequestDTO;
import com.vermeg.dashboard.requests.ElkLoginRequestDTO;
import com.vermeg.dashboard.requests.ElkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KibanaService {
    private final RestTemplate restTemplate;
    private final ElkProperties elk1 = ElkProperties.builder()
            .url("http://localhost:5601")
            .username("elastic")
            .password("ftUrvD2IA63ZdEQWFNfK")
            .kbnDashboardPath("/app/dashboards#/view/51d03a10-6d31-11ee-b0e1-f75d33f45fa7?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-5d,to:now))")
            .kbnVersion("8.9.1")
            .build();

    private final Map<String, ElkProperties> userElkMap = Map.of("user1", elk1);

    private final HashMap<String, String> elkCookieMap = new HashMap<>();

    public KibanaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpEntity<String> elkLogin(ElkProperties elkProperties) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("kbn-version", elkProperties.getKbnVersion());
        headers.add("kbn-xsrf", "true");

        String authUrl = elkProperties.getUrl() + "/internal/security/login";
        ElkLoginRequestDTO body = new ElkLoginRequestDTO();
        body.setParams(new ElkLoginParamsRequestDTO(elkProperties.getUsername(), elkProperties.getPassword()));

        try {
            return restTemplate.exchange(
                    authUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    String.class
            );

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public String getElkCookie(String username) {
        try {
            if (elkCookieMap.containsKey(username)) {
                return elkCookieMap.get(username);
            }
            HttpEntity<String> httpEntity = elkLogin(userElkMap.get(username));

            String cookie = httpEntity.getHeaders().getFirst("Set-Cookie");
            elkCookieMap.put(username, cookie);
            return cookie;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public ResponseEntity<String> getDashboard() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", getElkCookie("user1"));
        return new ResponseEntity<>(userElkMap.get("user1").getKbnDashboardPath(),headers, HttpStatus.OK);
    }

}