package com.serdar.licenceapp.controllers.admin;

import com.serdar.licenceapp.commands.ProjectCommand;
import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.commands.UserCommand;
import com.serdar.licenceapp.converter.ProjectConverter;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.User;
import com.serdar.licenceapp.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectConverter projectConverter;

    @PostMapping("/saveProject")
    public ResponseCommand saveProject(@RequestBody ProjectCommand projectCommand){
        boolean succeeded = this.projectService.saveProject(projectCommand);
        ResponseCommand command = new ResponseCommand();
        command.setSuccessfull(succeeded);
        if(succeeded){
            command.setResponseMessage("Project '" + projectCommand.getName() + "' saved successfully");
        }else{
            command.setResponseMessage("Project could not be saved");
        }
        return command;
    }

    @GetMapping("/GetProject/{id}")
    public ProjectCommand getProject(@PathVariable Long id){
        Project project = this.projectService.getProjectById(id);
        if(project == null){
            return null;
        }
        return projectConverter.convertToProjectCommand(project);
    }
}
