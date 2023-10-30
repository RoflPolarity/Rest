package report.pflb.ProjectReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.pflb.ProjectReport.Entity.guests;
import report.pflb.ProjectReport.Repository.guestsRepository;

import java.util.List;

@Service
public class guestsService {
    @Autowired
    private guestsRepository guestsRepository;

    public guests save(guests guests){return guestsRepository.save(guests);}
    public guests findByPhoneNumber(String phone){return guestsRepository.findByPhoneNumber(phone);}
    public guests findByE_mail(String e_mail){return guestsRepository.findByEMail(e_mail);}
    public List<guests> findAll(){return guestsRepository.findAll();}
}
