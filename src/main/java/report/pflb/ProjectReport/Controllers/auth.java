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
        List<employees> employees = employeesService.findAll().stream().filter(x -> (x.getEMail()+x.getPassword()).hashCode()==(request.getLogin()+request.getPassword()).hashCode()).toList();
        List<guests> guests = guestsService.findAll().stream().filter(x -> (x.getEMail()+x.getPassword()).hashCode()==(request.getLogin()+request.getPassword()).hashCode()).toList();
        if (employees.size()>0){
            employees employee = employees.get(0);
            employee.setToken(jwtService.generateToken(employee));
            employeesService.save(employee);
            return ResponseEntity.ok(AuthResponse.builder()
                    .user(employees)
                    .status(true)
                    .build());
        }else if(guests.size()>0){
            System.out.println(guests.size());
            guests guest = guests.get(0);
            guest.setToken(jwtService.generateToken(guest));
            guestsService.save(guest);
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

    @CrossOrigin
    @PostMapping("api/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        guests guests = new guests(request.getFirstName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getE_mail(),
                request.getPassword());
        guests.setToken("0");
        guestsService.save(guests);
        System.out.println(guests);
        return ResponseEntity.ok(RegisterResponse.builder().user(guests).status(true).build());
    }
}
