package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.employees;
import report.pflb.ProjectReport.Repository.employeesRepository;

import java.util.List;

@Service
public class employeesService {
    @Autowired
    private employeesRepository employeesRepository;

    public employees save(employees employees){return employeesRepository.save(employees);}
    public employees findByFirstName(String lastName){return employeesRepository.findByLastName(lastName);}
    public List<employees> findByPosition(String position){return employeesRepository.findByPosition(position);}
    public List<employees> findAll(){return employeesRepository.findAll();}
    public employees findByE_mail(String e_mail){return employeesRepository.findByEMail(e_mail);}
}
