package com.serdar.licenceapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestKey;
    private Date requestDate;
    private String requestOwner;
    private Date createDate;
    private Date updateDate;
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

    @ManyToOne
    private Project project;
    @ManyToOne
    private User createUser;
}
