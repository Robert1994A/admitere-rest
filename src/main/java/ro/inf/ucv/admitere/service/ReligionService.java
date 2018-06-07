package ro.inf.ucv.admitere.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Religion;
import ro.inf.ucv.admitere.repository.ReligionRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class ReligionService {
	private static final Logger logger = Logger.getLogger(ReligionService.class);

	@Autowired
	private ReligionRepository religionRepository;

	public List<Religion> findAll() {
		List<Religion> religions = null;
		try {
			religions = religionRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all religions: ", e);
		}
		return religions;
	}

	public Religion save(Religion religion, boolean flush) {
		Religion savedReligion = null;
		try {
			if (religion != null) {
				if (flush) {
					savedReligion = religionRepository.saveAndFlush(religion);
				} else {
					savedReligion = religionRepository.save(religion);
				}
			}
		} catch (Exception e) {
			logger.error("Save religion: " + religion, e);
			throw e;
		}
		return savedReligion;
	}

	public Religion findOne(Integer id) {
		Religion religion = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				religion = religionRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find religion by id: " + id, e);
		}

		return religion;
	}
}
