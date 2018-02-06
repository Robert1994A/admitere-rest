package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
