package com.serdar.licenceapp.commands;

import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.Status;
import com.serdar.licenceapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RequestCommand {
    private Long id;
    private String requestKey;
    private Date requestDate;
    private String requestOwner;
    private Status status;
    public String License;
    public String MacId;
    public String MotherboardSerial;
    public String SystemCreationTime;
    public String IdentifyingNumber;
    public String SystemUUID;
    public String SpecialCode;
    public boolean IsDemoRequest;
    public String DeleteReason;

    private Long projectId;
    private Long createdUserId;
}
