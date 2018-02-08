package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Country;
import ro.inf.ucv.admitere.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	List<State> findByCountry(Country country);

}
