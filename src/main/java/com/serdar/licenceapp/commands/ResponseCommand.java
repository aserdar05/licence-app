package com.serdar.licenceapp.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommand {
    private boolean isSuccessfull;
    private String responseMessage;
    private int responseCode;
}
