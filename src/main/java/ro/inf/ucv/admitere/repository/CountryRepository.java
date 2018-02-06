package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Country;

@Repository
@RepositoryRestResource(path = "countries")
public interface CountryRepository extends JpaRepository<Country, Long> {

	@RestResource(path = "name")
	List<Country> findByNameIgnoreCaseContaining(@Param("name") String name);

}