package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.State;

@Repository
@RepositoryRestResource(path = "states")
public interface StateRepository extends JpaRepository<State, Long> {

	@RestResource(path = "country")
	List<State> findByCountry(Country country);

}
