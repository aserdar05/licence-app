package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.RequestCommand;
import com.serdar.licenceapp.commands.ResponseCommand;

public interface RequestService {
    ResponseCommand SaveRequest(RequestCommand command);
}
