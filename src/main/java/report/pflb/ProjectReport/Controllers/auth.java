package report.pflb.ProjectReport.Controllers;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import report.pflb.ProjectReport.Config.JwtService;
import report.pflb.ProjectReport.Entity.employees;
import report.pflb.ProjectReport.Entity.guests;
import report.pflb.ProjectReport.service.employeesService;
import report.pflb.ProjectReport.service.guestsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class auth {
    @Autowired
    private employeesService employeesService;
    @Autowired
    private guestsService guestsService;
    @Autowired
    private JwtService jwtService;
    @CrossOrigin
    @PostMapping("/api/login")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        System.out.println(request.getLogin());
        System.out.println(request.getPassword());
        employees employees = employeesService.findAll().stream().filter(x -> (x.getEMail()+x.getPassword()).hashCode()==(request.getLogin()+request.getPassword()).hashCode()).toList().get(0);
        guests guests = guestsService.findAll().stream().filter(x -> (x.getEMail()+x.getPassword()).hashCode()==(request.getLogin()+request.getPassword()).hashCode()).toList().get(0);
        if (employees!=null){
            employees.setToken(jwtService.generateToken(employees));
            employeesService.save(employees);
            return ResponseEntity.ok(AuthResponse.builder()
                    .user(employees)
                    .status(true)
                    .build());
        }else if(guests!=null){
            guests.setToken(jwtService.generateToken(guests));
            guestsService.save(guests);
            return ResponseEntity.ok(AuthResponse.builder()
                    .user(guests)
                    .status(true)
                    .build());
        }else {
            return ResponseEntity.ok(AuthResponse.builder()
                    .user(null)
                    .status(false)
                    .build());
        }
    }
}
