package ro.inf.ucv.admitere.service;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.repository.GenderRepository;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;

@Service
@Transactional
public class GenderService {

	private static final Logger logger = Logger.getLogger(GenderService.class);

	@Autowired
	private GenderRepository genderRepository;

	public List<Gender> findAll() {
		List<Gender> genders = null;
		try {
			genders = genderRepository.findAll();
		} catch (Exception e) {
			logger.error("Find all genders: ", e);
		}
		return genders;
	}

	public Gender save(Gender gender, boolean flush) {
		Gender savedGender = null;
		try {
			if (gender != null) {
				if (flush) {
					savedGender = genderRepository.saveAndFlush(gender);
				} else {
					savedGender = genderRepository.save(gender);
				}
			}
		} catch (Exception e) {
			logger.error("Save gender: " + gender, e);
		}
		return savedGender;
	}

	public Gender findOne(Integer id) {
		Gender gender = null;
		try {
			if (PrimitiveUtils.isValid(id)) {
				gender = genderRepository.findById(id).get();
			}
		} catch (Exception e) {
			logger.error("Find gender by id: ", e);
		}

		return gender;
	}
}
