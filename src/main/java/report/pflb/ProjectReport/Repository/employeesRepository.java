package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.employees;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface employeesRepository extends JpaRepository<employees,Integer> {
    employees save(employees employees);
    employees findByLastName(String last_name);
    employees findByEMail(String e_mail);
    List<employees> findByPosition(String position);
    List<employees> findAll();
}
