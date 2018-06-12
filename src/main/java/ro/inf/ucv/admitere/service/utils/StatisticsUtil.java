package ro.inf.ucv.admitere.service.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AppliedSession;
import ro.inf.ucv.admitere.wrapper.Statistics;

@Service
public class StatisticsUtil {

	private static final Logger logger = Logger.getLogger(StatisticsUtil.class);

	public List<Statistics> createAllStatistics(List<AppliedSession> appliedSessions) {
		// TODO: Keep this on memory because is more faster.
		// TODO: Add more statistics here.
		List<Statistics> statistics = new ArrayList<>();
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

		return statistics;
	}

	public void createStatisticsByEthnicity(AppliedSession appliedSession, Map<String, Integer> statisticsByEthnicity) {
		String ethnicity = appliedSession.getUser().getProfile().getEthnicity().getName();
		updateMap(statisticsByEthnicity, ethnicity);
	}

	public void createStatisticsByReligion(AppliedSession appliedSession, Map<String, Integer> statisticsByReligion) {
		String religion = appliedSession.getUser().getProfile().getReligion().getName();
		updateMap(statisticsByReligion, religion);
	}

	public void createStatisticsByGender(AppliedSession appliedSession, Map<String, Integer> statisticsByGender) {
		String gender = appliedSession.getUser().getProfile().getGender().getName();
		updateMap(statisticsByGender, gender);
	}

	public void createStatisticsByUserBirthYear(AppliedSession appliedSession,
			Map<String, Integer> statisticsByUserBirthYear) {
		String birthYear = DateFormatUtils.format(appliedSession.getUser().getProfile().getBirthDate(), "yyyy");
		updateMap(statisticsByUserBirthYear, birthYear);
	}

	public void createStatisticsByCity(AppliedSession appliedSession, Map<String, Integer> statisticsByCity) {
		String city = appliedSession.getUser().getProfile().getCity().getName();
		updateMap(statisticsByCity, city);
	}

	public void createStatisticsByDay(AppliedSession appliedSession, Map<String, Integer> statisticsByDay) {
		String creationDate = DateFormatUtils.format(appliedSession.getCreationDate(), "yyyy-MM-dd");
		updateMap(statisticsByDay, creationDate);
	}

	public Statistics createStatisticsObject(String name, Map<String, Integer> statistics) {
		return new Statistics(name, statistics.keySet(), statistics.values());
	}

	public void updateMap(Map<String, Integer> stats, String string) {
		Integer n = stats.get(string);
		if (n != null) {
			n += 1;
		} else {
			n = 1;
		}
		stats.put(string, n);
	}
}
