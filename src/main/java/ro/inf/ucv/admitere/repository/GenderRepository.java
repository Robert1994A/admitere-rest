package ro.inf.ucv.admitere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

	Page<Gender> findByNameIgnoreCaseContaining(String search, Pageable pageable);

	Page<Gender> findAll(Pageable pageable);

}
