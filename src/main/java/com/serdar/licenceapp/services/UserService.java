package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    boolean changePassword(ChangePasswordCommand command);
    boolean saveUser(UserCommand command);
    User getUserById(Long userId);
    User getUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);
    List<User> getUsers(Sort sort);
    void deleteUser(Long userId);
}
