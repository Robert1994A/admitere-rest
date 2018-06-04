package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.entity.User;

@Repository
public interface AppliedSessionRepository extends JpaRepository<AppliedSession, String> {

	AppliedSession findByUserAndAdmissionSpecialization(User user, AdmissionSpecialization admissionSpecialization);

	List<AppliedSession> findByUser(User user, Sort sort);

	List<AppliedSession> findByAdmissionSpecialization(AdmissionSpecialization admissionSpecialization);

}
