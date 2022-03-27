package com.serdar.licenceapp.converter;

import com.serdar.licenceapp.commands.RequestCommand;
import com.serdar.licenceapp.domain.Request;
import org.springframework.stereotype.Component;

@Component
public class RequestConverter {
    public RequestCommand convertToRequestCommand(Request request){
        RequestCommand command = new RequestCommand();
        command.setId(request.getId());
        command.setIsDemoRequest(request.isIsDemoRequest());
        command.setRequestDate(request.getRequestDate());
        command.setRequestKey(request.getRequestKey());
        command.setDeleteReason(request.getDeleteReason());
        command.setRequestOwner(request.getRequestOwner());
        command.setLicense(request.getLicense());
        command.setIdentifyingNumber(request.getIdentifyingNumber());
        command.setMacId(request.getMacId());
        command.setMotherboardSerial(request.getMotherboardSerial());
        command.setDeleteReason(request.getDeleteReason());
        command.setIsDemoRequest(request.isIsDemoRequest());
        command.setSpecialCode(request.getSpecialCode());
        command.setStatus(request.getStatus());
        command.setSystemCreationTime(request.getSystemCreationTime());
        command.setSystemUUID(request.getSystemUUID());

        return command;
    }

    public Request convertToRequest(RequestCommand command){
        Request request = new Request();
        request.setId(command.getId());
        request.setIsDemoRequest(command.isIsDemoRequest());
        request.setRequestDate(command.getRequestDate());
        request.setRequestKey(command.getRequestKey());
        request.setDeleteReason(command.getDeleteReason());
        request.setRequestOwner(command.getRequestOwner());
        request.setLicense(command.getLicense());
        request.setIdentifyingNumber(command.getIdentifyingNumber());
        request.setMacId(command.getMacId());
        request.setMotherboardSerial(command.getMotherboardSerial());
        request.setDeleteReason(command.getDeleteReason());
        request.setIsDemoRequest(command.isIsDemoRequest());
        request.setSpecialCode(command.getSpecialCode());
        request.setStatus(command.getStatus());
        request.setSystemCreationTime(command.getSystemCreationTime());
        request.setSystemUUID(command.getSystemUUID());

        return request;
    }
}
