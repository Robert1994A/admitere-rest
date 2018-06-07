package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Citizenship;
import ro.inf.ucv.admitere.repository.CitizenshipRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class CitizenshipService {
	private static final Logger logger = Logger.getLogger(CitizenshipService.class);

	@Autowired
	private CitizenshipRepository citizenshipRepository;

	public List<Citizenship> findAll() {
		List<Citizenship> citizenships = null;
		try {
			citizenships = citizenshipRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all citizenship: ", e);
		}
		return citizenships;
	}

	public Citizenship save(Citizenship citizenship, boolean flush) {
		Citizenship savedCitizenship = null;
		try {
			if (citizenship != null) {
				if (flush) {
					savedCitizenship = citizenshipRepository.saveAndFlush(citizenship);
				} else {
					savedCitizenship = citizenshipRepository.save(citizenship);
				}
			}
		} catch (Exception e) {
			logger.error("Save citizenship: " + citizenship, e);
			throw e;
		}
		return savedCitizenship;
	}

	public Citizenship findOne(Integer id) {
		Citizenship citizenship = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				citizenship = citizenshipRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find citizenship by id: " + id, e);
		}

		return citizenship;
	}
}
