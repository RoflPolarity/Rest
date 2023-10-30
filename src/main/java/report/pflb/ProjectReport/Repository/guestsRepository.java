package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.guests;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface guestsRepository extends JpaRepository<guests, Integer> {
    guests save(guests guests);
    guests findByPhoneNumber(String phoneNumber);
    guests findByEMail(String e_mail);
    List<guests> findAll();
}
