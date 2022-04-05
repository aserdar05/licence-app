package com.serdar.licenceapp.converter;

import com.serdar.licenceapp.commands.ProjectCommand;
import com.serdar.licenceapp.domain.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public Project convertToProject(ProjectCommand command){
        Project project = new Project();
        project.setId(command.getProjectId());
        project.setName(command.getName());
        project.setCeateRequestInfo(command.getCeateRequestInfo());
        project.setStatus(command.getStatus());
        project.setDescription(command.getDescription());
        project.setPrivateKey(command.getPrivateKey());
        project.setPublicKey(command.getPublicKey());
        project.setRemainingLicense(command.getRemainingLicense());
        project.setTotalLicense(command.getTotalLicense());
        project.setValidateUniqueInfo(command.getValidateUniqueInfo());
        return  project;
    }

    public ProjectCommand convertToProjectCommand(Project project){
        ProjectCommand projectCommand = new ProjectCommand();
        projectCommand.setProjectId(project.getId());
        projectCommand.setName(project.getName());
        projectCommand.setCeateRequestInfo(project.getCeateRequestInfo());
        projectCommand.setStatus(project.getStatus());
        projectCommand.setDescription(project.getDescription());
        projectCommand.setPrivateKey(project.getPrivateKey());
        projectCommand.setPublicKey(project.getPublicKey());
        projectCommand.setRemainingLicense(project.getRemainingLicense());
        projectCommand.setTotalLicense(project.getTotalLicense());
        projectCommand.setValidateUniqueInfo(project.getValidateUniqueInfo());
        return  projectCommand;
    }
}
