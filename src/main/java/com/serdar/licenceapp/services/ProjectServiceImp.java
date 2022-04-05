package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.ProjectCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.ProjectConverter;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.exception.UserNotFoundException;
import com.serdar.licenceapp.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class ProjectServiceImp implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectConverter projectConverter;

    @Override
    @Transactional
    public boolean saveProject(ProjectCommand command) {
        try{
            Long projectId = command.getProjectId();
            Project project = null;
            if(projectId == null){
                project = projectConverter.ConvertToProject(command);
                project.setCreateDate(new Date());
            }else{
                project = projectRepository.findById(projectId).
                        orElseThrow(() -> new UserNotFoundException("Project not found"));
                project.setCeateRequestInfo(command.getCeateRequestInfo());
                project.setStatus(command.getStatus());
                project.setName(command.getName());
                project.setDescription(command.getDescription());
                project.setRemainingLicense(command.getRemainingLicense());
                project.setTotalLicense(command.getTotalLicense());
                project.setValidateUniqueInfo(command.getValidateUniqueInfo());
            }

            project.setUpdateDate(new Date());
            projectRepository.save(project);
            log.info("New project saved." + command.getName() + "--" + command.getDescription());
        }catch(Exception e){
            log.error("Error occurred", e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Project getProjectById(Long projectId)
    {
        return this.projectRepository.findById(projectId).orElse(null);
    }


}
