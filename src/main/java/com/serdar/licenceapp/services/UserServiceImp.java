package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.UserConverter;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImp(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public boolean changePassword(ChangePasswordCommand command) {
        try{
            Optional<User> userOptional = userRepository.findByEmail(command.getUserEmail());
            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.setPassword(command.getPassword());
                userRepository.save(user);
            }
            else{
                throw new RuntimeException("User with email " + command.getUserEmail() + " not found!");
            }
        }catch(Exception e){
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveUser(UserCommand command) {
        try{
            User user = userConverter.ConvertToUser(command);
            user.setUpdateDate(new Date());
            if(command.getProjectId() != null){
                Project project = new Project();
                project.setId(command.getProjectId());
                user.setProject(project);
            }
            userRepository.save(user);
        }catch(Exception e){
            log.error("Error occurred", e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User GetUserById(Long userId) {
        return this.userRepository.getById(userId);
    }

    @Override
    public List<User> GetUsers(Sort sort) {
        if(sort == null){
            return this.userRepository.findAll();
        }
        else{
            return this.userRepository.findAll(sort);
        }
    }
}
