package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.MedicalCondition;
import ro.inf.ucv.admitere.repository.MedicalConditionRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class MedicalConditionService {

	private static final Logger logger = Logger.getLogger(MedicalConditionService.class);

	@Autowired
	private MedicalConditionRepository medicalConditionRepository;

	public List<MedicalCondition> findAll() {
		List<MedicalCondition> medicalConditions = null;
		try {
			medicalConditions = medicalConditionRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all medical conditions: ", e);
		}
		return medicalConditions;
	}

	public MedicalCondition save(MedicalCondition medicalCondition, boolean flush) {
		MedicalCondition savedMedicalCondition = null;
		try {
			if (medicalCondition != null) {
				if (flush) {
					savedMedicalCondition = medicalConditionRepository.saveAndFlush(medicalCondition);
				} else {
					savedMedicalCondition = medicalConditionRepository.save(medicalCondition);
				}
			}
		} catch (Exception e) {
			logger.error("Save medical condition: " + medicalCondition, e);
			throw e;
		}
		return savedMedicalCondition;
	}

	public MedicalCondition findOne(Integer id) {
		MedicalCondition medicalCondition = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				medicalCondition = medicalConditionRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find medical condition by id: ", e);
		}

		return medicalCondition;
	}
}
