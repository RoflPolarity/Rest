package report.pflb.ProjectReport.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import report.pflb.ProjectReport.Entity.dishes;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface DishesRepository extends JpaRepository<dishes,Integer> {
    dishes save(dishes dishes);
    dishes findByName(String name);
    List<dishes> findAll();
}
