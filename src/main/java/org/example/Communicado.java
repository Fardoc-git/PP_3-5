package org.example;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class Communicado {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";

//    get
    public String getMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
                , List.class);
        List users = responseEntity.getBody();
        System.out.println(users);
        responseEntity.getHeaders().get("Set-Cookie").forEach(System.out::println);

        HttpHeaders responseHeaders =  responseEntity.getHeaders();
        return responseEntity.getHeaders().getFirst("Set-Cookie");
    }

//    post
    public String postMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

//    put
    public String putMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);

        return responseEntity.getBody();
    }

//    delete
    public String deleteMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + requestEntity.getBody().getId()
                , HttpMethod.DELETE, requestEntity, String.class);

        return responseEntity.getBody();
    }
}
