package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.user;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    user findByUsername(String username);
    List<user> findAll();
}
