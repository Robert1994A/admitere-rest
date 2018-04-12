package ro.inf.ucv.admitere.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.AlreadyAppliedException;
import ro.inf.ucv.admitere.exceptions.NotAuthenticatedException;
import ro.inf.ucv.admitere.repository.AppliedSessionRepository;
import ro.inf.ucv.admitere.utils.ListUtils;

@Service
@Transactional
public class AppliedSessionService {

	private static final Logger logger = Logger.getLogger(AppliedSessionService.class);

	@Autowired
	private AppliedSessionRepository appliedSessionRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private AdmissionSpecializationService admissionSpecializationService;

	public List<AppliedSession> findAll() {
		List<AppliedSession> appliedSessions = null;
		try {
			appliedSessions = appliedSessionRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all applied sessions: ", e);
		}
		return appliedSessions;
	}

	public AppliedSession save(AppliedSession appliedSession, boolean flush) {
		AppliedSession savedAppliedSession = null;
		try {
			if (appliedSession != null) {
				if (flush) {
					savedAppliedSession = appliedSessionRepository.saveAndFlush(appliedSession);
				} else {
					savedAppliedSession = appliedSessionRepository.save(appliedSession);
				}
			}
		} catch (Exception e) {
			logger.error("Save addmision session: " + appliedSession, e);
		}
		return savedAppliedSession;
	}

	public void applyAtAdmissionSession(String admissionSpecializationId, String name)
			throws AlreadyAppliedException, NotAuthenticatedException {
		User authenticatedUser = userService.findByUsername(name);
		if (authenticatedUser != null) {
			AdmissionSpecialization admissionSpecialization = admissionSpecializationService
					.findById(admissionSpecializationId);
			if (admissionSpecialization != null) {
				AppliedSession appliedSession = appliedSessionRepository
						.findByUserAndAdmissionSpecialization(authenticatedUser, admissionSpecialization);
				if (appliedSession != null) {
					throw new AlreadyAppliedException();
				} else {
					AppliedSession sessionToSave = new AppliedSession();
					sessionToSave.setAdmissionSpecialization(admissionSpecialization);
					sessionToSave.setUser(authenticatedUser);
					sessionToSave = this.save(sessionToSave, true);
					List<AppliedSession> appliedSessions = authenticatedUser.getAppliedSessions();
					if (ListUtils.isEmpty(appliedSessions)) {
						appliedSessions = new ArrayList<AppliedSession>();
					}
					appliedSessions.add(sessionToSave);
					this.userService.save(authenticatedUser, false);
				}
			}
		} else {
			throw new NotAuthenticatedException();
		}
	}
}
