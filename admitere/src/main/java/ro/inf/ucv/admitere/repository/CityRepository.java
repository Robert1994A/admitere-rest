package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.State;

@Repository
@RepositoryRestResource(path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {

	@RestResource(path = "state")
	List<City> findByState(State state);

}
