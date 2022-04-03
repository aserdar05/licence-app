package com.serdar.licenceapp.controllers;

import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.security.jwt.JwtTokenProvider;
import com.serdar.licenceapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/home")
@CrossOrigin(origins = "*")
public class HomeController {
    private final UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public HomeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        //principal = httpServletRequest.getUserPrincipal.
        if(principal == null){
            //logout will also use here so we should return ok http status.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;
        User user = userService.getUserByEmail(authenticationToken.getName());
        user.setToken(tokenProvider.generateToken(authenticationToken));

        ResponseCommand response = new ResponseCommand();
        response.setSuccessfull(true);
        response.setResponseMessage("User logged in successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseCommand signup(@RequestBody UserCommand userInfo){
        boolean succeeded = this.userService.saveUser(userInfo);
        ResponseCommand command = new ResponseCommand();
        command.setSuccessfull(succeeded);
        if(succeeded){
            command.setResponseMessage("User '" + userInfo.getEmail() + "' saved successfully");
        }else{
            command.setResponseMessage("User could not be saved");
        }
        return command;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseCommand logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseCommand responseCommand = new ResponseCommand();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            responseCommand.setSuccessfull(true);
            responseCommand.setResponseMessage("User logged out successfully");
        }
        else{
            responseCommand.setSuccessfull(false);
            responseCommand.setResponseMessage("No authenticated user to logout");
        }
        return responseCommand;
    }
}
