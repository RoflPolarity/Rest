package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.tables;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface tablesRepository extends JpaRepository<tables,Integer> {
    List<tables> findAll();
    tables findByNumber(int number);
    tables save(tables tables);
}
