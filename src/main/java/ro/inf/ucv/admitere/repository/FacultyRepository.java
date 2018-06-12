package ro.inf.ucv.admitere.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.inf.ucv.admitere.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

	Page<Faculty> findByNameOrUrlOrDescriptionAllIgnoreCaseContaining(String search, String search2, Pageable pageable);

}
