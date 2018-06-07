package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.State;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findByState(State state);

	Page<City> findAll(Pageable pageable);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void delete(City entity);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteAll();

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void deleteAll(Iterable<? extends City> entities);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	<S extends City> S save(S entity);
}
