package com.serdar.licenceapp.commands;

import com.serdar.licenceapp.domain.CreateRequestPart;
import com.serdar.licenceapp.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCommand {
    private Long projectId;
    private String name;
    private String description;
    private Integer totalLicense;
    private Integer remainingLicense;
    private Status status;
    private CreateRequestPart ceateRequestInfo;
    private CreateRequestPart ValidateUniqueInfo;
    private String privateKey;
    private String publicKey;
}
