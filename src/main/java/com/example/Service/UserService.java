package com.example.Service;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Service.Model.userModel;

@RestController
public class UserService {
static String itemId;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/user/post")
    public userModel postUser(){
        userModel newItem = new userModel();
        newItem.setFirstName("NewUser");
        newItem.setLastName("last name");
        newItem.setEmail("service@gmail.com");
        newItem.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<userModel> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<userModel> response = restTemplate.postForEntity("http://localhost:9090/person/", request, userModel.class);
       System.out.println(response.getBody()+"1111111111111111111111111111");
        itemId = response.getBody().getId();
        return response.getBody();
    }
    @RequestMapping(value = "/user/getAll")
    public Object getProductList() {
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       HttpEntity <Object> entity = new HttpEntity<Object>(headers);
       System.out.println(restTemplate.exchange("http://localhost:9090/person/", HttpMethod.GET, entity, String.class).getBody());
       return restTemplate.exchange("http://localhost:9090/person/", HttpMethod.GET, entity, Object.class).getBody();
    }

    @RequestMapping(value = "/user/getById")
    public Object getById(){
        ResponseEntity<userModel> response = restTemplate.exchange("http://localhost:9090/person/" + itemId, HttpMethod.GET, null,
        new ParameterizedTypeReference<userModel>() {
        });
System.out.println(response.getBody()+"2222222222222222222222222");
        return response.getBody();
    }
    @RequestMapping(value = "/user/updateById")
    public ResponseEntity<String> updateById(){
    userModel newItem = new userModel();
    newItem.setFirstName("test user");
    newItem.setLastName("last name");
    newItem.setEmail("service@gmail.com");
    newItem.setPassword("password");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<userModel> request = new HttpEntity<>(newItem, headers);
    ResponseEntity<String> response = restTemplate.exchange("http://localhost:9090/person/" + itemId, HttpMethod.PUT, request,
            new ParameterizedTypeReference<String>() {
            });
            System.out.println(response+"3333333333333333333333333333333333333");
            return response;
    }

    @RequestMapping(value = "/user/deleteById")
    public ResponseEntity<String> deleteById(){
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:9090/person/" + itemId, HttpMethod.DELETE, null,
        new ParameterizedTypeReference<String>() {
        });
        System.out.println(response+"4444444444444444444444444444444444444444");
        return response;
    }

}
   
