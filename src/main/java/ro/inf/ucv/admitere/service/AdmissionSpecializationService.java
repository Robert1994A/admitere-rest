package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.AdmissionSpecialization;
import ro.inf.ucv.admitere.repository.AdmissionSpecializationRepository;

@Service
@Transactional
public class AdmissionSpecializationService {

	private static final Logger logger = Logger.getLogger(AdmissionSpecializationService.class);

	@Autowired
	private AdmissionSpecializationRepository admissionSpecializationRepository;

	public List<AdmissionSpecialization> findAll() {
		List<AdmissionSpecialization> addmisionSpecializationRepositories = null;
		try {
			addmisionSpecializationRepositories = admissionSpecializationRepository.findAll();
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
					savedAdmissionSpecialization = admissionSpecializationRepository
							.saveAndFlush(admissionSpecialization);
				} else {
					savedAdmissionSpecialization = admissionSpecializationRepository.save(admissionSpecialization);
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
				admissionSpecialization = admissionSpecializationRepository.findById(admissionSpecializationId).get();
			}
		} catch (Exception e) {
			logger.error("Find admission specialization by id: " + admissionSpecializationId, e);
		}
		return admissionSpecialization;
	}
}
