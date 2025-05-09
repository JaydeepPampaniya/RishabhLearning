package com.security.spring.security1;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String greet(){
        return "hello world";
    }

    @GetMapping("/about")
    public String about(HttpServletRequest request){
        return "about"+ request.getSession().getId();

    }
}
