package com.serdar.licenceapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer totalLicense;
    private Integer remainingLicense;
    private Date createDate;
    private Date updateDate;
    private String createUser;
    private String updateUser;
    private Status status;
    private CreateRequestPart ceateRequestInfo;
    private CreateRequestPart ValidateUniqueInfo;
    private String privateKey;
    private String publicKey;

    @OneToMany
    private Set<Request> requests = new HashSet<>();
    @OneToMany
    private Set<User> users = new HashSet<>();
}
