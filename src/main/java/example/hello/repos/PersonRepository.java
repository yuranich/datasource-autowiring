package example.hello.repos;

import example.hello.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yury Samarin
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
