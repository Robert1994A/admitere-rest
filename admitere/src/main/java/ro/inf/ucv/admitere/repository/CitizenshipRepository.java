package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Citizenship;

@Repository
public interface CitizenshipRepository extends JpaRepository<Citizenship, Integer> {

}
