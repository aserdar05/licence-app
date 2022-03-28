package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.domain.User;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean changePassword(ChangePasswordCommand command);
    boolean saveUser(UserCommand command);
    User GetUserById(Long userId);
    List<User> GetUsers(Sort sort);
    void deleteUser(Long userId);
}
