package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.SpecializationSample;

@Repository
public interface SpecializationSampleRepository extends JpaRepository<SpecializationSample, Long> {

}
