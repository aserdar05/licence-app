package com.serdar.licenceapp.controllers.user;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.UserConverter;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.services.UserService;
import com.serdar.licenceapp.services.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PatchMapping("/ChangePassword")
    public ResponseCommand ChangePassword(ChangePasswordCommand command){
        boolean succeeded = userService.changePassword(command);
        if(succeeded){
            log.debug("changed password for user: " + command.getUserEmail());
            return new ResponseCommand(
                    true,
                    "Password changed successfully",
                    HttpStatus.OK.value());
        }
        else{
            return new ResponseCommand(
                    false,
                    "An error occured while changing password",
                    HttpStatus.OK.value());
        }
    }

    @GetMapping("/GetUser/{id}")
    public UserCommand GetUser(@PathVariable Long id){
        User user = this.userService.getUserById(id);
        if(user == null){
            return null;
        }
        return userConverter.ConvertToUserCommand(user);
    }
}
