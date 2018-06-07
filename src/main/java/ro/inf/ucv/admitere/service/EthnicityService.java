package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Ethnicity;
import ro.inf.ucv.admitere.repository.EthnicityRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class EthnicityService {

	private static final Logger logger = Logger.getLogger(EthnicityService.class);

	@Autowired
	private EthnicityRepository ethnicityRepository;

	public List<Ethnicity> findAll() {
		List<Ethnicity> ethnicities = null;
		try {
			ethnicities = ethnicityRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all ethnicities: ", e);
		}
		return ethnicities;
	}

	public Ethnicity save(Ethnicity ethnicity, boolean flush) {
		Ethnicity savedEthnicity = null;
		try {
			if (ethnicity != null) {
				if (flush) {
					savedEthnicity = ethnicityRepository.saveAndFlush(ethnicity);
				} else {
					savedEthnicity = ethnicityRepository.save(ethnicity);
				}
			}
		} catch (Exception e) {
			logger.error("Save ethnicity: " + ethnicity, e);
			throw e;
		}
		return savedEthnicity;
	}

	public Ethnicity findOne(Integer id) {
		Ethnicity ethnicity = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				ethnicity = ethnicityRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find ethnicity by id: " + id, e);
		}

		return ethnicity;
	}
}
