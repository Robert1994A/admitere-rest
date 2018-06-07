package ro.inf.ucv.admitere.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.Gender;
import ro.inf.ucv.admitere.repository.GenderRepository;
import ro.inf.ucv.admitere.service.utils.PaginationUtils;
import ro.inf.ucv.admitere.utils.PrimitiveUtils;
import ro.inf.ucv.admitere.wrapper.SearchModel;

@Service
@Transactional
public class GenderService {

	private static final Logger logger = Logger.getLogger(GenderService.class);

	@Autowired
	private GenderRepository genderRepository;

	@Autowired
	private PaginationUtils paginationUtils;

	public Page<Gender> findAll(Pageable pageable) {
		Page<Gender> genders = null;
		try {
			genders = genderRepository.findAll(pageable);
		} catch (Exception e) {
			logger.error("Find all genders: ", e);
		}

		return genders;
	}

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
				gender.setCreationDate(new Date());
				gender.setId(null);
				if (flush) {
					savedGender = this.genderRepository.saveAndFlush(gender);
				} else {
					savedGender = this.genderRepository.save(gender);
				}
			}
		} catch (Exception e) {
			logger.error("Save gender: " + gender, e);
			throw e;
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

	public Page<Gender> inteligentPagination(SearchModel searchModel) {
		Page<Gender> genders = null;
		try {
			if (searchModel != null) {
				Pageable pageable = paginationUtils.getPageRequest(new Gender(), searchModel);
				if (StringUtils.isNotBlank(searchModel.getSearch())) {
					genders = pagination(searchModel.getSearch(), pageable);
				} else {
					genders = findAll(pageable);
				}
			}
		} catch (Exception e) {
			logger.error("Find genders: " + e);
		}

		return genders;
	}

	private Page<Gender> pagination(String search, Pageable pageable) {
		Page<Gender> genders = null;
		try {
			if (StringUtils.isNotBlank(search) && pageable != null) {
				genders = this.genderRepository.findByNameIgnoreCaseContaining(search, pageable);
			}
		} catch (Exception e) {
			logger.error("Paginate genders: ", e);
		}

		return genders;
	}

	public void deleteOne(Integer genderId) {
		if (PrimitiveUtils.isValid(genderId)) {
			this.genderRepository.deleteById(genderId);
		}
	}

	public List<String> deleteByIds(List<Integer> genderIds) {
		List<String> warningMessages = new ArrayList<String>();
		if (genderIds != null && !genderIds.isEmpty()) {
			for (Integer genderId : genderIds) {
				if (genderId != null && genderId.intValue() > 0) {
					try {
						this.deleteOne(genderId);
					} catch (Exception e) {
						logger.error("Cannot delete gender with id: " + genderId);
						warningMessages.add("Cannot delete gender with id: " + genderId);
					}
				} else {
					logger.error("Cannot delete gender with id: " + genderId);
					warningMessages.add("Cannot delete gender with id: " + genderId);
				}
			}
		}

		return warningMessages;
	}
}
