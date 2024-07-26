package org.example;

import org.example.config.Config;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Objects;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        Communicado communicado = context.getBean("communicado", Communicado.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);

        String sessionId = communicado.getMethod(requestEntity);

        User user = new User(3L, "James", "Brown", -1);
        headers.add("Cookie", sessionId);
        requestEntity = new HttpEntity<>(user, headers);
        String firstKey = communicado.postMethod(requestEntity);
        System.out.println(firstKey);

        user.setName("Thomas");
        user.setLastName("Shelby");
        requestEntity = new HttpEntity<>(user, headers);
        String secondKey = communicado.putMethod(requestEntity);
        System.out.println(secondKey);

        String thirdKey = communicado.deleteMethod(requestEntity);
        System.out.println(thirdKey);

        System.out.println(firstKey + secondKey + thirdKey);
    }
}
