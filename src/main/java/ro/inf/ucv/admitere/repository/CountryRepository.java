package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	List<Country> findByNameIgnoreCaseContaining( String name);

}