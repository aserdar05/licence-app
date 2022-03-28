package com.serdar.licenceapp.controllers.user;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.UserConverter;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.services.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    private final UserServiceImp userService;
    private final UserConverter userConverter;

    public UserController(UserServiceImp userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/ChangePassword")
    public ResponseCommand ChangePassword(ChangePasswordCommand command){
        boolean succeeded = userService.changePassword(command);
        if(succeeded){
            log.debug("changed password for user: " + command.getUserEmail());
            return new ResponseCommand(true, "Password changed successfully");
        }
        else{
            return new ResponseCommand(false, "An error occured while changing password");
        }
    }

    @GetMapping("/GetUser/{id}")
    public UserCommand GetUser(@PathVariable Long id){
        User user = this.userService.GetUserById(id);
        if(user == null){
            return null;
        }
        return userConverter.ConvertToUserCommand(user);
    }

    @PostMapping("/SaveUser")
    public ResponseCommand saveUser(@RequestBody UserCommand userCommand){
        boolean succeeded = this.userService.saveUser(userCommand);
        ResponseCommand command = new ResponseCommand();
        command.setSuccessfull(succeeded);
        if(succeeded){
            command.setResponseMessage("User '" + userCommand.getEmail() + "' saved successfully");
        }else{
            command.setResponseMessage("User could not be saved");
        }
        return command;
    }
}
