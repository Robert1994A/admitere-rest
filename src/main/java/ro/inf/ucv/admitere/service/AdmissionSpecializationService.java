package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.repository.AdmissionSpecializationRepository;
import ro.inf.ucv.admitere.service.utils.StatisticsUtil;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Service
@Transactional
public class AdmissionSpecializationService {

	private static final Logger logger = Logger.getLogger(AdmissionSpecializationService.class);

	@Autowired
	private AdmissionSpecializationRepository admissionSpecializationRepository;

	@Autowired
	private AppliedSessionService appliedSessionService;

	@Autowired
	private StatisticsUtil statisticsUtil;

	public List<AdmissionSpecialization> findAll() {
		List<AdmissionSpecialization> addmisionSpecializationRepositories = null;
		try {
			addmisionSpecializationRepositories = this.admissionSpecializationRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all addmision specializations: ", e);
		}
		return addmisionSpecializationRepositories;
	}

	public AdmissionSpecialization save(AdmissionSpecialization admissionSpecialization, boolean flush) {
		AdmissionSpecialization savedAdmissionSpecialization = null;
		try {
			if (admissionSpecialization != null) {
				if (flush) {
					savedAdmissionSpecialization = this.admissionSpecializationRepository
							.saveAndFlush(admissionSpecialization);
				} else {
					savedAdmissionSpecialization = this.admissionSpecializationRepository.save(admissionSpecialization);
				}
			}
		} catch (Exception e) {
			logger.error("Save addmision specialization sample: " + admissionSpecialization, e);
			throw e;
		}
		return savedAdmissionSpecialization;
	}

	public AdmissionSpecialization findById(String admissionSpecializationId) {
		AdmissionSpecialization admissionSpecialization = null;
		try {
			if (StringUtils.isNotBlank(admissionSpecializationId)) {
				admissionSpecialization = this.admissionSpecializationRepository.findById(admissionSpecializationId)
						.get();
			}
		} catch (Exception e) {
			logger.error("Find admission specialization by id: " + admissionSpecializationId, e);
		}
		return admissionSpecialization;
	}

	public List<Statistics> getStatistics(String admissionSpecializationId) {
		List<Statistics> statistics = null;
		try {
			if (StringUtils.isNotBlank(admissionSpecializationId)) {
				AdmissionSpecialization admissionSpecialization = findById(admissionSpecializationId);
				if (admissionSpecializationId != null) {
					List<AppliedSession> appliedSessions = this.appliedSessionService
							.findAppliedSessionsByAdmissionSpecialization(admissionSpecialization);
					if (appliedSessions != null && !appliedSessions.isEmpty()) {
						statistics = this.statisticsUtil.createAllStatistics(appliedSessions);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Get statistics for admission specilization id: " + admissionSpecializationId, e);
		}

		return statistics;
	}
}
