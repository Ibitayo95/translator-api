package com.project.translatorapi.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hello")
public class Hello {

    public RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String greetUser() {
        return "Hello user";
    }

    @GetMapping("/{userName}")
    public String superGreeting(@PathVariable String userName) {
        return "Hello " + userName;
    }

    @GetMapping("/getTestData")
    private Object[] getTestData() {
        String uri = "https://jsonplaceholder.typicode.com/posts/";
        return restTemplate.getForObject(uri, Object[].class);
    }



}
