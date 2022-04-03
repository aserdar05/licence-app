package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ChangePasswordCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.UserConverter;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.exception.UserNotFoundException;
import com.serdar.licenceapp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    //@Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImp(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    @CachePut(value="userCache", key="#userId")
    public boolean changePassword(ChangePasswordCommand command) {
        try{
            Optional<User> userOptional = userRepository.findByEmail(command.getUserEmail());
            if(userOptional.isPresent()){
                User user = userOptional.get();
                user.setPassword(passwordEncoder.encode(command.getPassword()));
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
    @CachePut(value="userCache", key="'id=' + #userId")
    public boolean saveUser(UserCommand command) {
        log.info(command.getEmail() + "--" + command.getName() + "--" + command.getSurname());
        try{
            Long userId = command.getUserId();
            User user = null;
            if(userId == null){
                user = userConverter.ConvertToUser(command);
                user.setCreateDate(new Date());
            }else{
                user = userRepository.findById(userId).
                        orElseThrow(() -> new UserNotFoundException("User not found"));
                user.setEmail(command.getEmail());
                user.setPassword(command.getPassword());
                user.setName(command.getName());
                user.setSurname(command.getSurname());
                user.setRole(command.getRole());
            }

            user.setUpdateDate(new Date());
            if(command.getProjectId() != null){
                Project project = new Project();
                project.setId(command.getProjectId());
                user.setProject(project);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }catch(Exception e){
            log.error("Error occurred", e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @CacheEvict(value="userCache", key="#userId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple entires to delete
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        userRepository.delete(user);
    }

    @Override
    @Cacheable(value = "userCache", key="#userId")
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    @Cacheable(value = "userCache", key="#email")
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Cacheable(value="userCache")
    public List<User> getUsers(Sort sort) {
        if(sort == null){
            return this.userRepository.findAll();
        }
        else{
            return this.userRepository.findAll(sort);
        }
    }
}
