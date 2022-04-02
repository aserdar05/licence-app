package com.serdar.licenceapp.converter;

import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserCommand ConvertToUserCommand(User user){
        UserCommand command = new UserCommand();
        command.setUserId(user.getId());
        command.setName(user.getName());
        command.setSurname(user.getSurname());
        command.setEmail(user.getEmail());
        command.setPassword(user.getPassword());
        command.setBirthDate(user.getBirthDate());
        return command;
    }

    public User ConvertToUser(UserCommand command){
        User user = new User();
        user.setId(command.getUserId());
        user.setName(command.getName());
        user.setSurname(command.getSurname());
        user.setEmail(command.getEmail());
        user.setPassword(command.getPassword());
        user.setBirthDate(command.getBirthDate());
        user.setRole(command.getRole());
        return user;
    }
}
