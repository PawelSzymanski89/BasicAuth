package pl.szymanski.pawel.basicauth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String helloUser(){
        return "HELLO USER";
    }

    @RequestMapping(path = "/admin")
    public String helloAdmin(){
        return "HELLO ADMIN";
    }

}
