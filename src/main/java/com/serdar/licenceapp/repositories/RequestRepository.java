package com.serdar.licenceapp.repositories;

import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends JpaRepository<Request, Long>, CrudRepository<Request, Long> {
}