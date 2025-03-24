package demo.JsonWebToken.Service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.JsonWebToken.Entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByUsername(String username);

}
