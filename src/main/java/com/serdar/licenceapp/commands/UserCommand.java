package com.serdar.licenceapp.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommand {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Date birthDate;
    private Long projectId;
}
