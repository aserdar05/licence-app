package com.serdar.licenceapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordCommand {
    private String userEmail;
    private String password;
    private String passwordConfirm;
}
