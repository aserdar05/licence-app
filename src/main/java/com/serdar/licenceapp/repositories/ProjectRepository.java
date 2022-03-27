package com.serdar.licenceapp.repositories;

import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, CrudRepository<Project, Long> {
}
