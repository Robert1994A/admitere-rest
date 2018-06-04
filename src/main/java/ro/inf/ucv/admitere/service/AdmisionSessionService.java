package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.repository.AdmissionSessionRepository;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Service
@Transactional
public class AdmisionSessionService {

	private static final Logger logger = Logger.getLogger(AdmisionSessionService.class);

	@Autowired
	private AdmissionSessionRepository admissionSessionRepository;

	public List<AdmissionSession> findAll() {
		List<AdmissionSession> admissionSessions = null;
		try {
			admissionSessions = admissionSessionRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all addmision sessions: ", e);
		}
		return admissionSessions;
	}

	public AdmissionSession save(AdmissionSession admissionSession, boolean flush) {
		AdmissionSession savedAdmissionSession = null;
		try {
			if (admissionSession != null) {
				if (flush) {
					savedAdmissionSession = admissionSessionRepository.saveAndFlush(admissionSession);
				} else {
					savedAdmissionSession = admissionSessionRepository.save(admissionSession);
				}
			}
		} catch (Exception e) {
			logger.error("Save addmision session: " + admissionSession, e);
		}
		return savedAdmissionSession;
	}

	public List<Statistics> getStatistics(String admissionSessionId) {
		List<Statistics> stats = null;
		// TODO: Implement this.
		return stats;
	}
}
