package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.tables;
import report.pflb.ProjectReport.Repository.tablesRepository;

import java.util.List;

@Service
public class tablesService {
    @Autowired
    private tablesRepository tablesRepository;

    public List<tables> findAll() {return tablesRepository.findAll();}

    public tables findByNumber(int number) {return tablesRepository.findByNumber(number);}

    public tables save(tables tables) {return tablesRepository.save(tables);}
}
