package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ProjectCommand;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;

public interface ProjectService {
    boolean saveProject(ProjectCommand command);
    Project getProjectById(Long projectId);
}
