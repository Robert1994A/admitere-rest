package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.MaritalStatus;
import ro.inf.ucv.admitere.repository.MaritalStatusRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class MaritalStatusService {

	private static final Logger logger = Logger.getLogger(MaritalStatusService.class);

	@Autowired
	private MaritalStatusRepository maritalStatusRepository;

	public List<MaritalStatus> findAll() {
		List<MaritalStatus> maritalStatus = null;
		try {
			maritalStatus = maritalStatusRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all marital status: ", e);
		}
		return maritalStatus;
	}

	public MaritalStatus save(MaritalStatus maritalStatus, boolean flush) {
		MaritalStatus savedMaritalStatus = null;
		try {
			if (maritalStatus != null) {
				if (flush) {
					savedMaritalStatus = maritalStatusRepository.saveAndFlush(maritalStatus);
				} else {
					savedMaritalStatus = maritalStatusRepository.save(maritalStatus);
				}
			}
		} catch (Exception e) {
			logger.error("Save marital status: " + maritalStatus, e);
			throw e;
		}
		return savedMaritalStatus;
	}

	public MaritalStatus findOne(Integer id) {
		MaritalStatus maritalStatus = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				maritalStatus = maritalStatusRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find marital status by id: " + id, e);
		}

		return maritalStatus;
	}
}
