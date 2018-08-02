package example.hello.repos;

import example.hello.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yury Samarin
 */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
