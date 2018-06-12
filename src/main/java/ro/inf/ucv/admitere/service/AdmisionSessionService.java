package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSession;
import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.repository.AdmissionSessionRepository;
import ro.inf.ucv.admitere.service.utils.StatisticsUtil;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Service
@Transactional
public class AdmisionSessionService {

	private static final Logger logger = Logger.getLogger(AdmisionSessionService.class);

	@Autowired
	private AdmissionSessionRepository admissionSessionRepository;

	@Autowired
	private AppliedSessionService appliedSessionService;

	@Autowired
	private StatisticsUtil statisticsUtil;

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
			throw e;
		}

		return savedAdmissionSession;
	}

	public AdmissionSession findById(String admissionSessionId) {
		AdmissionSession admissionSession = null;
		try {
			if (StringUtils.isNotBlank(admissionSessionId)) {
				admissionSession = this.admissionSessionRepository.findById(admissionSessionId).get();
			}
		} catch (Exception e) {
			logger.error("Find admission session by id: " + admissionSessionId, e);
		}

		return admissionSession;
	}

	public List<Statistics> getStatistics(String admissionSessionId) {
		List<Statistics> statistics = null;
		try {
			if (StringUtils.isNotBlank(admissionSessionId)) {
				AdmissionSession admissionSession = findById(admissionSessionId);
				if (admissionSession != null) {
					List<AdmissionSpecialization> admissionSpecializations = admissionSession
							.getAdmissionSpecializations();
					if (admissionSpecializations != null && !admissionSpecializations.isEmpty()) {
						List<AppliedSession> appliedSessions = this.appliedSessionService
								.findAppliedSessionsByAdmissionSpecializations(admissionSpecializations);
						if (appliedSessions != null && !appliedSessions.isEmpty()) {
							statistics = this.statisticsUtil.createAllStatistics(appliedSessions);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Get statistics for admission session id: " + admissionSessionId, e);
		}

		return statistics;
	}
}
