package com.serdar.licenceapp.services;

import com.serdar.licenceapp.commands.RequestCommand;
import com.serdar.licenceapp.commands.ResponseCommand;
import com.serdar.licenceapp.converter.RequestConverter;
import com.serdar.licenceapp.domain.Project;
import com.serdar.licenceapp.domain.Request;
import com.serdar.licenceapp.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class RequestServiceImp implements RequestService{
    private final RequestConverter requestConverter;
    private final ProjectRepository projectRepository;

    public RequestServiceImp(RequestConverter requestConverter, ProjectRepository projectRepository) {
        this.requestConverter = requestConverter;
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseCommand SaveRequest(RequestCommand command) {
        Date now = new Date();

        Request request = requestConverter.convertToRequest(command);
        Project project =  projectRepository.getById(command.getProjectId());
//        ProjectVM projectVM = OperationContext.GetProjectInstance().Get(requestVM.ProjectId).ToProjectVM();
//
//        request.Id = OperationContext.GetRequestInstance().GetMaxId() + 1;
//        request.CreateDate = now;
//        request.RequestDate = now;
//        request.Status = (int)Enums.Status.Active;
//        //request.RequestOwner = projectVM.ProjectName + "-" + requestNo.ToString();
//        try
//        {
//            request.License = CreateLicense(request, requestNo, projectVM.PrivateKey);
//        }
//        catch (Exception e)
//        {
//            string requestStr = JsonConvert.SerializeObject(request);//TODO: bu kısım test edilecek
//            _logger.Error($"Lisans oluşturulurken hata oluştu. Request nesnesi : {requestStr}");
//            throw e;
//        }
//
//        OperationContext.GetRequestInstance().SaveRequest(request);
//        _logger.Info($"{projectVM.ProjectName} projesi için yeni " + (requestVM.IsDemoRequest ? "demo lisansı" : "lisans" + " oluşturuldu."));
//
//        return new ExecuteResult
//        {
//            Succeeded = true,
//                    ResultMessage = request.RequestOwner + " için " + (requestVM.IsDemoRequest ? "demo lisansı" : "lisans") + " oluşturuldu." + (requestVM.IsDemoRequest ? "Lisans proje kotasından düşülmedi." : ""),
//                    Data = request.Id.ToString()
//        };
        return null;
    }
}
