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
import com.example.Service.Model.bookModel;

@RestController
public class BookService {
    static String itemId;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/book/post")
    public bookModel postUser(){
        bookModel newItem = new bookModel();
        newItem.setName("NewServiceBook");
        newItem.setAuthor("newServiceAuthor");
        newItem.setCategory("Fiction");
        newItem.setCopies(1000);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<bookModel> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<bookModel> response = restTemplate.postForEntity("http://localhost:9090/book/", request, bookModel.class);
       System.out.println(response.getBody()+"555555555555555555555555");
        itemId = response.getBody().getId();
        return response.getBody();
    }
    @RequestMapping(value = "/book/getAll")
    public Object getProductList() {
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       HttpEntity <Object> entity = new HttpEntity<Object>(headers);
       System.out.println(restTemplate.exchange("http://localhost:9090/book/", HttpMethod.GET, entity, String.class).getBody());
       return restTemplate.exchange("http://localhost:9090/book/", HttpMethod.GET, entity, Object.class).getBody();
    }

    @RequestMapping(value = "/book/getById")
    public Object getById(){
        ResponseEntity<bookModel> response = restTemplate.exchange("http://localhost:9090/book/" + itemId, HttpMethod.GET, null,
        new ParameterizedTypeReference<bookModel>() {
        });
System.out.println(response.getBody()+"6666666666666666666666");
        return response.getBody();
    }
    @RequestMapping(value = "/book/updateById")
    public ResponseEntity<String> updateById(){
        bookModel newItem = new bookModel();
        newItem.setName("NewServiceBook");
        newItem.setAuthor("newUpdatedServiceAuthor");
        newItem.setCategory("Fiction");
        newItem.setCopies(1000);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<bookModel> request = new HttpEntity<>(newItem, headers);
    ResponseEntity<String> response = restTemplate.exchange("http://localhost:9090/book/" + itemId, HttpMethod.PUT, request,
            new ParameterizedTypeReference<String>() {
            });
            System.out.println(response+"777777777777777777777777");
            return response;
    }

    @RequestMapping(value = "/book/deleteById")
    public ResponseEntity<String> deleteById(){
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:9090/book/" + itemId, HttpMethod.DELETE, null,
        new ParameterizedTypeReference<String>() {
        });
        System.out.println(response+"88888888888888888888888888888");
        return response;
    }

}
