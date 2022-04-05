package com.serdar.licenceapp.repositories;

import com.serdar.licenceapp.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long>, CrudRepository<Project, Long> {
    public Optional<Project> findById(Long id);
}
