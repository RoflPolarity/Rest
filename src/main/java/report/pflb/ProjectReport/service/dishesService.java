package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.dishes;
import report.pflb.ProjectReport.Repository.DishesRepository;

import java.util.List;

@Service
public class dishesService {
    private final DishesRepository dishesRepository;
    @Autowired
    public dishesService(DishesRepository dishesRepository){this.dishesRepository = dishesRepository;}
    public dishes save(dishes dishes){return dishesRepository.save(dishes);}
    public List<dishes> findAll(){return dishesRepository.findAll();}
    public dishes findBuName(String name){return dishesRepository.findByName(name);}
}
