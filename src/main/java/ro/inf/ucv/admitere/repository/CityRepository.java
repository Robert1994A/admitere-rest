package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.City;
import ro.inf.ucv.admitere.entity.State;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findByState(State state);

}
