package ro.inf.ucv.admitere.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.repository.AdmissionSpecializationRepository;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Service
@Transactional
public class AdmissionSpecializationService {

	private static final Logger logger = Logger.getLogger(AdmissionSpecializationService.class);

	@Autowired
	private AdmissionSpecializationRepository admissionSpecializationRepository;

	@Autowired
	private AppliedSessionService appliedSessionService;

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
						// TODO: Add more statistics and made this using sql and not memory for
						// optimization.
						Map<String, Integer> statisticsByDay = new HashMap<String, Integer>();
						Map<String, Integer> statisticsByCity = new HashMap<String, Integer>();
						Map<String, Integer> statisticsByUserBirthYear = new HashMap<String, Integer>();
						Map<String, Integer> statisticsByGender = new HashMap<String, Integer>();
						Map<String, Integer> statisticsByReligion = new HashMap<String, Integer>();
						Map<String, Integer> statisticsByEthnicity = new HashMap<String, Integer>();
						for (AppliedSession appliedSession : appliedSessions) {
							try {
								createStatisticsByDay(appliedSession, statisticsByDay);
								createStatisticsByGender(appliedSession, statisticsByGender);
								createStatisticsByCity(appliedSession, statisticsByCity);
								createStatisticsByReligion(appliedSession, statisticsByReligion);
								createStatisticsByEthnicity(appliedSession, statisticsByEthnicity);
								createStatisticsByUserBirthYear(appliedSession, statisticsByUserBirthYear);
							} catch (Exception e) {
								logger.error("Failed to create statistics: " + appliedSession, e);
							}
						}
						statistics = new ArrayList<Statistics>();
						statistics.add(createStatisticsObject("Day", statisticsByDay));
						statistics.add(createStatisticsObject("City", statisticsByCity));
						statistics.add(createStatisticsObject("User birth year", statisticsByUserBirthYear));
						statistics.add(createStatisticsObject("Religion", statisticsByReligion));
						statistics.add(createStatisticsObject("Gender", statisticsByGender));
						statistics.add(createStatisticsObject("Ethnicity", statisticsByEthnicity));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Get statistics for admission specilization id: " + admissionSpecializationId, e);
		}

		return statistics;
	}

	private void createStatisticsByEthnicity(AppliedSession appliedSession,
			Map<String, Integer> statisticsByEthnicity) {
		String ethnicity = appliedSession.getUser().getProfile().getEthnicity().getName();
		updateMap(statisticsByEthnicity, ethnicity);
	}

	private void createStatisticsByReligion(AppliedSession appliedSession, Map<String, Integer> statisticsByReligion) {
		String religion = appliedSession.getUser().getProfile().getReligion().getName();
		updateMap(statisticsByReligion, religion);
	}

	private void createStatisticsByGender(AppliedSession appliedSession, Map<String, Integer> statisticsByGender) {
		String gender = appliedSession.getUser().getProfile().getGender().getName();
		updateMap(statisticsByGender, gender);
	}

	private void createStatisticsByUserBirthYear(AppliedSession appliedSession,
			Map<String, Integer> statisticsByUserBirthYear) {
		String birthYear = DateFormatUtils.format(appliedSession.getUser().getProfile().getBirthDate(), "yyyy");
		updateMap(statisticsByUserBirthYear, birthYear);
	}

	private void createStatisticsByCity(AppliedSession appliedSession, Map<String, Integer> statisticsByCity) {
		String city = appliedSession.getUser().getProfile().getCity().getName();
		updateMap(statisticsByCity, city);
	}

	private void createStatisticsByDay(AppliedSession appliedSession, Map<String, Integer> statisticsByDay) {
		String creationDate = DateFormatUtils.format(appliedSession.getCreationDate(), "yyyy-MM-dd");
		updateMap(statisticsByDay, creationDate);
	}

	private Statistics createStatisticsObject(String name, Map<String, Integer> statistics) {
		return new Statistics(name, statistics.keySet(), statistics.values());
	}

	private void updateMap(Map<String, Integer> stats, String string) {
		Integer n = stats.get(string);
		if (n != null) {
			n += 1;
		} else {
			n = 1;
		}
		stats.put(string, n);
	}
}
