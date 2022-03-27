package com.serdar.licenceapp.controllers;

import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.commands.UserCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/home", "/", ""})
public class HomeController {
    @RequestMapping("/login")
    public ResponseCommand login(UserCommand userInfo){
        ResponseCommand response = new ResponseCommand();

        response.setSuccessfull(true);
        response.setResponseMessage("User logged in successfully");
        return response;
    }

    @RequestMapping("/signup")
    public ResponseCommand signup(UserCommand userInfo){
        ResponseCommand response = new ResponseCommand();

        response.setSuccessfull(true);
        response.setResponseMessage("User signed up successfully");
        return response;
    }

    @RequestMapping("/logout")
    public ResponseCommand logout(Long userId){
        ResponseCommand response = new ResponseCommand();

        response.setSuccessfull(true);
        response.setResponseMessage("User logged out successfully");
        return response;
    }
}
