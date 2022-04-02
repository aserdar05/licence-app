package com.serdar.licenceapp.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Date birthDate;
    private Date createDate;
    private Date updateDate;
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
